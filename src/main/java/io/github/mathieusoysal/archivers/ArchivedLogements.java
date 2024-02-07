package io.github.mathieusoysal.archivers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.logement.Logement;

class ArchivedLogements {

    private Map<Integer, Logement> logements;

    public ArchivedLogements(Set<Logement> logements) {
        this.logements = logements
                .stream()
                .collect(Collectors.toMap(Logement::getId, logement -> logement));
    }

    public ArchivedLogements() {
        this.logements = new HashMap<>();
    }

    public static ArchivedLogements generateArchivedLogementsFromLinkArchive(final String linkToArchive) {
        var dataCollector = new DataCollectorFromArchive(linkToArchive);
        var logements = new ArchivedLogements();
        logements.addLogements(dataCollector.getAllLogements());
        return logements;
    }

    public void addLogement(Logement logement) {
        if (!logements.containsKey(logement.getId()))
            logements.put(logement.getId(), logement);
        else {
            Logement existingLogement = logements.get(logement.getId());
            existingLogement.addLogement(logement);
        }
    }

    public void addLogements(Collection<Logement> logements) {
        logements.forEach(this::addLogement);
    }

    public void addLogements(Logement[][] logements) {
        for (Logement[] logements2 : logements)
            for (Logement logement : logements2)
                addLogement(logement);
    }

    public List<Logement> getLogements() {
        return new ArrayList<>(logements.values());
    }
}