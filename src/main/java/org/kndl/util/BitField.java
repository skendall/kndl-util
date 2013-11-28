package org.kndl.util;

/**
 *
 *
 *
 */
public final class BitField {

    private long field;         // bit field

    private int fieldSize;      // size of the field values in bits

    public BitField(long initialNum, int fieldSize) {
        this.field = initialNum;
        this.fieldSize = fieldSize;
    }

    public BitField(int fieldSize) {
        this(0,fieldSize);
    }

    public BitField() {
        this(0,4);
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
        this.field = (this.field & ~(fieldSize << (fieldPosition * fieldSize))) | (value << (fieldPosition * fieldSize));
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
     * Shift the bitfield to the right by the number of bits specified by numBits.
     *
     * @param numBits
     * @return
     */

    public final BitField sr(int numBits) {
        this.field = this.field >> numBits;
        return this;
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
        return this;
    }

    // accessors

    public final long get(int fieldPosition) {
        return ((field >>> (fieldPosition * fieldSize)) & fieldSize);
    }

    public final long toLong() {
        return field;
    }

}
