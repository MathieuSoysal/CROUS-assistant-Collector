package io.github.mathieusoysal.data.managment.collectors;

import java.util.List;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.residence.Residence;
import io.github.mathieusoysal.residence.pojo.Convertor;

public class DataCollectorFromCrous {
    private static final Logger LOGGER = Logger.getLogger();
    private static final String LINK_TO_GET_ALL_LOGEMENTS = "https://trouverunlogement.lescrous.fr/api/fr/search/32";

    public static List<Residence> getAvailableResidencesWithoutConnection() {
        Requestor requestor = new RequestorWithoutConnection();
        String jsonResidences = requestor.requestWitGet(LINK_TO_GET_ALL_LOGEMENTS);
        return Convertor.getResidencesFromBruteJsonString(jsonResidences);
    }

    public static List<Residence> getAllResidencesWithoutConnection() {
        LOGGER.info(() -> "Getting all residences");
        Requestor requestor = new RequestorWithoutConnection();
        String jsonResidences = requestor.requestWitGet("https://trouverunlogement.lescrous.fr/api/fr/search/29");
        return Convertor.getResidencesFromBruteJsonString(jsonResidences);
    }

    public static List<Residence> getAvailableResidencesWithConnection(String email, String password) {
        Requestor requestor = new RequestorWithConnection(email, password);
        String jsonResidences = requestor.requestWitGet(LINK_TO_GET_ALL_LOGEMENTS);
        return Convertor.getResidencesFromBruteJsonString(jsonResidences);
    }

    private DataCollectorFromCrous() {
    }
}
