package io.github.mathieusoysal.archivers;

import java.time.LocalDate;

import io.github.mathieusoysal.Properties;
import io.github.mathieusoysal.data.managment.savers.ArchiveSaver;

@FunctionalInterface
public interface Archiver {
    static final String DEFAULT_LINK_TO_ARCHIVE = "https://mathieusoysal.github.io/CROUS-assistant-Collector/v2";
    static final ArchiveSaver ARCHIVE_SAVER = ArchiveSaver.startPath();

    static String getLinkToArchive() {
        if (Properties.LINK_TO_ARCHIVE.isPresent())
            return Properties.LINK_TO_ARCHIVE.getValue();
        else
            return DEFAULT_LINK_TO_ARCHIVE;
    }

    static LocalDate getDayToArchive() {
        if (Properties.SPECIFIC_DAY.isPresent())
            return LocalDate.parse(Properties.SPECIFIC_DAY.getValue());
        else
            return LocalDate.now();
    }

    public void archive();
}
