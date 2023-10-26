package io.github.mathieusoysal.logement;

import java.util.List;

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
    private List<Equipement> equipements;
    private int areaMin;
    private int areaMax;
    private List<OccupationMod> occupationMods;

    Logement(int id) {
        this.id = id;
    }

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
        return id == other.id;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Address getAddress() {
        return address;
    }

    public int getBedCount() {
        return bedCount;
    }

    public BedKind getBedKind() {
        return bedKind;
    }

    public int getBedroomCount() {
        return bedroomCount;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public boolean isInUnavailabilityPeriod() {
        return inUnavailabilityPeriod;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isHighDemand() {
        return highDemand;
    }

    public boolean isLowStock() {
        return lowStock;
    }

    public List<Equipement> getEquipements() {
        return equipements;
    }

    public int getAreaMin() {
        return areaMin;
    }

    public int getAreaMax() {
        return areaMax;
    }

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

     void setAreaMin(int areaMin) {
        this.areaMin = areaMin;
    }

     void setAreaMax(int areaMax) {
        this.areaMax = areaMax;
    }

     void setOccupationMods(List<OccupationMod> occupationMods) {
        this.occupationMods = occupationMods;
    }

}