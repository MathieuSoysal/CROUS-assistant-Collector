package io.github.mathieusoysal.data.managment.savers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

class FolderManagerTest {

    @Test
    void testCreateArchiveFolder() {
        File archiveFolder = FolderManager.createArchiveFolder();

        assertTrue(archiveFolder.exists());
        assertTrue(archiveFolder.isDirectory());
        assertEquals("archive", archiveFolder.getName());
        archiveFolder.delete();
    }

    @Test
    void testGetOrCreateArchiveFolderWithGivenDate() {
        OffsetDateTime date = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, OffsetDateTime.now().getOffset());

        File actualArchiveFile = FolderManager.getOrCreateArchiveFolderWithGivenDate(date);

        assertEquals("2024-01-01", actualArchiveFile.getName());
    }
}