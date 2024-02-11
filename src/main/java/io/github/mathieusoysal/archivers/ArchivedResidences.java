package io.github.mathieusoysal.archivers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.residence.Residence;

class ArchivedResidences {

    private Map<Integer, Residence> residences;

    public ArchivedResidences(Set<Residence> residences) {
        this.residences = residences
                .stream()
                .collect(Collectors.toMap(Residence::getId, residence -> residence));
    }

    public ArchivedResidences() {
        this.residences = new HashMap<>();
    }

    public static ArchivedResidences generateArchivedResidencesFromLinkArchive(final String linkToArchive) {
        var dataCollector = new DataCollectorFromArchive(linkToArchive);
        var residences = new ArchivedResidences();
        residences.addResidences(dataCollector.getAllResidences());
        return residences;
    }

    public void addResidence(Residence residence) {
        if (!residences.containsKey(residence.getId()))
            residences.put(residence.getId(), residence);
        else {
            Residence existingResidence = residences.get(residence.getId());
            existingResidence.addResidence(residence);
        }
    }

    public void addResidences(Collection<Residence> residences) {
        residences.forEach(this::addResidence);
    }

    public void addResidences(Residence[][] residences) {
        for (Residence[] residences2 : residences)
            for (Residence residence : residences2)
                addResidence(residence);
    }

    public List<Residence> getResidences() {
        return new ArrayList<>(residences.values());
    }
}