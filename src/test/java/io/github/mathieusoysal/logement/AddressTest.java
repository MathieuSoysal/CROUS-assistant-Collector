package io.github.mathieusoysal.logement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void testGetGoodAttributes_withSimpleAddress() {
        // Arrange
        String addressString = "123 Main St 12345 Anytown";

        // Act
        Address address = new Address(addressString, null);
        Address address2 = new Address(address.getStreet(), address.getCity(), address.getZipCode(), null);

        // Assert
        Assertions.assertEquals(address.getFullAddress(), address2.getFullAddress());
    }

    @Test
    void testGetFullAddressFromAttributes() {
        // Arrange
        String street = "123 Main St";
        String city = "Anytown";
        String zipCode = "12345";
        Address address = new Address(street, city, zipCode, null);

        // Assert
        Assertions.assertEquals(street + " " + zipCode + " " + city, address.getFullAddress());
    }

    @Test
    void testGetFullAddress() {
        // Arrange
        String street = "123 Main St";
        String city = "Anytown";
        String zipCode = "12345";
        Address address = new Address(street, city, zipCode, null);

        // Act
        String fullAddress = address.getFullAddress();

        // Assert
        Assertions.assertEquals(street + " " + zipCode + " " + city, fullAddress);
    }

    @Test
    void testGetStreet() {
        // Arrange
        String street = "123 Main St";
        Address address = new Address(street, null, null, null);

        // Act
        String result = address.getStreet();

        // Assert
        Assertions.assertEquals(street, result);
    }

    @Test
    void testGetCity() {
        // Arrange
        String city = "Anytown";
        Address address = new Address(null, city, null, null);

        // Act
        String result = address.getCity();

        // Assert
        Assertions.assertEquals(city, result);
    }

    @Test
    void testGetZipCode() {
        // Arrange
        String zipCode = "12345";
        Address address = new Address(null, null, zipCode, null);

        // Act
        String result = address.getZipCode();

        // Assert
        Assertions.assertEquals(zipCode, result);
    }

    @Test
    void testGetLocation() {
        // Arrange
        Location location = new Location(0, 0);
        Address address = new Address(null, null, null, location);

        // Act
        Location result = address.getLocation();

        // Assert
        Assertions.assertEquals(location, result);
    }

    @Test
    void testEquals_withAddress() {
        // Arrange
        Address address1 = new Address("123 Main St", "Anytown", "12345", null);
        Address address2 = new Address("123 Main St", "Anytown", "12345", null);
        Address address3 = new Address("456 Oak St", "Othertown", "67890", null);

        // Act & Assert
        Assertions.assertEquals(address1, address2);
        Assertions.assertNotEquals(address1, address3);
    }

    @Test
    void testEquals_withLocation() {
        // Arrange
        Address address1 = new Address("123 Main St", "Anytown", "12345", new Location(0, 0));
        Address address2 = new Address("123 Main St", "Anytown", "12345", new Location(0, 0));
        Address address3 = new Address("123 Main St", "Anytown", "12345", new Location(0, 1));

        // Act & Assert
        Assertions.assertEquals(address1, address2);
        Assertions.assertNotEquals(address1, address3);
    }

    @Test
    void testEqualsWithNull() {
        // Arrange
        Address address1 = new Address("123 Main St", "Anytown", "12345", null);
        Address address2 = null;

        // Act & Assert
        Assertions.assertNotEquals(address1, address2);
    }

    @Test
    void testEqualsWithDifferentClass() {
        // Arrange
        Address address = new Address("123 Main St", "Anytown", "12345", null);
        Object obj = new Object();

        // Act & Assert
        Assertions.assertNotEquals(address, obj);
    }
}
