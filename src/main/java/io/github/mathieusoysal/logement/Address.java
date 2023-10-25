package io.github.mathieusoysal.logement;

import java.util.regex.Pattern;

/**
 * This class represents an address, which consists of a street, city, zip code,
 * and location.
 * The location is represented by a {@link Location} object.
 *
 * @see Location
 * @version 1.0
 * @author MathieuSoysal
 */
public class Address {

    /**
     * The street address of the residence.
     */
    private String address;

    public String getAddress() {
        return address;
    }

    /**
     * The street of the address.
     */
    private String street;

    /**
     * The city of the address.
     */
    private String city;

    /**
     * The zip code of the address.
     */
    private String zipCode;

    /**
     * The location of the address.
     */
    private Location location;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Location getLocation() {
        return location;
    }

    public Address(String street, String city, String zipCode, Location location) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.address = getFullAddress();
        this.location = location;
    }

    public Address(String address, Location location) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
        this.location = location;
    }

    private void initAddressAttributesFromString(String address) {
        this.street = ;
        this.zipCode = addressAttributes[1].split(" ")[0];
        this.city = AddressUtils.getCityFromString(address);
    }

    private String getFullAddress() {
        return street + ", " + zipCode + " " + city;
    }
}

class AddressUtils {
    private static final Pattern PATTERN_REGEX_CITY = Pattern.compile("(\\d =?)[^\\d]*$", Pattern.MULTILINE);
    private static final Pattern PATTER_REGEX_ZIP_CODE = Pattern.compile("\\d*(?=[a-zA-Z]*[^\\d]*$)", Pattern.MULTILINE);
    private static final Pattern PATTERN_REGEX_STREET = Pattern.compile(".*[^ ](?= {1,}[a-zA-Z]*[0-9]{1,} {1,}[^0-9]*$)", Pattern.MULTILINE);

    private AddressUtils() {
    }

    public static String getCityFromString(String address) {
        return PATTERN_REGEX_CITY.matcher(address).group(1);
    }

    public static String getZipCodeFromString(String address) {
        return PATTER_REGEX_ZIP_CODE.matcher(address).group(1);
    }

}