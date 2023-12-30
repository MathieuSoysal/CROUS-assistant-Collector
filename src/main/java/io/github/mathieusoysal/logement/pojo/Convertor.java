package io.github.mathieusoysal.logement.pojo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.logement.Address;
import io.github.mathieusoysal.logement.BedKind;
import io.github.mathieusoysal.logement.Location;
import io.github.mathieusoysal.logement.OccupationKind;
import io.github.mathieusoysal.logement.TransportKind;
import io.github.mathieusoysal.logement.TransportUnitOfMeasure;

public class Convertor {
    private static final Logger LOGGER = Logger.getLogger();

    private Convertor() {
    }

    static List<Item> getItemsFromJsonFile(File file) throws StreamReadException, DatabindException, IOException {
        LOGGER.info(() -> "Reading json file for convertion to java object");
        ObjectMapper objectMapper = new ObjectMapper();
        Input results = objectMapper.readValue(file, Input.class);
        LOGGER.info(() -> "Json file converted to java object");
        return results.getResults().getItems();
    }

    static List<Item> getItemsFromJsonString(String json) throws StreamReadException, DatabindException, IOException {
        LOGGER.info(() -> "Reading json string for convertion to java object");
        ObjectMapper objectMapper = new ObjectMapper();
        Input results = objectMapper.readValue(json, Input.class);
        LOGGER.info(() -> "Json string converted to java object");
        return results.getResults().getItems();
    }

    static List<Logement> convertItemsToLogements(List<Item> items) {
        LOGGER.info(() -> "Converting items to logements");
        var result = items.stream().map(Convertor::convertItemToLogement).toList();
        LOGGER.info(() -> "Items converted to logements");
        return result;
    }

    public static List<Logement> getLogementsFromBruteJsonFile(File file)
            throws StreamReadException, DatabindException, IOException {
        List<Item> items = getItemsFromJsonFile(file);
        return convertItemsToLogements(items);
    }

    public static List<Logement> getLogementsFromBruteJsonString(String json)
            throws StreamReadException, DatabindException, IOException {
        List<Item> items = getItemsFromJsonString(json);
        return convertItemsToLogements(items);
    }

    private static Logement convertItemToLogement(Item item) {
        LogementBuilder logementBuilder = new LogementBuilder(item.getId())
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
        return logementBuilder.build();
    }

    private static Address getAddress(Item item) {
        return new Address(item.getResidence().getAddress(),
                new Location(item.getResidence().getLocation().getLat(), item.getResidence().getLocation().getLon()));
    }

    private static List<io.github.mathieusoysal.logement.Equipement> getEquipements(Item item) {
        return item.getEquipments().stream()
                .map(equipment -> io.github.mathieusoysal.logement.Equipement.fromString((equipment.getLabel())))
                .toList();
    }

    private static List<OccupationMod> getOccupationMods(Item item) {
        return item.getOccupationModes().stream()
                .map(occupationMod -> new OccupationMod(
                        OccupationKind.fromString(occupationMod.getType()),
                        occupationMod.getRent().getMin(),
                        occupationMod.getRent().getMax()))
                .toList();
    }

    private static List<io.github.mathieusoysal.logement.Transport> getTransports(Item item) {
        return item.getResidence().getTransports().stream()
                .map(transport -> new io.github.mathieusoysal.logement.Transport(
                        TransportKind.fromString(transport.getLabel()),
                        transport.getDescription(),
                        transport.getDistance(),
                        TransportUnitOfMeasure.fromString(transport.getUnitOfMeasure())))
                .toList();
    }

}
