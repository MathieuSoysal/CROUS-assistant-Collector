package io.github.mathieusoysal.residence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import io.github.mathieusoysal.residence.AddressUtils;

class AddressUtilsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "Toulouse Cedex 4.;11 Allée du Professeur Camille Soula CS 47806 31078 Toulouse Cedex 4.",
            "Toulouse Cedex 4;118 Route de Narbonne CS 17705 31077 Toulouse Cedex 4",
            "NICE cedex2;96, avenue de Valrose - 06106 NICE cedex2",
            "Marseille;10 rue Henri Poincaré -13388 Marseille",
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
            "Foyer Etudiant des Alpes - 295 Faubourg Montmélian 73 000 CHAMBERY;Foyer Etudiant des Alpes - 295 Faubourg Montmélian",
            "11 Allée du Professeur Camille Soula CS 47806 31078 Toulouse Cedex 4.;11 Allée du Professeur Camille Soula CS 47806",
            "118 Route de Narbonne CS 17705 31077 Toulouse Cedex 4;118 Route de Narbonne CS 17705",
            "96, avenue de Valrose - 06106 NICE cedex2;96, avenue de Valrose",
            "10 rue Henri Poincaré -13388 Marseille;10 rue Henri Poincaré",
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
            "11 Allée du Professeur Camille Soula CS 47806 31078 Toulouse Cedex 4.;31078",
            "258 Chemin du Moulin à Vent Quartier Saint Cesaire 30 000 Nîmes;30000",
            "58 Chemin de la Cardinière 73 000 CHAMBERY;73000",
            "118 Route de Narbonne CS 17705 31077 Toulouse Cedex 4;31077",
            "96, avenue de Valrose - 06106 NICE cedex2;06106",
            "10 rue Henri Poincaré -13388 Marseille;13388",
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