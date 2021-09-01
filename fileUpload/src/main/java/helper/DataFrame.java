package helper;

import java.util.ArrayList;
import java.util.List;
/** 
 * 
 * A simple class to represent a DataFrame
 * @author Pritam Sil
 */
public class DataFrame
{
	byte[][] mat;//The binary matrix to store the presence absence dataset
	String rownames[];//Array to store the row names
	String colnames[];//Array to store the column names
	/**
	 * Constructor
	 * @param a the binary matrix
	 * @param b the array containing row names
	 * @param c the array containing column names
	 */
	public DataFrame(byte [][]a,String []b,String c[])
	{
		this.mat=a;
		this.rownames=b;
		this.colnames=c;
	}
	/**
	 * Constructor
	 * @param a the binary matrix as a list of list
	 * @param rnames the list containing row names
	 * @param cnames the array containing column names
	 */
	public DataFrame(List<List<Integer>> mat2, List<String> rnames, String[] cnames)
	{
		this.rownames=new String[rnames.size()];
		this.mat=new byte[mat2.size()][mat2.get(0).size()];
		for(int i=0;i<this.rownames.length;i++)
		{
			rownames[i]=rnames.get(i);
			List<Integer> temp=mat2.get(i);
			for(int j=0;j<mat[0].length;j++)
				mat[i][j]=(byte)((int)temp.get(j));
		}
		this.colnames=cnames;
	}
	/**
	 * Method to return the row name of a certain row
	 * @param i the row index
	 * @return the row name
	 * */
	public String getRName(int i)
	{
		return this.rownames[i];
	}
	/**
	 * Method to return the number of rows
	 * @return the number of rows
	 * */
	public int getRCount()
	{
		return this.rownames.length;
	}
	/**
	 * Method to return the number of columns
	 * @return the number of columns
	 * */
	public int getCCount()
	{
		return this.colnames.length;
	}
	/**
	 * Method to return the column name of a certain column
	 * @param i the column index
	 * @return the column name
	 * */
	public String getCName(int i)
	{
		return this.colnames[i];
	}
	/**
	 * Method to return the dataframe as a String
	 * @return the dataframe as a String
	 * */
	public String toString()
	{
		String s="	";
		for(int i=0;i<colnames.length;i++)
			s+=colnames[i]+"	";
		s+="\n";
		for(int i=0;i<mat.length;i++)
		{
			s+=rownames[i]+"	";
			for(int j=0;j<mat[0].length;j++)
				s+=mat[i][j]+"	";
			s+="\n";
		}
		return s;
	}
	/**
	 * Method to convert the dataframe as a transaction list
	 * @return a list of lists where each list will contain the item ids
	 * */
	public List<List<Long>> convertToTr()
	{
		List<List<Long>> tran=new ArrayList<List<Long>>();
		for(int i=0;i<mat.length;i++)
		{
			List<Long> temp=new ArrayList<Long>();
			for(int j=0;j<mat[0].length;j++)
				if(mat[i][j]==1)
					temp.add((long) (j+1));
			tran.add(temp);
		}
		return tran;
	}
}