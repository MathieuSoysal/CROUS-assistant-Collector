
package io.github.mathieusoysal.logement.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "medias",
        "area",
        "bedCount",
        "bedroomCount",
        "beds",
        "roomCount",
        "bookingData",
        "code",
        "occupationModes",
        "label",
        "reference",
        "studyLevel",
        "equipments",
        "residence",
        "inUnavailabilityPeriod",
        "unavailabilityMessage",
        "description",
        "specialConditions",
        "available",
        "highDemand",
        "lowStock"
})
@Generated("jsonschema2pojo")
class Item {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("medias")
    private List<Media> medias = new ArrayList<Media>();
    @JsonProperty("area")
    private Area area;
    @JsonProperty("bedCount")
    private Integer bedCount;
    @JsonProperty("bedroomCount")
    private Integer bedroomCount;
    @JsonProperty("beds")
    private List<Bed> beds = new ArrayList<Bed>();
    @JsonProperty("roomCount")
    private Integer roomCount;
    @JsonProperty("bookingData")
    private BookingData bookingData;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("occupationModes")
    private List<OccupationMode> occupationModes = new ArrayList<OccupationMode>();
    @JsonProperty("label")
    private String label;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("studyLevel")
    private StudyLevel studyLevel;
    @JsonProperty("equipments")
    private List<Equipment> equipments = new ArrayList<Equipment>();
    @JsonProperty("residence")
    private Residence residence;
    @JsonProperty("inUnavailabilityPeriod")
    private Boolean inUnavailabilityPeriod;
    @JsonProperty("unavailabilityMessage")
    private Object unavailabilityMessage;
    @JsonProperty("description")
    private String description;
    @JsonProperty("specialConditions")
    private Object specialConditions;
    @JsonProperty("available")
    private Boolean available;
    @JsonProperty("highDemand")
    private Boolean highDemand;
    @JsonProperty("lowStock")
    private Boolean lowStock;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Item withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("medias")
    public List<Media> getMedias() {
        return medias;
    }

    @JsonProperty("medias")
    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    public Item withMedias(List<Media> medias) {
        this.medias = medias;
        return this;
    }

    @JsonProperty("area")
    public Area getArea() {
        return area;
    }

    @JsonProperty("area")
    public void setArea(Area area) {
        this.area = area;
    }

    public Item withArea(Area area) {
        this.area = area;
        return this;
    }

    @JsonProperty("bedCount")
    public Integer getBedCount() {
        return bedCount;
    }

    @JsonProperty("bedCount")
    public void setBedCount(Integer bedCount) {
        this.bedCount = bedCount;
    }

    public Item withBedCount(Integer bedCount) {
        this.bedCount = bedCount;
        return this;
    }

    @JsonProperty("bedroomCount")
    public Integer getBedroomCount() {
        return bedroomCount;
    }

    @JsonProperty("bedroomCount")
    public void setBedroomCount(Integer bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public Item withBedroomCount(Integer bedroomCount) {
        this.bedroomCount = bedroomCount;
        return this;
    }

    @JsonProperty("beds")
    public List<Bed> getBeds() {
        return beds;
    }

    @JsonProperty("beds")
    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }

    public Item withBeds(List<Bed> beds) {
        this.beds = beds;
        return this;
    }

    @JsonProperty("roomCount")
    public Integer getRoomCount() {
        return roomCount;
    }

    @JsonProperty("roomCount")
    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Item withRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
        return this;
    }

    @JsonProperty("bookingData")
    public BookingData getBookingData() {
        return bookingData;
    }

    @JsonProperty("bookingData")
    public void setBookingData(BookingData bookingData) {
        this.bookingData = bookingData;
    }

    public Item withBookingData(BookingData bookingData) {
        this.bookingData = bookingData;
        return this;
    }

    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    public Item withCode(Integer code) {
        this.code = code;
        return this;
    }

    @JsonProperty("occupationModes")
    public List<OccupationMode> getOccupationModes() {
        return occupationModes;
    }

    @JsonProperty("occupationModes")
    public void setOccupationModes(List<OccupationMode> occupationModes) {
        this.occupationModes = occupationModes;
    }

    public Item withOccupationModes(List<OccupationMode> occupationModes) {
        this.occupationModes = occupationModes;
        return this;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public Item withLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    public Item withReference(String reference) {
        this.reference = reference;
        return this;
    }

    @JsonProperty("studyLevel")
    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    @JsonProperty("studyLevel")
    public void setStudyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
    }

    public Item withStudyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
        return this;
    }

    @JsonProperty("equipments")
    public List<Equipment> getEquipments() {
        return equipments;
    }

    @JsonProperty("equipments")
    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Item withEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
        return this;
    }

    @JsonProperty("residence")
    public Residence getResidence() {
        return residence;
    }

    @JsonProperty("residence")
    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public Item withResidence(Residence residence) {
        this.residence = residence;
        return this;
    }

    @JsonProperty("inUnavailabilityPeriod")
    public Boolean getInUnavailabilityPeriod() {
        return inUnavailabilityPeriod;
    }

    @JsonProperty("inUnavailabilityPeriod")
    public void setInUnavailabilityPeriod(Boolean inUnavailabilityPeriod) {
        this.inUnavailabilityPeriod = inUnavailabilityPeriod;
    }

    public Item withInUnavailabilityPeriod(Boolean inUnavailabilityPeriod) {
        this.inUnavailabilityPeriod = inUnavailabilityPeriod;
        return this;
    }

    @JsonProperty("unavailabilityMessage")
    public Object getUnavailabilityMessage() {
        return unavailabilityMessage;
    }

    @JsonProperty("unavailabilityMessage")
    public void setUnavailabilityMessage(Object unavailabilityMessage) {
        this.unavailabilityMessage = unavailabilityMessage;
    }

    public Item withUnavailabilityMessage(Object unavailabilityMessage) {
        this.unavailabilityMessage = unavailabilityMessage;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Item withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("specialConditions")
    public Object getSpecialConditions() {
        return specialConditions;
    }

    @JsonProperty("specialConditions")
    public void setSpecialConditions(Object specialConditions) {
        this.specialConditions = specialConditions;
    }

    public Item withSpecialConditions(Object specialConditions) {
        this.specialConditions = specialConditions;
        return this;
    }

    @JsonProperty("available")
    public Boolean getAvailable() {
        return available;
    }

    @JsonProperty("available")
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Item withAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @JsonProperty("highDemand")
    public Boolean getHighDemand() {
        return highDemand;
    }

    @JsonProperty("highDemand")
    public void setHighDemand(Boolean highDemand) {
        this.highDemand = highDemand;
    }

    public Item withHighDemand(Boolean highDemand) {
        this.highDemand = highDemand;
        return this;
    }

    @JsonProperty("lowStock")
    public Boolean getLowStock() {
        return lowStock;
    }

    @JsonProperty("lowStock")
    public void setLowStock(Boolean lowStock) {
        this.lowStock = lowStock;
    }

    public Item withLowStock(Boolean lowStock) {
        this.lowStock = lowStock;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Item withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Item.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("medias");
        sb.append('=');
        sb.append(((this.medias == null) ? "<null>" : this.medias));
        sb.append(',');
        sb.append("area");
        sb.append('=');
        sb.append(((this.area == null) ? "<null>" : this.area));
        sb.append(',');
        sb.append("bedCount");
        sb.append('=');
        sb.append(((this.bedCount == null) ? "<null>" : this.bedCount));
        sb.append(',');
        sb.append("bedroomCount");
        sb.append('=');
        sb.append(((this.bedroomCount == null) ? "<null>" : this.bedroomCount));
        sb.append(',');
        sb.append("beds");
        sb.append('=');
        sb.append(((this.beds == null) ? "<null>" : this.beds));
        sb.append(',');
        sb.append("roomCount");
        sb.append('=');
        sb.append(((this.roomCount == null) ? "<null>" : this.roomCount));
        sb.append(',');
        sb.append("bookingData");
        sb.append('=');
        sb.append(((this.bookingData == null) ? "<null>" : this.bookingData));
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null) ? "<null>" : this.code));
        sb.append(',');
        sb.append("occupationModes");
        sb.append('=');
        sb.append(((this.occupationModes == null) ? "<null>" : this.occupationModes));
        sb.append(',');
        sb.append("label");
        sb.append('=');
        sb.append(((this.label == null) ? "<null>" : this.label));
        sb.append(',');
        sb.append("reference");
        sb.append('=');
        sb.append(((this.reference == null) ? "<null>" : this.reference));
        sb.append(',');
        sb.append("studyLevel");
        sb.append('=');
        sb.append(((this.studyLevel == null) ? "<null>" : this.studyLevel));
        sb.append(',');
        sb.append("equipments");
        sb.append('=');
        sb.append(((this.equipments == null) ? "<null>" : this.equipments));
        sb.append(',');
        sb.append("residence");
        sb.append('=');
        sb.append(((this.residence == null) ? "<null>" : this.residence));
        sb.append(',');
        sb.append("inUnavailabilityPeriod");
        sb.append('=');
        sb.append(((this.inUnavailabilityPeriod == null) ? "<null>" : this.inUnavailabilityPeriod));
        sb.append(',');
        sb.append("unavailabilityMessage");
        sb.append('=');
        sb.append(((this.unavailabilityMessage == null) ? "<null>" : this.unavailabilityMessage));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        sb.append("specialConditions");
        sb.append('=');
        sb.append(((this.specialConditions == null) ? "<null>" : this.specialConditions));
        sb.append(',');
        sb.append("available");
        sb.append('=');
        sb.append(((this.available == null) ? "<null>" : this.available));
        sb.append(',');
        sb.append("highDemand");
        sb.append('=');
        sb.append(((this.highDemand == null) ? "<null>" : this.highDemand));
        sb.append(',');
        sb.append("lowStock");
        sb.append('=');
        sb.append(((this.lowStock == null) ? "<null>" : this.lowStock));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.area == null) ? 0 : this.area.hashCode()));
        result = ((result * 31) + ((this.roomCount == null) ? 0 : this.roomCount.hashCode()));
        result = ((result * 31) + ((this.code == null) ? 0 : this.code.hashCode()));
        result = ((result * 31) + ((this.specialConditions == null) ? 0 : this.specialConditions.hashCode()));
        result = ((result * 31) + ((this.available == null) ? 0 : this.available.hashCode()));
        result = ((result * 31) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((result * 31) + ((this.lowStock == null) ? 0 : this.lowStock.hashCode()));
        result = ((result * 31) + ((this.studyLevel == null) ? 0 : this.studyLevel.hashCode()));
        result = ((result * 31) + ((this.inUnavailabilityPeriod == null) ? 0 : this.inUnavailabilityPeriod.hashCode()));
        result = ((result * 31) + ((this.label == null) ? 0 : this.label.hashCode()));
        result = ((result * 31) + ((this.unavailabilityMessage == null) ? 0 : this.unavailabilityMessage.hashCode()));
        result = ((result * 31) + ((this.reference == null) ? 0 : this.reference.hashCode()));
        result = ((result * 31) + ((this.medias == null) ? 0 : this.medias.hashCode()));
        result = ((result * 31) + ((this.equipments == null) ? 0 : this.equipments.hashCode()));
        result = ((result * 31) + ((this.bedroomCount == null) ? 0 : this.bedroomCount.hashCode()));
        result = ((result * 31) + ((this.occupationModes == null) ? 0 : this.occupationModes.hashCode()));
        result = ((result * 31) + ((this.highDemand == null) ? 0 : this.highDemand.hashCode()));
        result = ((result * 31) + ((this.id == null) ? 0 : this.id.hashCode()));
        result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
        result = ((result * 31) + ((this.beds == null) ? 0 : this.beds.hashCode()));
        result = ((result * 31) + ((this.residence == null) ? 0 : this.residence.hashCode()));
        result = ((result * 31) + ((this.bookingData == null) ? 0 : this.bookingData.hashCode()));
        result = ((result * 31) + ((this.bedCount == null) ? 0 : this.bedCount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return ((((((((((((((((((((((((this.area == rhs.area) || ((this.area != null) && this.area.equals(rhs.area)))
                && ((this.roomCount == rhs.roomCount)
                        || ((this.roomCount != null) && this.roomCount.equals(rhs.roomCount))))
                && ((this.code == rhs.code) || ((this.code != null) && this.code.equals(rhs.code))))
                && ((this.specialConditions == rhs.specialConditions)
                        || ((this.specialConditions != null) && this.specialConditions.equals(rhs.specialConditions))))
                && ((this.available == rhs.available)
                        || ((this.available != null) && this.available.equals(rhs.available))))
                && ((this.description == rhs.description)
                        || ((this.description != null) && this.description.equals(rhs.description))))
                && ((this.lowStock == rhs.lowStock) || ((this.lowStock != null) && this.lowStock.equals(rhs.lowStock))))
                && ((this.studyLevel == rhs.studyLevel)
                        || ((this.studyLevel != null) && this.studyLevel.equals(rhs.studyLevel))))
                && ((this.inUnavailabilityPeriod == rhs.inUnavailabilityPeriod)
                        || ((this.inUnavailabilityPeriod != null)
                                && this.inUnavailabilityPeriod.equals(rhs.inUnavailabilityPeriod))))
                && ((this.label == rhs.label) || ((this.label != null) && this.label.equals(rhs.label))))
                && ((this.unavailabilityMessage == rhs.unavailabilityMessage) || ((this.unavailabilityMessage != null)
                        && this.unavailabilityMessage.equals(rhs.unavailabilityMessage))))
                && ((this.reference == rhs.reference)
                        || ((this.reference != null) && this.reference.equals(rhs.reference))))
                && ((this.medias == rhs.medias) || ((this.medias != null) && this.medias.equals(rhs.medias))))
                && ((this.equipments == rhs.equipments)
                        || ((this.equipments != null) && this.equipments.equals(rhs.equipments))))
                && ((this.bedroomCount == rhs.bedroomCount)
                        || ((this.bedroomCount != null) && this.bedroomCount.equals(rhs.bedroomCount))))
                && ((this.occupationModes == rhs.occupationModes)
                        || ((this.occupationModes != null) && this.occupationModes.equals(rhs.occupationModes))))
                && ((this.highDemand == rhs.highDemand)
                        || ((this.highDemand != null) && this.highDemand.equals(rhs.highDemand))))
                && ((this.id == rhs.id) || ((this.id != null) && this.id.equals(rhs.id))))
                && ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null)
                        && this.additionalProperties.equals(rhs.additionalProperties))))
                && ((this.beds == rhs.beds) || ((this.beds != null) && this.beds.equals(rhs.beds))))
                && ((this.residence == rhs.residence)
                        || ((this.residence != null) && this.residence.equals(rhs.residence))))
                && ((this.bookingData == rhs.bookingData)
                        || ((this.bookingData != null) && this.bookingData.equals(rhs.bookingData))))
                && ((this.bedCount == rhs.bedCount)
                        || ((this.bedCount != null) && this.bedCount.equals(rhs.bedCount))));
    }

}
