package io.github.mathieusoysal.data.managment.convertors;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.exceptions.ConvertionRuntimeException;
import io.github.mathieusoysal.residence.Residence;

public class Convertor {
    private static final Logger LOGGER = Logger.getLogger();

    public static <T> String convertResidencesToJson(List<T> residences) {
        LOGGER.info(() -> "Converting residences to json");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String result = null;
        try {
            result = ow.writeValueAsString(residences);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting residences to json", e);
            throw new ConvertionRuntimeException("Error while converting residences to json", e);
        }
        LOGGER.info(() -> "Residences converted to json");
        return result;
    }

    public static List<Residence> convertJsonToListOfResidences(String jsonResidences) {
        LOGGER.info(() -> "Converting json to List of residences");
        List<Residence> residences = null;
        try {
            residences = new ObjectMapper().readValue(jsonResidences, new TypeReference<List<Residence>>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting json to List of residences", e);
            throw new ConvertionRuntimeException("Error while converting json to List of residences", e);
        }
        LOGGER.info(() -> "Json converted to residences");
        return residences;
    }

    public static Residence[] convertJsonToArrayOfResidences(String jsonResidences) {
        LOGGER.info(() -> "Converting json to Array of residences");
        Residence[] residences = null;
        try {
            residences = new ObjectMapper().readValue(jsonResidences, Residence[].class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting json to Array of residences", e);
            throw new ConvertionRuntimeException("Error while converting json to Array of residences", e);
        }
        LOGGER.info(() -> "Json converted to residences");
        return residences;
    }

    public static Integer[] convertJsonToArrayOfIds(String jsonResidences) {
        LOGGER.info(() -> "Converting json to Array of Id");
        Integer[] residences = null;
        try {
            residences = new ObjectMapper().readValue(jsonResidences, Integer[].class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting json to Array of Id", e);
            throw new ConvertionRuntimeException("Error while converting json to Array of Ids", e);
        }
        LOGGER.info(() -> "Json converted to Ids");
        return residences;
    }

    public static String convertResidenceMatrixToJson(Residence[][] residences) {
        LOGGER.info(() -> "Converting Matrix of residences to json");
        var objectMapper = new ObjectMapper();
        String result;
        try {
            result = objectMapper.writeValueAsString(residences);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting Matrix of residences to json", e);
            throw new ConvertionRuntimeException("Error while converting Matrix of residences to json", e);
        }
        LOGGER.info(() -> "Residences converted to json");
        return result;
    }

    public static String convertIdMatrixToJson(Integer[][] idResidences) {
        LOGGER.info(() -> "Converting Matrix of id of residences to json");
        var objectMapper = new ObjectMapper();
        String result;
        try {
            result = objectMapper.writeValueAsString(idResidences);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting Matrix of id residences to json", e);
            throw new ConvertionRuntimeException("Error while converting Matrix of id residences to json", e);
        }
        LOGGER.info(() -> "Id residences converted to json");
        return result;
    }
}
