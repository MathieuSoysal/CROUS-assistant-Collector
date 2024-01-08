package io.github.mathieusoysal.data.managment.savers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

public class FolderManagerTest {

    @Test
    void testCreateArchiveFolder() {
        File archiveFolder = FolderManager.createArchiveFolder();

        assertTrue(archiveFolder.exists());
        assertTrue(archiveFolder.isDirectory());
        assertEquals("archive", archiveFolder.getName());
        archiveFolder.delete();
    }
}
