package io.github.mathieusoysal.logement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AddressUtilsTest {

    // Parameterized tests with strings

    @ParameterizedTest
    @ValueSource(strings = {
            "123 Main St, 84450 city",
            "123 Main St     84450           city",
            "123 Main St     84450       11841    city",
            "456 Main St 8484 84450 city"
    })
    void testGetCityFromString(String address) {
        String city = AddressUtils.getCityFromString(address);
        assertEquals("city", city);
    }

    @Test
    void testGetZipCodeFromString() {
        String address = "123 Main St, Anytown, USA 12345";
        String zipCode = AddressUtils.getZipCodeFromString(address);
        assertEquals("12345", zipCode);
    }

    @Test
    void testGetStreetFromString() {
        String address = "123 Main St, Anytown, USA 12345";
        String street = AddressUtils.getStreetFromString(address);
        assertEquals("123 Main St", street);
    }

}