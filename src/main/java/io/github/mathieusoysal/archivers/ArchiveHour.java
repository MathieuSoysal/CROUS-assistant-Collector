package io.github.mathieusoysal.archivers;

import io.github.mathieusoysal.Properties;
import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromCrous;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.data.managment.savers.DataSaver;

public class ArchiveHour implements Archiver {

    @Override
    public void archive() {
        var logements = DataCollectorFromCrous.getAvailableLogementsWithConnection(Properties.MAIL.getValue(),
                Properties.PASSWORD.getValue());
        DataSaver.saveForCurrentDay(ArchiveName.HOUR, logements);
    }

}
