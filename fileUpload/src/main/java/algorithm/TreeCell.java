package algorithm;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/** 
 * 
 * This is used for representing a Cell in the Cellular Learning Automata
 * @author Pritam Sil
 */
public class TreeCell
{
	public long type;// the cell type 
	public TNode root;// the root node
	
	/**
	 * Constructor
	 * @param ch The type of the cell
	 */
	public TreeCell(long ch)
	{
		type=ch;
		root=null;
	}
	
	/**
	 * Method to add a Transaction to the FPTree
	 * @param list The transaction
	 * @param count the count of the transaction
	 */
	public void add(List<Long> s,long id)
	{
		if(root==null)
		{
			root=new TNode(s.get(0));
			TNode temp=root;
			for(int i=1;i<s.size();i++)
			{
				temp.child.add(new TNode(s.get(i)));
				temp=temp.child.get(0);
			}
			temp.val.add(id);
		}
		else
		{
			TNode temp=root;
			boolean flag;
			for(int i=0;i<s.size()-1;i++)
			{
				if(temp.val.size()>0)
				{
					TNode temp1=new TNode(-1);
					temp.child.add(temp1);
					temp1.val.addAll(temp.val);
					temp.val.clear();
//					System.out.println("In main "+temp);
				}
				flag=true;
				for(TNode k:temp.child)
					if(k.type==s.get(i+1))
					{
						temp=k;
						flag=false;
						break;
					}
				if(flag)
				{
					TNode temp1=new TNode(s.get(i+1));
					temp.child.add(temp1);
					temp=temp1;
				}
			}
			if(temp.child.size()>0)
			{

//				System.out.println("Final Node : "+temp);
				for(TNode temp1:temp.child)
					if(temp1.type==-1)
					{
						temp1.val.add(id);
						//System.out.println("TBA : "+temp1);
						return;
					}
				
			}
			temp.val.add(id);
		}
	}
	
	/**
	 * Method to return the tids in a subtree with temp as the root
	 * @param temp the root node of the subtree
	 * @param trans a list to store the tids
	 * */
	public void getTids(TNode temp,List<Long> trans)
	{
		if(temp==null)
			return;
		trans.addAll(temp.val);
		for(TNode i:temp.child)
			getTids(i,trans);
	}
	
	/**
	 * Method to return the tids in a subtree with temp as the root
	 * @param temp the current node 
	 * @param clusterMap a map to store the clusters of each item
	 * @param item a list to store the items
	 * */
	public void traverseCluster(TNode temp,Map<Long,Map<Set<Long>,Set<Long>>> clusterMap,List<Long> item)
	{
		item.add(temp.type);
//		if(item.size()>1)
//		{
			if(temp.val.size()>0)
			{
				
				if(temp.type!=-1)
				{
					Set<Long> tt=new TreeSet<Long>();
					tt.addAll(temp.val);
					Set<Long> itemTemp=new TreeSet<Long>();
					itemTemp.addAll(item);
					clusterMap.get(temp.type).put(itemTemp,tt);
				}
//				System.out.println("+++++-"+itemTemp);
				for(int i=2;i<item.size();i++)
				{
					Set<Long> ttt=new TreeSet<Long>();
					for(int j=0;j<i;j++)
						ttt.add(item.get(j));
					Long type=item.get(i-1);
//					System.out.println(clusterMap.get(type)+" "+ttt+" "+temp.val);
					if(clusterMap.get(type).containsKey(ttt))
					{
						clusterMap.get(type).get(ttt).addAll(temp.val);
//						System.out.println("------"+clusterMap.get(type)+" "+ttt+" "+temp.val);
					}
					else
					{
						Set<Long> tt=new TreeSet<Long>();
						tt.addAll(temp.val);
						Set<Long> itemTemp=new TreeSet<Long>();
						itemTemp.addAll(item);
						clusterMap.get(temp.type).put(itemTemp,tt);
//						System.out.println("-@@@@@"+clusterMap.get(type)+" "+ttt+" "+temp.val);
					}
				}
			}
			else if(item.size()>1) 
			{
				Set<Long> itemTemp=new TreeSet<Long>();
				itemTemp.addAll(item);
				clusterMap.get(temp.type).put(itemTemp,new TreeSet<Long>());
			}
		//}
		for(TNode i:temp.child)
		{
			this.traverseCluster(i, clusterMap, item);
			item.remove(i.type);
		}
	}
	/**
	 * Method to perform inorder traversal
	 * @param temp the root Node
	 * */
	public void traverse(TNode temp)
	{
		if(temp==null)
			return;
		System.out.print(temp+"|");
		for(TNode i:temp.child)
			traverse(i);
	}
}
