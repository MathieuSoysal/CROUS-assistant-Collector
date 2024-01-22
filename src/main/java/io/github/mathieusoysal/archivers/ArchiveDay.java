package io.github.mathieusoysal.archivers;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.data.managment.savers.DataSaver;

public class ArchiveDay implements Archiver {

    @Override
    public void archive() {
        var dataCollector = new DataCollectorFromArchive(Archiver.getLinkToArchive());
        var sumUpOfTheDay = dataCollector.getSumUpOfDay(Archiver.getDayToArchive());
        DataSaver.saveForCurrentDay(ArchiveName.DAY_SUM_UP, sumUpOfTheDay);
    }


}
