package org.kndl.util.test;

import org.junit.Test;
import org.kndl.util.IDUtil;

import static junit.framework.Assert.assertTrue;

public class IDUtilTest {

    @Test
    public void shortenTest() {
        IDUtil util = new IDUtil();
        String s1 = "http://www.cnn.com";
        assertTrue(util.shorten(s1).equals("n_y2Fd"));
    }

    @Test
    public void uniqueIdTest() {
        IDUtil util = new IDUtil();
        assertTrue(util.nextUnique() != util.nextUnique());
    }

}
