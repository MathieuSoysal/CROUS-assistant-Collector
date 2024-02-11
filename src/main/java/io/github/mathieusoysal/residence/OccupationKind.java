package io.github.mathieusoysal.residence;

/**
 * An enumeration representing the different kinds of occupation for a housing
 * unit.
 */
public enum OccupationKind {

    /**
     * A housing unit occupied by a single person
     */
    ALONE,

    /**
     * A housing unit occupied by a couple with one space
     */
    COUPLE_ONE_SPACE,

    /**
     * A housing unit occupied by a couple with two spaces
     */
    COUPLE_TWO_SPACES,

    /**
     * A housing unit occupied by several people
     */
    HOUSE_SHARING,

    /**
     * No occupation kind
     */
    NONE,

    /**
     * Unknown occupation kind
     */
    UNKNOWN;

    /**
     * 
     * Returns the OccupationKind corresponding to the given string representation.
     * 
     * @param occupationKind the string representation of the OccupationKind to
     *                       retrieve
     * @return the corresponding OccupationKind, or NONE if the given string is
     *         null, blank, or "null"
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
