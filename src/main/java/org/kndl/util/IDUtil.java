package org.kndl.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple object to generate numbers based on certain
 * sets of criteria.
 * 
 * @author skendall
 *
 */

public class IDUtil {
	
	/**
	 * Used for the rolling integer index
	 */
	
	private static final AtomicInteger				IDX_ID = new AtomicInteger();

    /**
     * Character map used in generating base-64 hash
     */

    private static final char[] CHAR_MAP= new char[] {
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '0','1','2','3','4','5','6','7','8','9','-','_'};

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
		long id = System.currentTimeMillis();
		id = (id << 8) | IDX_ID.getAndIncrement();
		return id;
    }

    /**
     * Generates a unique base-62 hash string based on a string
     * passed to the method.
     *
     * @param str
     * @return
     */

    public final String shorten(String str) {
        if(str == null || str.isEmpty())
            return "";

        StringBuilder hash = new StringBuilder();

        int dividend = str.hashCode();
        int remainder = 0;

        while(dividend != 0) {
            remainder = dividend & 63;
            dividend = dividend >>> 6;
            hash.append(CHAR_MAP[remainder]);
        }

        return hash.toString();
    }


}
