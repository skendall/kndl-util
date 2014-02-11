package org.kndl.util;

/**
 * Convenience object that provides a way to create a bit field
 * based on a specified field size (defaults to 4 bits) and an
 * initial value.  The field can be accessed and mutated in a
 * variety of different ways easily and has the ability to
 * "serialize/deserialize" easily as well.
 *
 * Bit field size is limited to 64 bits (the max size of a long).
 *
 * @author skendall
 *
 */
public final class BitField {

    /**
     * The backing field store
     */

    private long field;         // bit field

    /**
     * The size of each field; the fields must be all the same
     * size.  Additionally, making the field size non-divisible
     * by 64 has potentially catastrophic consequences.
     */

    private int[] fieldSize;      // size of the field values in bits

    private int curPos = 0;

    public BitField(int[] fieldSizes) {
        this.field = 0;
        int totalSize = 0;
        for(int i=0;i<fieldSizes.length;i++) {
            totalSize += fieldSizes[i];
        }
        if(totalSize > 64)
            throw new RuntimeException("Configured field sizes are greater than 64-bits.");
        this.fieldSize = new int[fieldSizes.length+1];
        System.arraycopy(fieldSizes,0,fieldSize,0,fieldSizes.length);
        fieldSize[fieldSize.length-1] = (64 - totalSize);
    }

    public BitField(long initialNum, int fieldSize) {
        this.field = initialNum;
        this.fieldSize = new int[64/fieldSize];
        for(int i=0;i<this.fieldSize.length;i++)
            this.fieldSize[i] = fieldSize;
    }

    public BitField(int fieldSize) {
        this(0,fieldSize);
    }

    public BitField() {
        this(4);
    }

    // mutators

    /**
     * Set the fieldPosition in the bit field to value.
     *
     * @param fieldPosition
     * @param value
     * @return
     */

    public final BitField set(int fieldPosition, long value) {
        long tVal = value & (long)(Math.pow(2,fieldSize[fieldPosition]))-1;
        this.field = field | (tVal << getFieldOffset(fieldPosition));
        return this;
    }

    /**
     * Shift the bitfield to the left by the number of bits specified by numBits.
     *
     * @param numBits
     * @return
     */

    public final BitField sl(int numBits) {
        this.field = this.field << numBits;
        return this;
    }

    /**
     * Shift the bitfield to the left by fieldSize number of bits.
     *
     * @return
     */

    public final BitField sl() {
        return sl(fieldSize[curPos++]);
    }

    /**
     * Shift the bitfield to the right by the number of bits specified by numBits.
     *
     * @param numBits
     * @return
     */

    public final BitField sr(int numBits) {
        this.field = this.field >>> numBits;
        return this;
    }

    /**
     * Shift the bitfield to the right by fieldSize number of bits.
     *
     * @return
     */

    public final BitField sr() {
        return sr(fieldSize[curPos--]);
    }

    /**
     * Invert all bits in the bit field.
     *
     * @return
     */

    public final BitField invert() {
        this.field = ~this.field;
        return this;
    }

    /**
     * Mask all bits from the starting index in the field at startIdx to the ending index in the field at
     * endIdx.  This operation is big endian.
     *
     * @param startIdx
     * @param endIdx
     * @return
     */

    public final BitField mask(int startIdx, int endIdx) {
        long mask = (long)(Math.pow(2,startIdx) - Math.pow(2,endIdx));
        this.field = field & mask;
        return this;
    }

    /**
     * Clears the bitfield to all zeroes.
     *
     * @return
     */

    public final BitField clear() {
        this.field = 0;
        return this;
    }

    // accessors


    public final int getFieldSize(int fieldPosition) {
        return fieldSize[fieldPosition];
    }

    public final long get(int fieldPosition) {
        return (field >>> getFieldOffset(fieldPosition)) & (long)(Math.pow(2,fieldSize[fieldPosition]))-1;
    }

    public final long toLong() {
        return field;
    }

    public String toString() {
        return Long.toBinaryString(field);
    }

    private int getFieldOffset(int fieldPosition) {
        int offset = 0;
        for(int i=0;i<fieldPosition;i++)
            offset+=fieldSize[i];
        return offset;
    }

}
