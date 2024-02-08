package io.github.mathieusoysal.data.managment.savers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class FolderManagerTest {

    @AfterEach
    void tearDown() {
        File archiveFolder = new File("archive");
        if (archiveFolder.exists())
            archiveFolder.delete();
    }

    @Test
    void testCreateArchiveFolder() {
        File archiveFolder = FolderManager.getOrCreateArchiveFolder();

        assertTrue(archiveFolder.exists());
        assertTrue(archiveFolder.isDirectory());
        assertEquals("archive", archiveFolder.getName());
    }

    @Test
    void testGetOrCreateArchiveFolderWithGivenDate() {
        OffsetDateTime date = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, OffsetDateTime.now().getOffset());

        File actualArchiveFile = FolderManager.getOrCreateArchiveFolderWithGivenDate(date);

        assertEquals("2024-01-01", actualArchiveFile.getName());
    }
}