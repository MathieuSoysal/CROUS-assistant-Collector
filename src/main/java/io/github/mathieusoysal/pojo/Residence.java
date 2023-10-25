
package io.github.mathieusoysal.pojo;

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
        "label",
        "address",
        "code",
        "medias",
        "description",
        "location",
        "transports",
        "uairne",
        "sector",
        "allocationMinimumDuration"
})
@Generated("jsonschema2pojo")
public class Residence {

    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    private String label;
    @JsonProperty("address")
    private String address;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("medias")
    private List<Media__1> medias = new ArrayList<Media__1>();
    @JsonProperty("description")
    private String description;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("transports")
    private List<Transport> transports = new ArrayList<Transport>();
    @JsonProperty("uairne")
    private String uairne;
    @JsonProperty("sector")
    private Sector sector;
    @JsonProperty("allocationMinimumDuration")
    private Integer allocationMinimumDuration;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Residence withId(String id) {
        this.id = id;
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

    public Residence withLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    public Residence withAddress(String address) {
        this.address = address;
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

    public Residence withCode(Integer code) {
        this.code = code;
        return this;
    }

    @JsonProperty("medias")
    public List<Media__1> getMedias() {
        return medias;
    }

    @JsonProperty("medias")
    public void setMedias(List<Media__1> medias) {
        this.medias = medias;
    }

    public Residence withMedias(List<Media__1> medias) {
        this.medias = medias;
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

    public Residence withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public Residence withLocation(Location location) {
        this.location = location;
        return this;
    }

    @JsonProperty("transports")
    public List<Transport> getTransports() {
        return transports;
    }

    @JsonProperty("transports")
    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public Residence withTransports(List<Transport> transports) {
        this.transports = transports;
        return this;
    }

    @JsonProperty("uairne")
    public String getUairne() {
        return uairne;
    }

    @JsonProperty("uairne")
    public void setUairne(String uairne) {
        this.uairne = uairne;
    }

    public Residence withUairne(String uairne) {
        this.uairne = uairne;
        return this;
    }

    @JsonProperty("sector")
    public Sector getSector() {
        return sector;
    }

    @JsonProperty("sector")
    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Residence withSector(Sector sector) {
        this.sector = sector;
        return this;
    }

    @JsonProperty("allocationMinimumDuration")
    public Integer getAllocationMinimumDuration() {
        return allocationMinimumDuration;
    }

    @JsonProperty("allocationMinimumDuration")
    public void setAllocationMinimumDuration(Integer allocationMinimumDuration) {
        this.allocationMinimumDuration = allocationMinimumDuration;
    }

    public Residence withAllocationMinimumDuration(Integer allocationMinimumDuration) {
        this.allocationMinimumDuration = allocationMinimumDuration;
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

    public Residence withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Residence.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("label");
        sb.append('=');
        sb.append(((this.label == null) ? "<null>" : this.label));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null) ? "<null>" : this.address));
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null) ? "<null>" : this.code));
        sb.append(',');
        sb.append("medias");
        sb.append('=');
        sb.append(((this.medias == null) ? "<null>" : this.medias));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null) ? "<null>" : this.location));
        sb.append(',');
        sb.append("transports");
        sb.append('=');
        sb.append(((this.transports == null) ? "<null>" : this.transports));
        sb.append(',');
        sb.append("uairne");
        sb.append('=');
        sb.append(((this.uairne == null) ? "<null>" : this.uairne));
        sb.append(',');
        sb.append("sector");
        sb.append('=');
        sb.append(((this.sector == null) ? "<null>" : this.sector));
        sb.append(',');
        sb.append("allocationMinimumDuration");
        sb.append('=');
        sb.append(((this.allocationMinimumDuration == null) ? "<null>" : this.allocationMinimumDuration));
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
        result = ((result * 31) + ((this.address == null) ? 0 : this.address.hashCode()));
        result = ((result * 31) + ((this.code == null) ? 0 : this.code.hashCode()));
        result = ((result * 31) + ((this.uairne == null) ? 0 : this.uairne.hashCode()));
        result = ((result * 31) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((result * 31)
                + ((this.allocationMinimumDuration == null) ? 0 : this.allocationMinimumDuration.hashCode()));
        result = ((result * 31) + ((this.label == null) ? 0 : this.label.hashCode()));
        result = ((result * 31) + ((this.medias == null) ? 0 : this.medias.hashCode()));
        result = ((result * 31) + ((this.transports == null) ? 0 : this.transports.hashCode()));
        result = ((result * 31) + ((this.location == null) ? 0 : this.location.hashCode()));
        result = ((result * 31) + ((this.id == null) ? 0 : this.id.hashCode()));
        result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
        result = ((result * 31) + ((this.sector == null) ? 0 : this.sector.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Residence) == false) {
            return false;
        }
        Residence rhs = ((Residence) other);
        return (((((((((((((this.address == rhs.address)
                || ((this.address != null) && this.address.equals(rhs.address)))
                && ((this.code == rhs.code) || ((this.code != null) && this.code.equals(rhs.code))))
                && ((this.uairne == rhs.uairne) || ((this.uairne != null) && this.uairne.equals(rhs.uairne))))
                && ((this.description == rhs.description)
                        || ((this.description != null) && this.description.equals(rhs.description))))
                && ((this.allocationMinimumDuration == rhs.allocationMinimumDuration)
                        || ((this.allocationMinimumDuration != null)
                                && this.allocationMinimumDuration.equals(rhs.allocationMinimumDuration))))
                && ((this.label == rhs.label) || ((this.label != null) && this.label.equals(rhs.label))))
                && ((this.medias == rhs.medias) || ((this.medias != null) && this.medias.equals(rhs.medias))))
                && ((this.transports == rhs.transports)
                        || ((this.transports != null) && this.transports.equals(rhs.transports))))
                && ((this.location == rhs.location) || ((this.location != null) && this.location.equals(rhs.location))))
                && ((this.id == rhs.id) || ((this.id != null) && this.id.equals(rhs.id))))
                && ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null)
                        && this.additionalProperties.equals(rhs.additionalProperties))))
                && ((this.sector == rhs.sector) || ((this.sector != null) && this.sector.equals(rhs.sector))));
    }

}
