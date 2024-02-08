package io.github.mathieusoysal.archivers;

import java.time.LocalDate;
import java.util.List;

import io.github.mathieusoysal.Properties;
import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromCrous;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.residence.Residence;

class ArchiverHour implements Archiver {

    @Override
    public void archive() {
        var residences = archiveHour();
        ArchivedResidencesManager.updateArchiveOfAllResidences(residences);
    }

    private List<Residence> archiveHour() {
        var residences = DataCollectorFromCrous.getAvailableResidencesWithConnection(Properties.MAIL.getValue(),
                Properties.PASSWORD.getValue());
        var ids = residences.stream().map(Residence::getId).toList();
        ARCHIVE_SAVER
                .addPath("available-residences-id")
                .addPath(LocalDate.now())
                .endPathAndSaveData(ArchiveName.HOUR, ids);
        return residences;
    }

}
