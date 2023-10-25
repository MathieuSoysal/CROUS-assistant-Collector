package io.github.mathieusoysal.logement;

/**
 * An enumeration representing the different types of beds available in a room.
 * 
 * @version 1.0
 * @author MathieuSoysal
 */
public enum BedKind {
    SIMPLE,
    DOUBLE,
    MOBILE,
    RAISED,
    UNKNOWN;

    /**
     * Returns the BedKind corresponding to the given string.
     * 
     * @param bedKind the string representation of the BedKind
     * @return the corresponding BedKind, or UNKNOWN if the string is null, blank or "null"
     * @throws IllegalArgumentException if the string does not correspond to any BedKind
     */
    public static BedKind fromString(String bedKind) {
        if (bedKind == null || bedKind.isBlank() || bedKind.equals("null")) 
            return UNKNOWN;
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
                throw new IllegalArgumentException("BedKind " + bedKind + " not found");
        }
    }

}
