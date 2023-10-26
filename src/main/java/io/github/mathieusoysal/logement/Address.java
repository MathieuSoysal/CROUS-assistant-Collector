package io.github.mathieusoysal.logement;

/**
 * This class represents an address, which consists of a street, city, zip code,
 * and location.
 * The location is represented by a {@link Location} object.
 *
 * @see Location
 * @version 1.0
 * @author MathieuSoysal
 */
/**
 * Represents an address of a residence.
 */
public class Address {

    /**
     * The full street address of the residence.
     */
    private final String fullAddress;

    /**
     * The street of the address.
     */
    private final String street;

    /**
     * The city of the address.
     */
    private final String city;

    /**
     * The zip code of the address.
     */
    private final String zipCode;

    /**
     * The location of the address.
     */
    private final Location location;

    /**
     * Constructs an address object with the given street, city, zip code, and
     * location.
     *
     * @param street   the street of the address
     * @param city     the city of the address
     * @param zipCode  the zip code of the address
     * @param location the location of the address
     */
    public Address(final String street, final String city, final String zipCode, final Location location) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.fullAddress = getFullAddressFromAttributes(street, city, zipCode);
        this.location = location;
    }

    /**
     * Constructs an address object with the given full address and location.
     *
     * @param address  the full address of the residence
     * @param location the location of the address
     */
    public Address(final String address, final Location location) {
        this.street = AddressUtils.getStreetFromString(address);
        this.city = AddressUtils.getCityFromString(address);
        this.zipCode = AddressUtils.getZipCodeFromString(address);
        this.fullAddress = address;
        this.location = location;
    }

    /**
     * Returns the full street address of the residence.
     *
     * @return the full street address of the residence
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * Returns the street of the address.
     *
     * @return the street of the address
     */
    public String getStreet() {
        return street;
    }

    /**
     * Returns the city of the address.
     *
     * @return the city of the address
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the zip code of the address.
     *
     * @return the zip code of the address
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Returns the location of the address.
     *
     * @return the location of the address
     */
    public Location getLocation() {
        return location;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(final Object obj) {
        return false;
    }

    /**
     * Returns the full street address of the residence constructed from the given
     * street, city, and zip code.
     *
     * @param street  the street of the address
     * @param city    the city of the address
     * @param zipCode the zip code of the address
     * @return the full street address of the residence constructed from the given
     *         street, city, and zip code
     */
    private String getFullAddressFromAttributes(final String street, final String city, final String zipCode) {
        return street + " " + zipCode + " " + city;
    }
}
