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
        BitField f = new BitField(8);
        f.set(0,1);
        f.set(1,2);
        f.set(2,3);
        f.set(3,4);
        f.set(4,5);
        f.set(5,6);
        f.set(6,7);
        f.set(7,7);
        assertTrue(f.get(0) == 1);
        assertTrue(f.get(1) == 2);
        assertTrue(f.get(2) == 3);
        assertTrue(f.get(3) == 4);
        assertTrue(f.get(4) == 5);
        assertTrue(f.get(5) == 6);
        assertTrue(f.get(6) == 7);
        assertTrue(f.get(7) == 7);
        f.clear();
        assertTrue(f.toLong() == 0);
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

    @Test
    public void sl() {
        BitField f = new BitField(4);
        f.set(0,10);
        f.sl();
        assertTrue(f.get(1) == 10);
    }

    @Test
    public void sr() {
        BitField f = new BitField(4);
        f.set(1,10);
        f.sr();
        assertTrue(f.get(0) == 10);
    }

    @Test
    public void mask() {
        BitField f = new BitField(4);
        f = f.set(0,9).mask(4,1);
        assertTrue(f.get(0) == 8);
    }
}
