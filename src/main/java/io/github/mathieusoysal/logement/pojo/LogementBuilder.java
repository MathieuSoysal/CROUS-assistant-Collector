package io.github.mathieusoysal.logement.pojo;

import java.util.List;

import io.github.mathieusoysal.logement.Address;
import io.github.mathieusoysal.logement.BedKind;
import io.github.mathieusoysal.logement.Equipement;

class LogementBuilder {
    private Logement logement;

    LogementBuilder(int id) {
        logement = new Logement(id);
    }

    LogementBuilder withId(int hId) {
        logement.setId(hId);
        return this;
    }

    LogementBuilder withAddress(Address hAddress) {
        logement.setAddress(hAddress);
        return this;
    }

    LogementBuilder withBedCount(int hBedCount) {
        logement.setBedCount(hBedCount);
        return this;
    }

    LogementBuilder withBedKind(BedKind hBedKind) {
        logement.setBedKind(hBedKind);
        return this;
    }

    LogementBuilder withBedroomCount(int hBedroomCount) {
        logement.setBedroomCount(hBedroomCount);
        return this;
    }

    LogementBuilder withRoomCount(int hRoomCount) {
        logement.setRoomCount(hRoomCount);
        return this;
    }

    LogementBuilder withInUnavailabilityPeriod(boolean hInUnavailabilityPeriod) {
        logement.setInUnavailabilityPeriod(hInUnavailabilityPeriod);
        return this;
    }

    LogementBuilder withDescription(String hDescription) {
        logement.setDescription(hDescription);
        return this;
    }

    LogementBuilder withAvailable(boolean hAvailable) {
        logement.setAvailable(hAvailable);
        return this;
    }

    LogementBuilder withHighDemand(boolean hHighDemand) {
        logement.setHighDemand(hHighDemand);
        return this;
    }

    LogementBuilder withLowStock(boolean hLowStock) {
        logement.setLowStock(hLowStock);
        return this;
    }

    LogementBuilder withEquipements(List<Equipement> hEquipements) {
        logement.setEquipements(hEquipements);
        return this;
    }

    LogementBuilder withAreaMin(double hAreaMin) {
        logement.setAreaMin(hAreaMin);
        return this;
    }

    LogementBuilder withAreaMax(double hAreaMax) {
        logement.setAreaMax(hAreaMax);
        return this;
    }

    LogementBuilder withOccupationMods(List<OccupationMod> hOccupationMods) {
        logement.setOccupationMods(hOccupationMods);
        return this;
    }

    LogementBuilder withLabel(String label) {
        logement.setLabel(label);
        return this;
    }

    Logement build() {
        return logement;
    }

}