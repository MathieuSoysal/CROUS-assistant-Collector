package io.github.mathieusoysal.logement;

/**
 * This enum represents the unit of measure for transportation.
 */
public enum TransportUnitOfMeasure {
    METRE,
    NONE,
    ON_FOOT;

    /**
     * Returns the TransportUnitOfMeasure enum value corresponding to the given string.
     * @param transportUnitOfMeasure the string representation of the TransportUnitOfMeasure
     * @return the TransportUnitOfMeasure enum value corresponding to the given string
     * @throws IllegalArgumentException if the given string does not correspond to any TransportUnitOfMeasure enum value
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
                throw new IllegalArgumentException("TransportUnitOfMeasure " + transportUnitOfMeasure + " not found");
        }
    }
}
