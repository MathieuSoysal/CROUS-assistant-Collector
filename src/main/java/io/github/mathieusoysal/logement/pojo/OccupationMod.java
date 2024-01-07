package io.github.mathieusoysal.logement.pojo;

import io.github.mathieusoysal.logement.OccupationKind;

/**
 * This class represents a modification of the occupation of a housing unit.
 */
public class OccupationMod {
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
    

    public OccupationMod() {
    }

    /**
     * Constructs a new OccupationMod object with the specified occupation kind, minimum rent, and maximum rent.
     * 
     * @param occupationKind the new kind of occupation
     * @param rentMin the new minimum rent
     * @param rentMax the new maximum rent
     */
    OccupationMod(OccupationKind occupationKind, int rentMin, int rentMax) {
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
}
