package io.github.mathieusoysal.data.managment.collectors;

import java.util.List;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.logement.Logement;
import io.github.mathieusoysal.logement.pojo.Convertor;

public class DataCollectorFromCrous {
    private static final Logger LOGGER = Logger.getLogger();
    private static final String LINK_TO_GET_ALL_LOGEMENTS = "https://trouverunlogement.lescrous.fr/api/fr/search/32";

    public static List<Logement> getAvailableLogementsWithoutConnection() {
        Requestor requestor = new RequestorWithoutConnection();
        String jsonLogements = requestor.requestWitGet(LINK_TO_GET_ALL_LOGEMENTS);
        return Convertor.getLogementsFromBruteJsonString(jsonLogements);
    }

    public static List<Logement> getAllLogementsWithoutConnection() {
        LOGGER.info(() -> "Getting all logements");
        Requestor requestor = new RequestorWithoutConnection();
        String jsonLogements = requestor.requestWitGet("https://trouverunlogement.lescrous.fr/api/fr/search/29");
        return Convertor.getLogementsFromBruteJsonString(jsonLogements);
    }

    public static List<Logement> getAvailableLogementsWithConnection(String email, String password) {
        Requestor requestor = new RequestorWithConnection(email, password);
        String jsonLogements = requestor.requestWitGet(LINK_TO_GET_ALL_LOGEMENTS);
        return Convertor.getLogementsFromBruteJsonString(jsonLogements);
    }

    private DataCollectorFromCrous() {
    }
}
