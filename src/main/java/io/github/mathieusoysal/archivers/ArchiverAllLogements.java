package io.github.mathieusoysal.archivers;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.data.managment.savers.DataSaver;
import io.github.mathieusoysal.logement.LogementsClassifier;

public class ArchiverAllLogements implements Archiver {

    @Override
    public void archive() {
        var dataCollector = new DataCollectorFromArchive(Archiver.getLinkToArchive());
        var logements = new LogementsClassifier();
        logements.addLogements(dataCollector.getAllLogements());
        logements.addLogements(dataCollector.getConvertedSumUpOfDay(Archiver.getDayToArchive()));
        DataSaver.saveForGlobalArchive(ArchiveName.ALL_LOGEMENTS, logements.getLogements());
    }

}
