package helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithm.CellBiClust;

/** 
 * This is responsible for maintaning a separate ThreadPools for each Cell in the CLA. It provides a certain degree of parallelism to our algorithm
 * @author Pritam Sil
 */
public class ThreadChannel
{
	Map<Long,ExecutorService> mapping=new HashMap<Long,ExecutorService>();
	/**
	 * Constructor
	 * @param mapping a set containing all the cell types of CLAMap.
	 * @see CellBiClust 
	 */
	public ThreadChannel(Set<Long> mapping)
	{
		for(Long i:mapping)
			this.mapping.put(i,Executors.newFixedThreadPool(1));
	}
	
	/**
	 * Method to add a Thread to its corresponding ThreadPool
	 * @param type the type of the ThreadPool to which the thread is to be added
	 * @param ob the Thread object
	 */
	public void add(long type,Thread ob)
	{
		mapping.get(type).execute(ob);
	}
	
	/**
	 * Method to shutdown all the ThreadPools
	 */
	public void shutdown()
	{
		for(ExecutorService i:mapping.values())
			i.shutdown();
		for(ExecutorService i:mapping.values())
			while (!i.isTerminated());
	}
}
