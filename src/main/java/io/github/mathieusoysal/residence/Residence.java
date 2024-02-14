package io.github.mathieusoysal.residence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Residence
 * 
 * @author MathieuSoysal
 * @version 1.0.0
 * @since 2020-12-01
 */
public class Residence {

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
    private List<Equipment> equipements;
    private double areaMin;
    private double areaMax;
    private SortedSet<OccupationMode> occupationMods;
    private List<io.github.mathieusoysal.residence.Transport> transports;

    // Create constructor
    public Residence() {
    }

    /**
     * Constructs a new Residence object with the specified ID.
     *
     * @param id the ID of the Residence object
     */
    public Residence(int id) {
        this.id = id;
    }

    /**
     * Returns the hash code value for this Residence object.
     *
     * @return the hash code value for this Residence object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Residence))
            return false;
        Residence other = (Residence) obj;
        return id == other.id;
    }

    /**
     * Adds the specified Residence object to this Residence object.
     *
     * @param residence the Residence object to be added
     */
    public void addResidence(Residence residence) {
        if (residence == null)
            return;
        label = residence.getLabel();
        address = residence.getAddress();
        bedCount = residence.getBedCount();
        bedKind = residence.getBedKind();
        bedroomCount = residence.getBedroomCount();
        roomCount = residence.getRoomCount();
        inUnavailabilityPeriod = residence.isInUnavailabilityPeriod();
        description = residence.getDescription();
        available = residence.isAvailable();
        highDemand = residence.isHighDemand();
        lowStock = residence.isLowStock();
        equipements = residence.getEquipements();
        areaMin = residence.getAreaMin();
        areaMax = residence.getAreaMax();
        occupationMods.addAll(residence.occupationMods);
        transports = residence.getTransports();
    }

    /**
     * Returns the ID of this Residence object.
     *
     * @return the ID of this Residence object
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the label of this Residence object.
     *
     * @return the label of this Residence object
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the address of this Residence object.
     *
     * @return the address of this Residence object
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the number of beds in this Residence object.
     *
     * @return the number of beds in this Residence object
     */
    public int getBedCount() {
        return bedCount;
    }

    /**
     * Returns the kind of bed in this Residence object.
     *
     * @return the kind of bed in this Residence object
     */
    public BedKind getBedKind() {
        return bedKind;
    }

    /**
     * Returns the number of bedrooms in this Residence object.
     *
     * @return the number of bedrooms in this Residence object
     */
    public int getBedroomCount() {
        return bedroomCount;
    }

    /**
     * Returns the number of rooms in this Residence object.
     *
     * @return the number of rooms in this Residence object
     */
    public int getRoomCount() {
        return roomCount;
    }

    /**
     * Returns whether this Residence object is in an unavailability period.
     *
     * @return true if this Residence object is in an unavailability period; false
     *         otherwise
     */
    public boolean isInUnavailabilityPeriod() {
        return inUnavailabilityPeriod;
    }

    /**
     * Returns the description of this Residence object.
     *
     * @return the description of this Residence object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this Residence object is available.
     *
     * @return true if this Residence object is available; false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Returns whether this Residence object is in high demand.
     *
     * @return true if this Residence object is in high demand; false otherwise
     */
    public boolean isHighDemand() {
        return highDemand;
    }

    /**
     * Returns whether this Residence object is in low stock.
     *
     * @return true if this Residence object is in low stock; false otherwise
     */
    public boolean isLowStock() {
        return lowStock;
    }

    /**
     * Returns the list of equipment in this Residence object.
     *
     * @return the list of equipment in this Residence object
     */
    public List<Equipment> getEquipements() {
        return equipements;
    }

    /**
     * Returns the minimum area of this Residence object.
     *
     * @return the minimum area of this Residence object
     */
    public double getAreaMin() {
        return areaMin;
    }

    /**
     * Returns the maximum area of this Residence object.
     *
     * @return the maximum area of this Residence object
     */
    public double getAreaMax() {
        return areaMax;
    }

    /**
     * Returns the list of occupation modifications for this Residence object.
     *
     * @return the list of occupation modifications for this Residence object
     */
    public List<OccupationMode> getOccupationMods() {
        var result = new ArrayList<>(occupationMods);
        Collections.sort(result, (o1, o2) -> o1.hashCode() - o2.hashCode());
        return result;
    }

    /**
     * Returns the list of transports available for this residence.
     *
     * @return the list of transports available for this residence
     */
    public List<io.github.mathieusoysal.residence.Transport> getTransports() {
        return transports;
    }

    @Override
    public String toString() {
        return "Residence [id=" + id + ", label=" + label + ", address=" + address + ", bedCount=" + bedCount
                + ", bedKind=" + bedKind + ", bedroomCount=" + bedroomCount + ", roomCount=" + roomCount
                + ", inUnavailabilityPeriod=" + inUnavailabilityPeriod + ", description=" + description + ", available="
                + available + ", highDemand=" + highDemand + ", lowStock=" + lowStock + ", equipements=" + equipements
                + ", areaMin=" + areaMin + ", areaMax=" + areaMax + ", occupationMods=" + occupationMods
                + ", transports=" + transports + "]";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public void setBedKind(BedKind bedKind) {
        this.bedKind = bedKind;
    }

    public void setBedroomCount(int bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public void setInUnavailabilityPeriod(boolean inUnavailabilityPeriod) {
        this.inUnavailabilityPeriod = inUnavailabilityPeriod;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setHighDemand(boolean highDemand) {
        this.highDemand = highDemand;
    }

    public void setLowStock(boolean lowStock) {
        this.lowStock = lowStock;
    }

    public void setEquipements(List<Equipment> equipements) {
        this.equipements = equipements;
    }

    public void setAreaMin(double areaMin) {
        this.areaMin = areaMin;
    }

    public void setAreaMax(double areaMax) {
        this.areaMax = areaMax;
    }

    public void setOccupationMods(List<OccupationMode> occupationMods) {
        this.occupationMods = new TreeSet<>(occupationMods);
    }

    public void setTransports(List<io.github.mathieusoysal.residence.Transport> transports) {
        this.transports = transports;
    }

}