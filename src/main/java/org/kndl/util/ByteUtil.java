package org.kndl.util;

/**
 *
 * Byte manipulation for working with byte arrays.
 *
 * @author skendall
 *
 */
public class ByteUtil {

    public static byte[] intToByte(int i) {
        byte[] bytes = new byte[4];

        bytes[0] = (byte) i;
        bytes[1] = (byte) (i >> 8);
        bytes[2] = (byte) (i >> 16);
        bytes[3] = (byte) (i >> 24);

        return bytes;
    }

    public static byte[] longToByte(long l) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (l >> 56);
        bytes[1] = (byte) (l >> 48);
        bytes[2] = (byte) (l >> 40);
        bytes[3] = (byte) (l >> 32);
        bytes[4] = (byte) (l >> 24);
        bytes[5] = (byte) (l >> 16);
        bytes[6] = (byte) (l >> 8);
        bytes[7] = (byte) (l >> 0);
        return bytes;
    }

    public static byte[] leftShift(byte[] bytes, int len) {
        if(bytes == null || bytes.length <= len)
            return new byte[bytes.length];
        int idx = 0;
        for(int i=len;i<bytes.length;i++)
            bytes[idx++] = bytes[i];
        return bytes;
    }

    public static byte[] rightShift(byte[] bytes, int len) {
        if(bytes == null || bytes.length <= len)
            return new byte[bytes.length];
        int idx = 0;
        for(int i=0;i<len;i++)
            bytes[i] = bytes[i+len];
        return bytes;
    }

    public static void bytePrint(byte[] bytes) {
        for(int i=0;i<bytes.length;i++)
            System.out.print(Integer.toHexString(bytes[i] & 0x00ff) + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        bytePrint(longToByte(Long.MAX_VALUE));
        bytePrint(intToByte(Integer.MAX_VALUE));
    }
}
