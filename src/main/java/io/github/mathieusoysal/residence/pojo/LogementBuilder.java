package io.github.mathieusoysal.residence.pojo;

import java.util.List;

import io.github.mathieusoysal.residence.Address;
import io.github.mathieusoysal.residence.BedKind;
import io.github.mathieusoysal.residence.Equipment;
import io.github.mathieusoysal.residence.Residence;
import io.github.mathieusoysal.residence.OccupationMode;

class ResidenceBuilder {
    private Residence residence;

    ResidenceBuilder(int id) {
        residence = new Residence(id);
    }

    ResidenceBuilder withId(int hId) {
        residence.setId(hId);
        return this;
    }

    ResidenceBuilder withAddress(Address hAddress) {
        residence.setAddress(hAddress);
        return this;
    }

    ResidenceBuilder withBedCount(int hBedCount) {
        residence.setBedCount(hBedCount);
        return this;
    }

    ResidenceBuilder withBedKind(BedKind hBedKind) {
        residence.setBedKind(hBedKind);
        return this;
    }

    ResidenceBuilder withBedroomCount(int hBedroomCount) {
        residence.setBedroomCount(hBedroomCount);
        return this;
    }

    ResidenceBuilder withRoomCount(int hRoomCount) {
        residence.setRoomCount(hRoomCount);
        return this;
    }

    ResidenceBuilder withInUnavailabilityPeriod(boolean hInUnavailabilityPeriod) {
        residence.setInUnavailabilityPeriod(hInUnavailabilityPeriod);
        return this;
    }

    ResidenceBuilder withDescription(String hDescription) {
        residence.setDescription(hDescription);
        return this;
    }

    ResidenceBuilder withAvailable(boolean hAvailable) {
        residence.setAvailable(hAvailable);
        return this;
    }

    ResidenceBuilder withHighDemand(boolean hHighDemand) {
        residence.setHighDemand(hHighDemand);
        return this;
    }

    ResidenceBuilder withLowStock(boolean hLowStock) {
        residence.setLowStock(hLowStock);
        return this;
    }

    ResidenceBuilder withEquipements(List<Equipment> hEquipements) {
        residence.setEquipements(hEquipements);
        return this;
    }

    ResidenceBuilder withAreaMin(double hAreaMin) {
        residence.setAreaMin(hAreaMin);
        return this;
    }

    ResidenceBuilder withAreaMax(double hAreaMax) {
        residence.setAreaMax(hAreaMax);
        return this;
    }

    ResidenceBuilder withOccupationMods(List<OccupationMode> hOccupationMods) {
        residence.setOccupationMods(hOccupationMods);
        return this;
    }

    ResidenceBuilder withLabel(String label) {
        residence.setLabel(label);
        return this;
    }

    ResidenceBuilder withTransport(List<io.github.mathieusoysal.residence.Transport> transports) {
        residence.setTransports(transports);
        return this;
    }

    Residence build() {
        return residence;
    }

}