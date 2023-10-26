package io.github.mathieusoysal.logement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AddressUtilsTest {

    // Parameterized tests with strings

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
}