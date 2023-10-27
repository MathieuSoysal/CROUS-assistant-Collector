package io.github.mathieusoysal.logement;

/**
 * An enumeration representing the different kinds of occupation for a housing
 * unit.
 */
public enum OccupationKind {
    ALONE,
    COUPLE_ONE_SPACE,
    HOUSE_SHARING,
    COUPLE_TWO_SPACES,
    NONE,
    UNKNOWN;

    /**
     * Returns the OccupationKind corresponding to the given string representation.
     * 
     * @param occupationKind the string representation of the OccupationKind to
     *                       retrieve
     * @return the corresponding OccupationKind, or NONE if the given string is
     *         null, blank, or "null"
     * @throws IllegalArgumentException if the given string does not correspond to
     *                                  any OccupationKind
     */
    public static OccupationKind fromString(String occupationKind) {
        if (occupationKind == null || occupationKind.isBlank() || occupationKind.equals("null"))
            return NONE;
        switch (occupationKind) {
            case "alone":
                return ALONE;
            case "couple_one_space":
                return COUPLE_ONE_SPACE;
            case "house_sharing":
                return HOUSE_SHARING;
            case "couple_two_spaces":
                return COUPLE_TWO_SPACES;
            default:
                return UNKNOWN;
        }
    }
}
