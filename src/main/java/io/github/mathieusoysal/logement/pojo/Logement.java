package io.github.mathieusoysal.logement.pojo;

import java.util.List;

import io.github.mathieusoysal.logement.Address;
import io.github.mathieusoysal.logement.BedKind;
import io.github.mathieusoysal.logement.Equipement;

/**
 * Logement
 * 
 * @author MathieuSoysal
 * @version 1.0.0
 * @since 2020-12-01
 */
/**
 * Represents a housing unit.
 */
public class Logement {

    private int id;
    private String label;
    private Address address;
    private int bedCount;
    private BedKind bedKind;
    private int bedroomCount;
    private int roomCount;
    private boolean inUnavailabilityPeriod;
    private String description;
    private boolean available;
    private boolean highDemand;
    private boolean lowStock;
    private List<Equipement> equipements;
    private double areaMin;
    private double areaMax;
    private List<OccupationMod> occupationMods;

    /**
     * Constructs a new Logement object with the specified ID.
     *
     * @param id the ID of the Logement object
     */
    protected Logement(int id) {
        this.id = id;
    }

    /**
     * Returns the hash code value for this Logement object.
     *
     * @return the hash code value for this Logement object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Logement other = (Logement) obj;
        return id == other.id;
    }

    /**
     * Returns the ID of this Logement object.
     *
     * @return the ID of this Logement object
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the label of this Logement object.
     *
     * @return the label of this Logement object
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the address of this Logement object.
     *
     * @return the address of this Logement object
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the number of beds in this Logement object.
     *
     * @return the number of beds in this Logement object
     */
    public int getBedCount() {
        return bedCount;
    }

    /**
     * Returns the kind of bed in this Logement object.
     *
     * @return the kind of bed in this Logement object
     */
    public BedKind getBedKind() {
        return bedKind;
    }

    /**
     * Returns the number of bedrooms in this Logement object.
     *
     * @return the number of bedrooms in this Logement object
     */
    public int getBedroomCount() {
        return bedroomCount;
    }

    /**
     * Returns the number of rooms in this Logement object.
     *
     * @return the number of rooms in this Logement object
     */
    public int getRoomCount() {
        return roomCount;
    }

    /**
     * Returns whether this Logement object is in an unavailability period.
     *
     * @return true if this Logement object is in an unavailability period; false
     *         otherwise
     */
    public boolean isInUnavailabilityPeriod() {
        return inUnavailabilityPeriod;
    }

    /**
     * Returns the description of this Logement object.
     *
     * @return the description of this Logement object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this Logement object is available.
     *
     * @return true if this Logement object is available; false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Returns whether this Logement object is in high demand.
     *
     * @return true if this Logement object is in high demand; false otherwise
     */
    public boolean isHighDemand() {
        return highDemand;
    }

    /**
     * Returns whether this Logement object is in low stock.
     *
     * @return true if this Logement object is in low stock; false otherwise
     */
    public boolean isLowStock() {
        return lowStock;
    }

    /**
     * Returns the list of equipment in this Logement object.
     *
     * @return the list of equipment in this Logement object
     */
    public List<Equipement> getEquipements() {
        return equipements;
    }

    /**
     * Returns the minimum area of this Logement object.
     *
     * @return the minimum area of this Logement object
     */
    public double getAreaMin() {
        return areaMin;
    }

    /**
     * Returns the maximum area of this Logement object.
     *
     * @return the maximum area of this Logement object
     */
    public double getAreaMax() {
        return areaMax;
    }

    /**
     * Returns the list of occupation modifications for this Logement object.
     *
     * @return the list of occupation modifications for this Logement object
     */
    public List<OccupationMod> getOccupationMods() {
        return occupationMods;
    }

    void setId(int id) {
        this.id = id;
    }

    void setLabel(String label) {
        this.label = label;
    }

    void setAddress(Address address) {
        this.address = address;
    }

    void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    void setBedKind(BedKind bedKind) {
        this.bedKind = bedKind;
    }

    void setBedroomCount(int bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    void setInUnavailabilityPeriod(boolean inUnavailabilityPeriod) {
        this.inUnavailabilityPeriod = inUnavailabilityPeriod;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }

    void setHighDemand(boolean highDemand) {
        this.highDemand = highDemand;
    }

    void setLowStock(boolean lowStock) {
        this.lowStock = lowStock;
    }

    void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }

    void setAreaMin(double areaMin) {
        this.areaMin = areaMin;
    }

    void setAreaMax(double areaMax) {
        this.areaMax = areaMax;
    }

    void setOccupationMods(List<OccupationMod> occupationMods) {
        this.occupationMods = occupationMods;
    }

}