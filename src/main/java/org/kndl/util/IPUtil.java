package org.kndl.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * Helper class with methods that do IP address math.
 *
 * @author skendall
 *
 */
public final class IPUtil {

    /**
     * Takes an integer representation of an IP address and
     * converts it to an Inet4Address object.
     *
     * @param ipInt
     * @return
     */

    public final static Inet4Address intToIP(int ipInt) {
        if(ipInt == 0)
            return null;
        byte[] bytes = ByteUtil.intToByte(ipInt);
        try {
            Inet4Address address = (Inet4Address)InetAddress.getByAddress(bytes);
            return address;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes an Inet4Address object and converts it to an
     * integer.
     *
     * @param ip
     * @return
     */

    public final static int IPtoInt(Inet4Address ip) {
        if(ip == null)
            return 0;
        return ByteUtil.byteToInt(ip.getAddress(),0);
    }
}
