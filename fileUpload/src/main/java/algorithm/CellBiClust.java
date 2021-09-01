package algorithm;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import helper.CSVReader;
import helper.DataFrame;
import helper.MemoryTracker;
import helper.Predict;
import helper.ThreadChannel;
/** 
 * This is an implementation of the CellBiClust algorithm.
 * CellBiClust is described here:
 * <br/><br/>
 * Paper Name
 * <br/><br/>
 * 
 * It saves the result to a file and returns it to the user when runAlgorithm() is executed.
 *
 * @see TNode
 * @see TreeCell
 * @see ThreadChannel
 * @see CSVReader
 * @author Pritam Sil
 */
public class CellBiClust
{
	MemoryTracker m;//To keep track of the maximum memory used
	DataFrame inputData;//To store the input data
	List<List<Long>> TDB;// To store input Transactional Database
	long mincols;//The minimum number of rows in the cluster
	long minrows;//The minimum number of columns in the cluster
	Map<Long,Long> FImap;//To store the count of each frequent item
	Map<Long,TreeCell> CLAMap;//To store the CLA. Each Cell is mapped to its cell type in it.
	Set<Entry<Set<Long>, Set<Long>>> outputTemp;//To store the output
	
	//A comparator to to order two items based on their frequency
	private Comparator<Long> FIorderComparator=new Comparator<Long>(){
		public int compare(Long item1, Long item2){
			long compare = FImap.get(item2) - FImap.get(item1);
			if(compare == 0){ 
				return (int)(item1 - item2);
			}
			return (int)compare;
		}
	};
	
	//A comparator to compare between two lists
	private Comparator<Entry<Set<Long>,Set<Long>>> 
		coverComp=new Comparator<Entry<Set<Long>,Set<Long>>>()
	{
		public int compare(Entry<Set<Long>, Set<Long>> o1, Entry<Set<Long>, Set<Long>> o2)
		{
			if(o1.getKey().containsAll(o2.getKey()) && 
					o1.getValue().containsAll(o2.getValue()))
			{
				return 0;
			}

			return 1;
		}
		
	};
	
	//A comparator to compare between two Entry objects
	private Comparator<Entry<Set<Long>,Set<Long>>> entryComp=new Comparator<Entry<Set<Long>,Set<Long>>>() 
	{
		public int compare(Entry<Set<Long>, Set<Long>> o1, Entry<Set<Long>, Set<Long>> o2)
		{
			if(o1.getKey().size()<o2.getKey().size())
				return -1;
			if(Sets.symmetricDifference(o1.getKey(),o2.getKey()).isEmpty()
					&& Sets.symmetricDifference(o1.getValue(),o2.getValue()).isEmpty())
				return 0;
			return 1;
		}
	};
	
	/**
	 * Constructor
	 * @param TDB the DataFrame of the TDB
	 * @param minrows the minimum number of rows in the bicluster
	 * @param mincols the minimum number of columns in the bicluster
	 * */
	public CellBiClust(DataFrame TDB,long minrows,long mincols)
	{
		this.inputData=TDB;
		this.TDB=new ArrayList<List<Long>>();
		this.TDB.addAll(TDB.convertToTr());
		this.CLAMap=new TreeMap<Long,TreeCell>();
		this.mincols=mincols;
		this.minrows=minrows;
		this.m=new MemoryTracker();
	}
	
	/**
	 * Constructor
	 * @param input the path to an input file containing a presence absence database.
	 * @param delim the delimiter between each item in the input file.For example it will be "," for csv files
	 * @param minrows the minimum number of rows in the bicluster
	 * @param mincols the minimum number of columns in the bicluster
	 * @throws IOException 
	 * */
	public CellBiClust(String input,String delim,long minrows,long mincols) throws IOException
	{
		DataFrame TDB=new CSVReader().getDataFrame(input,delim);;
		this.inputData=TDB;
		this.inputData=TDB;
		this.TDB=new ArrayList<List<Long>>();
		this.TDB.addAll(TDB.convertToTr());
		this.CLAMap=new TreeMap<Long,TreeCell>();
		this.mincols=mincols;
		this.minrows=minrows;
		this.m=new MemoryTracker();
	}
	
	/**
	 * Method to return the input data object
	 */
	public DataFrame getInputData()
	{
		return this.inputData;
	}
	
	/**
	 * Method to create the FImap which maps each frequent item to its frquency.
	 */
	void find_freq_item()
	{
		Map<Long,Long> FImap=new HashMap<Long,Long>();
		this.FImap=new HashMap<Long,Long>();
		for(List<Long> s:TDB)
			for(Long i:s)
			{
				if(FImap.containsKey(i))
					FImap.put(i,FImap.get(i)+1);
				else
					FImap.put(i,(long)1);
			}
		for(Long i:FImap.keySet())
		{
			if(FImap.get(i)>=this.minrows)
				this.FImap.put(i, FImap.get(i));
		}
	}
	
	/**
	 * Method to convert the Transactional Database (TDB) into Dense Dataset (DDS)
	 * @return the Dense Dataset
	 */
	HashMap<Long, List<Long>> get_CDS()
	{
		HashMap<Long,List<Long>> ob=new HashMap<Long,List<Long>>();
		int tid=0;
		for(List<Long> i:TDB)
		{
			List<Long> temp=new ArrayList<Long>();
			for(Long j:i)
				if(FImap.containsKey(j) )
					temp.add(j);
			tid++;
			if(temp.size()==0)
				continue;
			Collections.sort(temp,FIorderComparator);
			ob.put((long)tid,temp);
			TreeCell tempCell=new TreeCell(temp.get(0));
			CLAMap.put(temp.get(0),tempCell);
		}
		return ob;
	}
	
	/**
	 * Method to generate the remaining clusters from the initial cluster map
	 * @param clusterMap the initial cluster map
	 * */
	public void getCluster(Map<Long,Map<Set<Long>,Set<Long>>> clusterMap)
	{
		Map<Set<Long>,Set<Long>> itemMap,temp;
		for(Long i:clusterMap.keySet())
		{
			itemMap=new HashMap<Set<Long>,Set<Long>>();
			
			for(Entry<Set<Long>, Set<Long>>  j:itemMap.entrySet())
				if(j.getKey().size()<this.mincols)
					clusterMap.get(i).remove(j.getKey());
			itemMap.putAll(clusterMap.get(i));
			do {
				temp=new HashMap<Set<Long>,Set<Long>>();
				Set<Entry<Set<Long>,Set<Long>>> tempS=itemMap.entrySet();
				for(Entry<Set<Long>,Set<Long>> j:tempS)
				{	
					boolean flag=true;
					for(Entry<Set<Long>,Set<Long>> k:tempS)
					{
						if(flag && j!=k)
						{
							continue;
						}
						else if(flag)
						{
							flag=false;
							continue;
						}
						Set<Long> itemset1=new TreeSet<Long>();
						itemset1.addAll(j.getKey()); 
						Set<Long> itemset2=new TreeSet<Long>();
						itemset2.addAll(k.getKey());
						itemset1.retainAll(itemset2);
						
						Set<Long> transet1=new TreeSet<Long>();
						transet1.addAll(j.getValue()); 
						Set<Long> transet2=new TreeSet<Long>();
						transet2.addAll(k.getValue());
						transet1.addAll(transet2);
						
						if(itemset1.size()>=this.mincols)
						{
							temp.put(itemset1, transet1);
							if(clusterMap.get(i).containsKey(itemset1))
								clusterMap.get(i).get(itemset1).addAll(transet1);
							else
								clusterMap.get(i).put(itemset1, transet1);
						}
					}
				}
				itemMap=temp;
			}while(temp.size()>1);
		}
	}
	
	/**
	 * Method to generate the bclusters by executing CellBiClust algorithm<br>
	 * <i>Warning</i>(for developers) : the returned object is mutable and modifying it will lead to undesired output
	 * @param outPath the output file path for saving the biclusters obtained (if null, the biclusters will be printed)
	 * @param fname the filename only. Example "output". File extensions will be added automatically
	 * @param rule set to "true" if you want to generate rules else set to "false"
	 * @param ruleth the minimum condfidence for the rule
	 * @return a set containing all the biclusters
	 * */
	public Set<Entry<Set<Long>, Set<Long>>> runAlgorithm(String outPath,String fname,String rule,double ruleth,double similarity) throws InterruptedException
	{
		long start=System.currentTimeMillis();
		find_freq_item();
		List<Long> orderMap=FImap.keySet().stream().collect(Collectors.toList());
		Collections.sort(orderMap,FIorderComparator);
		m.checkUsage();
		HashMap<Long, List<Long>> CDS=get_CDS();
		m.checkUsage();
		ThreadChannel channel=new ThreadChannel(FImap.keySet());
		for(Long s:CDS.keySet())
		{
			channel.add(CDS.get(s).get(0),new Thread(()->CLAMap.get(CDS.get(s).get(0)).add(CDS.get(s),s)));
		}
		channel.shutdown();
		Map<Long,Map<Set<Long>,Set<Long>>> clusterMap=new HashMap<Long,Map<Set<Long>,Set<Long>>>();
		for(Long i:FImap.keySet())
			clusterMap.put(i, new HashMap<Set<Long>,Set<Long>>());
		
		for(Long i:CLAMap.keySet())
		{
			TreeCell k=CLAMap.get(i);
			k.traverseCluster(k.root, clusterMap,new ArrayList<Long>());
		}
		this.getCluster(clusterMap);
		
		Set<Entry<Set<Long>,Set<Long>>> buf=new HashSet<Entry<Set<Long>,Set<Long>>>();
		clusterMap.forEach((k,v)->
		{
			buf.addAll(v.entrySet());
		});
		
		Set<Entry<Set<Long>,Set<Long>>> output=new HashSet<Entry<Set<Long>,Set<Long>>>();
		boolean flag;
		for(Entry<Set<Long>,Set<Long>> i:buf)
		{
			flag=true;
			if(i.getKey().size()<this.mincols || i.getValue().size()<this.minrows)
			{
				continue;
			}
			for(Entry<Set<Long>,Set<Long>> j:buf)
				if(this.entryComp.compare(i,j)!=0 && this.coverComp.compare(j,i)==0)
				{
					flag=false;
					break;
				}
			if(flag)
				output.add(i);
		}
		long end=System.currentTimeMillis();
		
		this.outputTemp=output;
		String details="==================Summary==================\n"
		+"Number of Biclusters Generated : " + output.size()
		+"\nNumber of transactions : " + TDB.size()
		+"\nMax memory : "+ m.getMaxMemory() +" MB\n"
		+"Time taken : "+ (end-start) +" ms\n"+"===========================================";
		if(outPath==null)
		{
			System.out.println("Following are the Biclusters ---");
			System.out.println(details);
			System.out.println(getBiClustersAsString());
		}
		else
		{
			
			try {
				FileWriter outFile = new FileWriter(outPath+"/"+fname+"_biclusters.csv");
				outFile.write(details);
				outFile.write(getBiClustersAsString());
				
				outFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("==================Summary==================");
		System.out.println("Number of Biclusters Generated : " + output.size());
		System.out.println("Number of transactions : " + TDB.size());
		System.out.println("Max memory : "+ m.getMaxMemory() +" MB");
		System.out.println("Time taken : "+ (end-start) +" ms");
		System.out.println("===========================================");
		if(rule.equals("true"))
			try {
				
				new Predict(this.inputData,output,ruleth).getRules(outPath,fname);
				new Predict(this.inputData,output,ruleth).getRulesFromBicluster(outPath,fname,similarity);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return output;
	}

	/**
	 * Method to convert the output from CellBiClust to a String
	 * @return the String value
	 * */
	public String getBiClustersAsString()
	{
		int k=1;
		String sout="\n";
		for(Entry<Set<Long>, Set<Long>> i:this.outputTemp)
		{
//			sout+="Bicluster "+k+++
//					"\nNumber of Items : "+i.getKey().size()+
//					"\nNumber of Transactions : "+i.getValue().size()+"\n";
//			String s1="Item_names :",s2="Item_indexes :";
			String s1="\"";
			for(Long j:i.getKey())
			{
//				s1+="\t"+this.inputData.getCName((int)(long)j-1);
				s1+=this.inputData.getCName((int)(long)j-1)+",";
//				s2+="\t"+(j-1);
			}
			sout+=s1.substring(0,s1.length()-1)+"\",\"";//+"\n"+s2+"\n";
//			s1="Transaction names : ";
//			s2="Transaction indexes : ";
			s1="";
			for(Long j:i.getValue())
			{
//				s1+="\t"+this.inputData.getRName((int)(long)j-1);
				s1+=this.inputData.getRName((int)(long)j-1)+",";
//				s2+="\t"+(j-1);
			}
//			sout+=s1+"\n"+s2+"\n\n";
			sout+=s1.substring(0,s1.length()-1)+"\"\n";
		}
		return sout;
	}
}
 