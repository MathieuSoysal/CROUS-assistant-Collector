package io.github.mathieusoysal.archivers;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;

public class ArchiverDay implements Archiver {

    @Override
    public void archive() {
        var dataCollector = new DataCollectorFromArchive(Archiver.getLinkToArchive());
        var sumUpOfTheDay = dataCollector.getSumUpOfDay(Archiver.getDayToArchive());
        ARCHIVE_SAVER
                .addPath("available")
                .addPath(Archiver.getDayToArchive())
                .endPathAndSaveData(ArchiveName.DAY_SUM_UP, sumUpOfTheDay);
    }

}
