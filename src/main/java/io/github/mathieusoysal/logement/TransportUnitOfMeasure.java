package io.github.mathieusoysal.logement;

/**
 * This enum represents the unit of measure for transportation.
 */
public enum TransportUnitOfMeasure {

    /**
     * The distance is measured in metres.
     */
    METRE,

    /**
     * The distance is measured on foot (time in minutes).
     */
    ON_FOOT,

    /**
     * The distance is not measured.
     */
    NONE,

    /**
     * The unit of measure is unknown.
     */
    UNKNOWN;

    /**
     * Returns the TransportUnitOfMeasure enum value corresponding to the given
     * string.
     * 
     * @param transportUnitOfMeasure the string representation of the
     *                               TransportUnitOfMeasure
     * @return the TransportUnitOfMeasure enum value corresponding to the given
     *         string
     */
    public static TransportUnitOfMeasure fromString(String transportUnitOfMeasure) {
        if (transportUnitOfMeasure == null || transportUnitOfMeasure.isBlank() || transportUnitOfMeasure.equals("null"))
            return NONE;
        switch (transportUnitOfMeasure) {
            case "metre":
                return METRE;
            case "on_foot":
                return ON_FOOT;
            default:
                return UNKNOWN;
        }
    }
}
