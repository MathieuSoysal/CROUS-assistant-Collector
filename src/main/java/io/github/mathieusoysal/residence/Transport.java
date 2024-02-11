package io.github.mathieusoysal.residence;

/**
 * Represents a mode of transportation.
 */
public class Transport {
    private TransportKind kind;
    private String description;
    private int distance;
    private TransportUnitOfMeasure unitOfMeasure;

    public Transport() {
    }

    /**
     * Constructs a new Transport object with the specified parameters.
     * 
     * @param kind          the kind of transportation
     * @param description   a description of the transportation
     * @param distance      the distance traveled by the transportation
     * @param unitOfMeasure the unit of measure for the distance
     */
    public Transport(TransportKind kind, String description, Integer distance, TransportUnitOfMeasure unitOfMeasure) {
        this.kind = kind;
        this.description = description;
        this.distance = distance == null ? 0 : distance;
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((kind == null) ? 0 : kind.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transport other = (Transport) obj;
        if (kind != other.kind)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

    /**
     * Returns the kind of transportation.
     * 
     * @return the kind of transportation
     */
    public TransportKind getKind() {
        return kind;
    }

    /**
     * Returns the description of the transportation.
     * 
     * @return the description of the transportation
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the distance traveled by the transportation.
     * 
     * @return the distance traveled by the transportation
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Returns the unit of measure for the distance.
     * 
     * @return the unit of measure for the distance
     */
    public TransportUnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

}
