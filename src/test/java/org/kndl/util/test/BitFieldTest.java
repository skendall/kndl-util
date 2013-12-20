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
    public void setFieldAndClear() {
        BitField f = new BitField();
        f.set(0,1);
        assertTrue(f.get(0) == 1);
        assertTrue(f.toLong() == 1);
        f.clear();
        assertTrue(f.toLong() == 0);
    }

    @Test
    public void setMultipleFields() {
        BitField f = new BitField();
        f.set(0, 1);
        f.set(1, 2);
        assertTrue(f.get(0) == 1);
        assertTrue(f.get(1) == 2);
    }

    @Test
    public void resetFieldSize() {
        BitField f = new BitField();
        f.set(0,1);
        f.set(2,10);
        assertTrue(f.get(0) == 1);
        assertTrue(f.get(2) == 10);
        f.setFieldSize(8);
        f.set(0,1);
        f.set(2,10);
        assertTrue(f.get(0) == 1);
        assertTrue(f.get(2) == 10);
    }
}
