package io.github.mathieusoysal.residence;

/**
 * An enumeration representing the different types of beds available in a room.
 * 
 * @version 1.0
 * @author MathieuSoysal
 */
public enum BedKind {

    /**
     * A simple bed.
     */
    SIMPLE,

    /**
     * A double bed.
     */
    DOUBLE,

    /**
     * A mobile bed.
     */
    MOBILE,

    /**
     * A raised bed.
     */
    RAISED,

    /**
     * No kind of bed.
     */
    NONE,

    /**
     * Unknown kind of bed.
     */
    UNKNOWN;

    /**
     * Returns the BedKind corresponding to the given string.
     * 
     * @param bedKind the string representation of the BedKind
     * @return the corresponding BedKind, or NONE if the string is null, blank or
     *         "null"
     */
    public static BedKind fromString(String bedKind) {
        if (bedKind == null || bedKind.isBlank() || bedKind.equals("null"))
            return NONE;
        switch (bedKind) {
            case "simple":
                return SIMPLE;
            case "double":
                return DOUBLE;
            case "mobile":
                return MOBILE;
            case "raised":
                return RAISED;
            default:
                return UNKNOWN;
        }
    }

}
