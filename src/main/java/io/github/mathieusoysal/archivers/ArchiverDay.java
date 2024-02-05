package io.github.mathieusoysal.archivers;

import java.time.LocalDate;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.data.managment.savers.ArchiveSaver;

public class ArchiverDay implements Archiver {

    @Override
    public void archive() {
        var dataCollector = new DataCollectorFromArchive(Archiver.getLinkToArchive());
        var sumUpOfTheDay = dataCollector.getSumUpOfDay(Archiver.getDayToArchive());
        ArchiveSaver.startPath()
        .addPath("available")
        .addPath(LocalDate.now())
        .endPathAndSaveData(ArchiveName.DAY_SUM_UP, sumUpOfTheDay);
    }


}
