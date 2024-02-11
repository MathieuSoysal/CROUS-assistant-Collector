package io.github.mathieusoysal.residence;

/**
 * This enum represents the different types of transportation available near a
 * housing unit.
 * It also provides a method to convert a String representation of a transport
 * kind to its corresponding enum value.
 */
public enum TransportKind {

    /**
     * A paying urban parking
     */
    PAYING_URBAN_PARKING,

    /**
     * A free urban parking
     */
    FREE_URBAN_PARKING,

    /**
     * A bus
     */
    BUS,

    /**
     * A tramway
     */
    TRAMWAY,

    /**
     * A private parking
     */
    PRIVATE_PARKING,

    /**
     * A train
     */
    TRAIN,

    /**
     * A bike
     */
    BIKE,

    /**
     * A RER
     */
    RER,

    /**
     * A metro
     */
    METRO,

    /**
     * No transport kind
     */
    NONE,

    /**
     * Unknown transport kind
     */
    UNKNOWN;

    /**
     * Converts a String representation of a transport kind to its corresponding
     * enum value.
     * 
     * @param transportKind the String representation of the transport kind
     * @return the corresponding TransportKind enum value
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
            case "RER":
                return RER;
            case "Métro":
                return METRO;
            default:
                return UNKNOWN;
        }
    }

}
