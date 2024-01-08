package io.github.mathieusoysal.data.managment.convertors;

import java.util.List;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.logement.Logement;

public class Convertor {
    private static final Logger LOGGER = Logger.getLogger();

    public static String convertLogementsToJson(List<Logement> logements) throws JsonProcessingException {
        LOGGER.info(() -> "Converting logements to json");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var result = ow.writeValueAsString(logements);
        LOGGER.info(() -> "Logements converted to json");
        return result;
    }

    public static List<Logement> convertJsonToListOfLogements(String jsonLogements) {
        LOGGER.info(() -> "Converting json to logements");
        List<Logement> logements = null;
        try {
            logements = new ObjectMapper().readValue(jsonLogements, new TypeReference<List<Logement>>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting json to logements", e);
            throw new RuntimeException("Error while converting json to logements", e);
        }
        LOGGER.info(() -> "Json converted to logements");
        return logements;
    }

    public static Logement[] convertJsonToArrayOfLogements(String jsonLogements) {
        LOGGER.info(() -> "Converting json to logements");
        Logement[] logements = null;
        try {
            logements = new ObjectMapper().readValue(jsonLogements, Logement[].class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting json to logements", e);
            throw new RuntimeException("Error while converting json to logements", e);
        }
        LOGGER.info(() -> "Json converted to logements");
        return logements;
    }

    public static String convertLogementMatrixToJson(Logement[][] logements) {
        LOGGER.info(() -> "Converting logements to json");
        var objectMapper = new ObjectMapper();
        String result;
        try {
            result = objectMapper.writeValueAsString(logements);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting logements to json", e);
            throw new RuntimeException("Error while converting logements to json", e);
        }
        LOGGER.info(() -> "Logements converted to json");
        return result;
    }
}
