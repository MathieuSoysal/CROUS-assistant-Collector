package io.github.mathieusoysal.archivers;

import java.time.LocalDate;
import java.util.List;

import io.github.mathieusoysal.Properties;
import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromCrous;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.logement.Logement;

class ArchiverHour implements Archiver {

    @Override
    public void archive() {
        var logements = archiveHour();
        ArchivedLogementsManager.updateArchiveOfAllLogements(logements);
    }

    private List<Logement> archiveHour() {
        var logements = DataCollectorFromCrous.getAvailableLogementsWithConnection(Properties.MAIL.getValue(),
                Properties.PASSWORD.getValue());
        var ids = logements.stream().map(Logement::getId).toList();
        ARCHIVE_SAVER
                .addPath("available")
                .addPath(LocalDate.now())
                .endPathAndSaveData(ArchiveName.HOUR, ids);
        return logements;
    }

}
