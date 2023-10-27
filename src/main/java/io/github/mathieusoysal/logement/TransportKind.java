package io.github.mathieusoysal.logement;

/**
 * This enum represents the different types of transportation available.
 * It also provides a method to convert a String representation of a transport
 * kind to its corresponding enum value.
 */
public enum TransportKind {
    PAYING_URBAN_PARKING,
    FREE_URBAN_PARKING,
    BUS,
    TRAMWAY,
    PRIVATE_PARKING,
    TRAIN,
    BIKE,
    RER,
    METRO,
    NONE,
    UNKNOWN;

    /**
     * Converts a String representation of a transport kind to its corresponding
     * enum value.
     * 
     * @param transportKind the String representation of the transport kind
     * @return the corresponding TransportKind enum value
     * @throws IllegalArgumentException if the transport kind is not found
     */
    public static TransportKind fromString(String transportKind) {
        if (transportKind == null || transportKind.isBlank() || transportKind.equals("null"))
            return NONE;
        switch (transportKind) {
            case "Stationnement urbain payant":
                return PAYING_URBAN_PARKING;
            case "Stationnement urbain gratuit":
                return FREE_URBAN_PARKING;
            case "Bus":
                return BUS;
            case "Tramway":
                return TRAMWAY;
            case "Parking privatif":
                return PRIVATE_PARKING;
            case "Train":
                return TRAIN;
            case "Vélo":
                return BIKE;
            case "":
                return NONE;
            case "RER":
                return RER;
            case "Métro":
                return METRO;
            default:
                throw new IllegalArgumentException("TransportKind " + transportKind + " not found");
        }
    }

}
