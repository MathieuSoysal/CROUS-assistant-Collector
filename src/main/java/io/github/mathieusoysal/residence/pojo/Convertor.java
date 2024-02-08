package io.github.mathieusoysal.residence.pojo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.residence.Address;
import io.github.mathieusoysal.residence.BedKind;
import io.github.mathieusoysal.residence.Location;
import io.github.mathieusoysal.residence.Residence;
import io.github.mathieusoysal.residence.OccupationKind;
import io.github.mathieusoysal.residence.TransportKind;
import io.github.mathieusoysal.residence.TransportUnitOfMeasure;

public class Convertor {
    private static final Logger LOGGER = Logger.getLogger();

    private Convertor() {
    }

    static List<Item> getItemsFromJsonFile(File file) {
        Input results;
        try {
            LOGGER.info(() -> "Reading json file for convertion to java object");
            ObjectMapper objectMapper = new ObjectMapper();
            results = objectMapper.readValue(file, Input.class);
        } catch (IOException e) {
            LOGGER.error(() -> "Error while reading json file");
            throw new ConvertorException("Error while reading json file", e);
        }
        LOGGER.info(() -> "Json file converted to java object");
        return results.getResults().getItems();
    }

    static List<Item> getItemsFromJsonString(String json) {
        Input results;
        try {
            LOGGER.info(() -> "Reading json string for convertion to java object");
            ObjectMapper objectMapper = new ObjectMapper();
            results = objectMapper.readValue(json, Input.class);
        } catch (IOException e) {
            LOGGER.error(() -> "Error while reading json file");
            throw new ConvertorException("Error while reading json file", e);
        }
        LOGGER.info(() -> "Json string converted to java object");
        return results.getResults().getItems();
    }

    static List<Residence> convertItemsToResidences(List<Item> items) {
        LOGGER.info(() -> "Converting items to residences");
        var result = items.stream().map(Convertor::convertItemToResidence).toList();
        LOGGER.info(() -> "Items converted to residences");
        return result;
    }

    public static List<Residence> getResidencesFromBruteJsonFile(File file) {
        List<Item> items = getItemsFromJsonFile(file);
        return convertItemsToResidences(items);
    }

    public static List<Residence> getResidencesFromBruteJsonString(String json) {
        List<Item> items = getItemsFromJsonString(json);
        return convertItemsToResidences(items);
    }

    private static Residence convertItemToResidence(Item item) {
        ResidenceBuilder residenceBuilder = new ResidenceBuilder(item.getId())
                .withLabel(item.getResidence().getLabel())
                .withAddress(getAddress(item))
                .withBedCount(item.getBedCount())
                .withBedKind(BedKind.fromString(item.getBeds().isEmpty() ? "" : item.getBeds().get(0).getType()))
                .withBedroomCount(item.getBedroomCount())
                .withRoomCount(item.getRoomCount())
                .withInUnavailabilityPeriod(item.getInUnavailabilityPeriod())
                .withDescription(item.getDescription())
                .withAvailable(item.getAvailable())
                .withHighDemand(item.getHighDemand())
                .withLowStock(item.getLowStock())
                .withEquipements(getEquipements(item))
                .withAreaMin(item.getArea().getMin())
                .withAreaMax(item.getArea().getMax())
                .withOccupationMods(getOccupationMods(item))
                .withTransport(getTransports(item));
        return residenceBuilder.build();
    }

    private static Address getAddress(Item item) {
        return new Address(item.getResidence().getAddress(),
                new Location(item.getResidence().getLocation().getLat(), item.getResidence().getLocation().getLon()));
    }

    private static List<io.github.mathieusoysal.residence.Equipment> getEquipements(Item item) {
        return item.getEquipments().stream()
                .map(equipment -> io.github.mathieusoysal.residence.Equipment.fromString((equipment.getLabel())))
                .toList();
    }

    private static List<io.github.mathieusoysal.residence.OccupationMode> getOccupationMods(Item item) {
        return item.getOccupationModes().stream()
                .map(occupationMod -> new io.github.mathieusoysal.residence.OccupationMode(
                        OccupationKind.fromString(occupationMod.getType()),
                        occupationMod.getRent().getMin(),
                        occupationMod.getRent().getMax()))
                .toList();
    }

    private static List<io.github.mathieusoysal.residence.Transport> getTransports(Item item) {
        return item.getResidence().getTransports().stream()
                .map(transport -> new io.github.mathieusoysal.residence.Transport(
                        TransportKind.fromString(transport.getLabel()),
                        transport.getDescription(),
                        transport.getDistance(),
                        TransportUnitOfMeasure.fromString(transport.getUnitOfMeasure())))
                .toList();
    }

    static class ConvertorException extends RuntimeException {
        public ConvertorException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
