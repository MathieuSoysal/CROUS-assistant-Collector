package io.github.mathieusoysal.data.managment.savers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


class FileManagerTest {

    @Test
    void testGetOrCreateArchiveFile() {
        // Assert that the file has the correct name
        assertEquals(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("HH")), ArchiveName.HOUR.getName());
    }
}