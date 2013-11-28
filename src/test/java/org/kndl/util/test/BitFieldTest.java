package org.kndl.util.test;

import org.junit.Test;
import org.kndl.util.BitField;

import static org.junit.Assert.assertTrue;

/**
 *
 *
 *
 */
public class BitFieldTest {

    @Test
    public void setField() {
        BitField f = new BitField();
        f.set(0,1);
        assertTrue(f.toLong() == 1);
    }
}
