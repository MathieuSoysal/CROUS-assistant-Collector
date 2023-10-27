package io.github.mathieusoysal.logement.pojo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.mathieusoysal.logement.Address;
import io.github.mathieusoysal.logement.BedKind;
import io.github.mathieusoysal.logement.Location;
import io.github.mathieusoysal.logement.OccupationKind;

class Convertor {

    private Convertor() {
    }

    static List<Item> getItemsFromJsonFile(File file) throws StreamReadException, DatabindException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input results = objectMapper.readValue(file, Input.class);
        return results.getResults().getItems();
    }

    public static List<Logement> convertItemsToLogements(List<Item> items) {
        return items.stream().map(Convertor::convertItemToLogement).toList();
    }

    public static List<Logement> getLogementsFromJsonFile(File file)
            throws StreamReadException, DatabindException, IOException {
        List<Item> items = getItemsFromJsonFile(file);
        return convertItemsToLogements(items);
    }

    private static Logement convertItemToLogement(Item item) {
        LogementBuilder logementBuilder = new LogementBuilder(item.getId())
                .withLabel(item.getResidence().getLabel())
                .withAddress(getAddress(item))
                .withBedCount(item.getBedCount())
                .withBedKind(BedKind.fromString(item.getBeds().get(0).getType()))
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
                .withOccupationMods(getOccupationMods(item));
        return logementBuilder.build();
    }

    private static Address getAddress(Item item) {
        return new Address(item.getResidence().getAddress(),
                new Location(item.getResidence().getLocation().getLat(), item.getResidence().getLocation().getLat()));
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

}
