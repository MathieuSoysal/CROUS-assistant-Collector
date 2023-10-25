package io.github.mathieusoysal.logement;

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

    public OccupationKind getOccupationKind() {
        return occupationKind;
    }

    public int getRentMin() {
        return rentMin;
    }

    public int getRentMax() {
        return rentMax;
    }

    OccupationMod(OccupationKind occupationKind, int rentMin, int rentMax) {
        this.occupationKind = occupationKind;
        this.rentMin = rentMin;
        this.rentMax = rentMax;
    }

}
