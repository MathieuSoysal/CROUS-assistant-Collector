package io.github.mathieusoysal.logement;

/**
 * This class represents a modification of the occupation of a housing unit.
 */
public class OccupationMode {
    /**
     * The new kind of occupation.
     */
    private OccupationKind occupationKind;

    /**
     * The new minimum rent.
     */
    private int rentMin;

    /**
     * The new maximum rent.
     */
    private int rentMax;
    

    public OccupationMode() {
    }

    /**
     * Constructs a new OccupationMod object with the specified occupation kind, minimum rent, and maximum rent.
     * 
     * @param occupationKind the new kind of occupation
     * @param rentMin the new minimum rent
     * @param rentMax the new maximum rent
     */
    public OccupationMode(OccupationKind occupationKind, int rentMin, int rentMax) {
        this.occupationKind = occupationKind;
        this.rentMin = rentMin;
        this.rentMax = rentMax;
    }

    /**
     * Returns the new kind of occupation.
     * 
     * @return the new kind of occupation
     */
    public OccupationKind getOccupationKind() {
        return occupationKind;
    }

    /**
     * Returns the new minimum rent.
     * 
     * @return the new minimum rent
     */
    public int getRentMin() {
        return rentMin;
    }

    /**
     * Returns the new maximum rent.
     * 
     * @return the new maximum rent
     */
    public int getRentMax() {
        return rentMax;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((occupationKind == null) ? 0 : occupationKind.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OccupationMode other = (OccupationMode) obj;
        return occupationKind == other.occupationKind;
    }


}
