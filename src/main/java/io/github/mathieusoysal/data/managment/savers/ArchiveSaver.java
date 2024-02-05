package io.github.mathieusoysal.data.managment.savers;

import java.io.File;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import io.github.mathieusoysal.data.managment.convertors.Convertor;
import io.github.mathieusoysal.logement.Logement;

public class ArchiveSaver {

    private Queue<String> folders;

    public static ArchiveSaver startPath() {
        var archivePath = new ArchiveSaver();
        archivePath.folders = new LinkedList<>();
        return archivePath;
    }

    public ArchiveSaver addPath(final String name) {
        var archivePath = new ArchiveSaver();
        archivePath.folders = new LinkedList<>(folders);
        archivePath.folders.add(name);
        return archivePath;
    }

    public ArchiveSaver addPath(OffsetDateTime date) {
        return addPath(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public ArchiveSaver addPath(LocalDate date) {
        return addPath(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public File endPathAndSaveData(final ArchiveName name, final String archivedData) {
        File archiveFile = generateArchiveFile(name);
        DataSaver.writeDataInsideArchiveFile(archivedData, archiveFile);
        return archiveFile;
    }

    public File endPathAndSaveData(final ArchiveName name, final List<Logement> logements) {
        return endPathAndSaveData(name, Convertor.convertLogementsToJson(logements));
    }

    private File generateArchiveFile(final ArchiveName name) {
        var folderAmbed = FolderManager.getOrCreateArchiveFolder();
        for (String folderName : folders)
            folderAmbed = FolderManager.getOrCreateArchiveFolderWithGivenFolderName(folderName, folderAmbed);
        var archiveFile =  FileManager.getArchiveFile(folderAmbed, name.getName());
        return archiveFile;
    }

}
