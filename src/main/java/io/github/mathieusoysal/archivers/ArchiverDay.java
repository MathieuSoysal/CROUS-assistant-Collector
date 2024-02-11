package io.github.mathieusoysal.archivers;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.data.managment.convertors.Convertor;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;

class ArchiverDay implements Archiver {

    @Override
    public void archive() {
        var dataCollector = new DataCollectorFromArchive(Archiver.getLinkToArchive());
        var sumUpOfTheDay = dataCollector.getConvertedSumUpOfDay(Archiver.getDayToArchive());
        String sumUpOfTheDayAsString = Convertor.convertIdMatrixToJson(sumUpOfTheDay);
        ARCHIVE_SAVER
                .addPath("available-residences-id")
                .addPath(Archiver.getDayToArchive())
                .endPathAndSaveData(ArchiveName.DAY_SUM_UP, sumUpOfTheDayAsString);
    }

}
