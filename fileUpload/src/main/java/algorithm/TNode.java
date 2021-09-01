package algorithm;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/** 
 * 
 * This is used for representing a Node in the FPTree
 * @author Pritam Sil
 */
public class TNode
{
	long type;//the item 
	List<Long> val;//a list of tids
	public List<TNode> child;//a list to store all the children of this node
	
	/**
	 * Constructor
	 * @param ch the type of the Node
	 */
	public TNode(long ch)
	{
		val=new LinkedList<Long>();
		this.type=ch;
		child=new LinkedList<TNode>();
	}
	
	/**
	 * Method to convert the TNode Object to a String
	 * @return the String format of the TNode Object
	 */
	public String toString()
	{
		return "<"+type+" "+Arrays.toString(val.toArray())+">";
	}
}
