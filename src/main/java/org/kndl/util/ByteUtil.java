package org.kndl.util;

/**
 *
 * Byte manipulation for working with byte arrays.  All byte
 * manipulation utilitizes big-ending byte codec's.
 *
 * @author skendall
 *
 */
public class ByteUtil {

    /**
     * Convert a char primitive to a byte array.
     * 
     * @param c
     * @return
     */
    
    public static byte[] charToByte(char c) {
    	byte[] bytes = new byte[2];
    	
    	bytes[0] = (byte) (c >> 8);
    	bytes[1] = (byte) c;
    	
    	return bytes;
    }
	
	/**
	 * Convert an integer primitive to a byte array.
	 * 
	 * @param i
	 * @return
	 */
	
    public static byte[] intToByte(int i) {
        byte[] bytes = new byte[4];

        bytes[0] = (byte) (i >> 24);
        bytes[1] = (byte) (i >> 16);
        bytes[2] = (byte) (i >> 8);
        bytes[3] = (byte) i;

        return bytes;
    }

    /**
     * Convert a long primitive to a byte array.
     * 
     * @param l
     * @return
     */
    
    public static byte[] longToByte(long l) {
        byte[] bytes = new byte[8];
        
        bytes[0] = (byte) (l >> 56);
        bytes[1] = (byte) (l >> 48);
        bytes[2] = (byte) (l >> 40);
        bytes[3] = (byte) (l >> 32);
        bytes[4] = (byte) (l >> 24);
        bytes[5] = (byte) (l >> 16);
        bytes[6] = (byte) (l >> 8);
        bytes[7] = (byte) l;
        
        return bytes;
    }
    
    
    
    public static char byteToChar(byte[] bytes, int startIdx) {
    	char c = Character.forDigit((bytes[startIdx] << 8) | (bytes[startIdx+1]),10);
    	return c;
    }
    
    public static int byteToInt(byte[] bytes, int startIdx) {
    	return 0;
    }

    public static long byteToLong(byte[] bytes) {
    	return 0;
    }
    
    /**
     * Shift bytes in the array to the left by len.
     * 
     * @param bytes
     * @param len
     * @return
     */
    
    public static byte[] leftShift(byte[] bytes, int len) {
        if(bytes == null || bytes.length <= len)
            return new byte[bytes.length];
        int idx = 0;
        for(int i=len;i<bytes.length;i++)
            bytes[idx++] = bytes[i];
        return bytes;
    }

    /**
     * Shift bytes in the array to the right by len.
     * 
     * @param bytes
     * @param len
     * @return
     */
    
    public static byte[] rightShift(byte[] bytes, int len) {
        if(bytes == null || bytes.length <= len)
            return new byte[bytes.length];
        int idx = 0;
        for(int i=0;i<len;i++)
            bytes[i] = bytes[i+len];
        return bytes;
    }
    
    /**
     * Prints out a pretty hex version of a byte array.  The
     * length of the line of text is specified based on the
     * lineLen.
     *  
     * @param bytes
     * @param lineLen
     */

	public static void bytePrint(byte[] bytes, int lineLen) {

		if (bytes.length < lineLen) {
			
			for (int i = 0; i < bytes.length; i++) {
				String s = Integer.toHexString(bytes[i] & 0x00ff);
				if (s.length() < 2)
					s = "0" + s;
				System.out.print(s + " ");
			}
			
			System.out.println();
			
		} else {
			
			int count = 0;
			
			while (count < bytes.length) {
				System.out.println("count = " + count);
				for (int i = count; i < count+lineLen; i++) {
					String s = Integer.toHexString(bytes[i] & 0x00ff);
					if (s.length() < 2)
						s = "0" + s;
					System.out.print(s + " ");
				}
				count += lineLen;
				System.out.println();
			}
			
		}
		
	}
	
	/**
	 * Conveniently print bytes in 8 columns.
	 * 
	 * @param bytes
	 */
	
	public static void bytePrint(byte[] bytes) {
		bytePrint(bytes,8);
	}

    public static void main(String args[]) {
        bytePrint(longToByte(Long.MAX_VALUE), 4);
        bytePrint(intToByte(Integer.MAX_VALUE), 4);
        bytePrint(charToByte('A'), 4);
    }
}
