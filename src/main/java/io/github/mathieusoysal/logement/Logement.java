package io.github.mathieusoysal.logement;

import java.util.List;

import io.github.mathieusoysal.Utils;

/**
 * Logement
 * 
 * @author MathieuSoysal
 * @version 1.0.0
 * @since 2020-12-01
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
    private List<Equipment> equipements;
    private double areaMin;
    private double areaMax;
    private List<OccupationMode> occupationMods;
    private List<io.github.mathieusoysal.logement.Transport> transports;

    // Create constructor
    public Logement() {
    }

    /**
     * Constructs a new Logement object with the specified ID.
     *
     * @param id the ID of the Logement object
     */
    public Logement(int id) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Logement other = (Logement) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (areaMax != other.areaMax)
            return false;
        if (areaMin != other.areaMin)
            return false;
        if (available != other.available)
            return false;
        if (bedCount != other.bedCount)
            return false;
        if (bedKind != other.bedKind)
            return false;
        if (bedroomCount != other.bedroomCount)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (highDemand != other.highDemand)
            return false;
        if (id != other.id)
            return false;
        if (inUnavailabilityPeriod != other.inUnavailabilityPeriod)
            return false;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (lowStock != other.lowStock)
            return false;
        if (!Utils.listEqualsIgnoreOrder(occupationMods, other.occupationMods))
            return false;
        if (roomCount != other.roomCount)
            return false;
        return Utils.listEqualsIgnoreOrder(transports, other.transports);
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
    public List<Equipment> getEquipements() {
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
    public List<OccupationMode> getOccupationMods() {
        return occupationMods;
    }

    /**
     * Returns the list of transports available for this logement.
     *
     * @return the list of transports available for this logement
     */
    public List<io.github.mathieusoysal.logement.Transport> getTransports() {
        return transports;
    }

    @Override
    public String toString() {
        return "Logement [id=" + id + ", label=" + label + ", address=" + address + ", bedCount=" + bedCount
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
        this.occupationMods = occupationMods;
    }

    public void setTransports(List<io.github.mathieusoysal.logement.Transport> transports) {
        this.transports = transports;
    }

}