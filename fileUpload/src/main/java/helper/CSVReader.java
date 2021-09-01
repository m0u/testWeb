package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/** 
 * 
 * This is responsible for reading the TDB file from the given input file
 * @author Pritam Sil
 */
public class CSVReader
{	
	/**
	 * Method to read the file and return a DataFrame object
	 * @param path the path to the file
	 * @param delim the delimiter value i.e. "," for csv files or "	" for tsv files
	 * @return a DataFrame object 
	 * */
	public DataFrame getDataFrame(String path,String delim) throws IOException
	{
		return this.getDataFrame(path, delim,1);
	}
	/**
	 * Method to read the file and return a DataFrame object
	 * @param path the path to the file
	 * @param delim the delimiter value i.e. "," for csv files or "	" for tsv files
	 * @param thresh a threshold value to convert the given data to a binary data in case it has values other than 0 or 1 
	 * @return a DataFrame object 
	 * */
	public DataFrame getDataFrame(String path,String delim,int thresh) throws IOException
	{
		
		BufferedReader read= new BufferedReader(new FileReader(path));
		String line=null;
//		byte[][] mat=new byte[Integer.parseInt(k[0])-1][Integer.parseInt(k[1])-1];
		
		line=read.readLine();
		String k[]=line.split(delim);
		String[] cnames=new String[k.length-1];
		for(int i=0;i<cnames.length;i++)
			cnames[i]=k[i+1];
		List<String> rnames=new ArrayList<String>();
		List<List<Integer>> mat=new ArrayList<List<Integer>>();
		
		while((line=read.readLine())!=null)
		{
			List<Integer> temp=new ArrayList<Integer>();
			k=line.split(delim);
			rnames.add(k[0]);
			
			for(int i=1;i<k.length;i++)
				if(Long.parseLong(k[i].trim())>=thresh)
					temp.add(1);
				else
					temp.add(0);
			mat.add(temp);
		}
		
		read.close();
		return new DataFrame(mat,rnames,cnames);
	}	
}
