package io.github.mathieusoysal.archivers;

import java.time.LocalDate;

import io.github.mathieusoysal.Properties;
import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromCrous;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.data.managment.savers.ArchivePathBuilder;

public class ArchiverHour implements Archiver {

    @Override
    public void archive() {
        var logements = DataCollectorFromCrous.getAvailableLogementsWithConnection(Properties.MAIL.getValue(),
                Properties.PASSWORD.getValue());
        ArchivePathBuilder
                .startPath()
                .addPath("available")
                .addPath(LocalDate.now())
                .endPathAndSaveData(ArchiveName.HOUR, logements);
    }

}
