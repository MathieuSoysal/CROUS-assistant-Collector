package io.github.mathieusoysal.logement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AddressUtilsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "city de la garde;123 Main St 84450       city de la garde",
            "Belle    -_éà@ç=$*ù+}ç_ç&     city;123 Main St     84450  Belle    -_éà@ç=$*ù+}ç_ç&     city",
            "qsdsqddqssdqdqst qsd sqdsq sqd sqd sqd sqcity;123 Main St     84450       11841    qsdsqddqssdqdqst qsd sqdsq sqd sqd sqd sqcity",
            "c;456 Main St 8484 84450 c"
    }, delimiter = ';')
    void testGetCityFromString(String expectedCity, String address) {
        String city = AddressUtils.getCityFromString(address);
        assertEquals(expectedCity, city);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "123 Main St 84450       city de la garde;123 Main St",
            "123 -  - - 8451 8451 Main St     84450 city;123 -  - - 8451 8451 Main St",
            "123 Main St     84450       11841 city;123 Main St     84450",
            "456 Main St 8484 84450 city;456 Main St 8484"
    }, delimiter = ';')
    void testGetStreetFromString(String address, String expectedStreet) {
        String street = AddressUtils.getStreetFromString(address);
        assertEquals(expectedStreet, street);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "123 Main St 84       city de la garde;84",
            "123 -  - - 8451 8451 Main St     84450 city;84450",
            "123 Main St     84450       11841 city;11841",
            "456 Main St 8484 84450 city;84450"
    }, delimiter = ';')
    void testGetZipCodeFromString(String address, String expectedZipCode) {
        String zipCode = AddressUtils.getZipCodeFromString(address);
        assertEquals(expectedZipCode, zipCode);
    }
}