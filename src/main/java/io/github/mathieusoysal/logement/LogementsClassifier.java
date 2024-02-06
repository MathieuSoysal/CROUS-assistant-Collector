package io.github.mathieusoysal.logement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LogementsClassifier {

    private Map<Integer, Logement> logements;

    public LogementsClassifier(Set<Logement> logements) {
        this.logements = logements
                .stream()
                .collect(Collectors.toMap(Logement::getId, logement -> logement));
    }

    public LogementsClassifier() {
        this.logements = new HashMap<>();
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