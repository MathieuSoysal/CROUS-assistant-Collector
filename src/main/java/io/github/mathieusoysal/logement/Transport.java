package io.github.mathieusoysal.logement;

/**
 * Represents a mode of transportation.
 */
public class Transport {
    private TransportKind kind;
    private String description;
    private int distance;
    private TransportUnitOfMeasure unitOfMeasure;

    public Transport(TransportKind kind, String description, int distance, TransportUnitOfMeasure unitOfMeasure) {
        this.kind = kind;
        this.description = description;
        this.distance = distance;
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((kind == null) ? 0 : kind.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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

    public TransportKind getKind() {
        return kind;
    }

    public String getDescription() {
        return description;
    }

    public int getDistance() {
        return distance;
    }

    public TransportUnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

}
