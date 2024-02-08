package io.github.mathieusoysal.residence;

/**
 * The Equipement enum represents the different types of equipment that can be
 * present in a housing unit.
 * 
 * @author MathieuSoysal
 */
public enum Equipment {
    /**
     * A toilet
     */
    WC,

    /**
     * A refrigerator
     */
    FRIDGE,

    /**
     * A shower
     */
    SHOWER,

    /**
     * A sink and a cooking hob
     */
    SINK_AND_HOB,

    /**
     * A living room
     */
    LIVING_ROOM,

    /**
     * A balcony
     */
    BALCONY,

    /**
     * A microwave
     */
    MICROWAVE,

    /**
     * A duplex apartment
     */
    DUPLEX,

    /**
     * No equipment
     */
    NONE,

    /**
     * Unknown equipment
     */
    UNKNOWN;

    /**
     * Returns the Equipement enum value corresponding to the given string.
     *
     * @param equipement the string representation of the equipment
     * @return the Equipement enum value corresponding to the given string
     */
    public static Equipment fromString(String equipement) {
        if (equipement == null || equipement.isBlank() || equipement.equals("null"))
            return NONE;
        switch (equipement) {
            case "WC":
                return WC;
            case "Frigo":
                return FRIDGE;
            case "Douche":
                return SHOWER;
            case "Evier + Plaque":
                return SINK_AND_HOB;
            case "Pièce à vivre":
                return LIVING_ROOM;
            case "Balcon":
                return BALCONY;
            case "Micro-onde":
                return MICROWAVE;
            case "Duplex":
                return DUPLEX;
            default:
                return UNKNOWN;
        }
    }
}
