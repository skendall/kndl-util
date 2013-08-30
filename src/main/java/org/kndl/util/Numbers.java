package org.kndl.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple object to generate numbers based on certain
 * sets of criteria.
 * 
 * @author skendall
 *
 */

public class Numbers {
	
	/**
	 * Used for the rolling integer index
	 */
	
	private static final AtomicInteger				IDX_ID = new AtomicInteger();	
	
	/**
	 * Generates a unique ID based on the following algorithm.
	 * 
	 * The top 56 bits are generated from the current time
	 * in milliseconds (epoch).  The last 8 bits are generated 
	 * by a rolling integer index.  
	 * 
	 * This ensures a unique ID until a time when Java may have
	 * long since expired.
	 * 
	 * @return
	 */
	
	public final long nextUnique() {
		int idx = 0;
		if(IDX_ID.get() >= 256)
			IDX_ID.set(0);
		else
			idx = IDX_ID.getAndIncrement();
		long id = System.currentTimeMillis();
		id = (id << 8) | idx;		
		return id;
	}

}
