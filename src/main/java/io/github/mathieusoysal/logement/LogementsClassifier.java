package io.github.mathieusoysal.logement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogementsClassifier {

    private List<Logement> logements;

    public LogementsClassifier() {
        this.logements = new ArrayList<>();
    }

    public void addLogement(Logement logement) {
        this.logements.add(logement);
    }

    public void addLogements(Logement[][] logements) {
        for (Logement[] logements1 : logements)
            for (Logement logement : logements1)
                this.logements.add(logement);
    }

    public Map<Integer, List<Logement>> classify() {
        var mapped = logements.stream().collect(
                Collectors.groupingBy(
                        Logement::getId,
                        Collectors.toList()));
        var result = new HashMap<Integer, List<Logement>>();
        mapped.forEach((key, value) -> result.put(key, value.stream().unordered().distinct().toList()));
        return result;
    }
}