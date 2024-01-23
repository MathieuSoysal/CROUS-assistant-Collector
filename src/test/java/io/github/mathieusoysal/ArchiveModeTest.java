package io.github.mathieusoysal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ArchiveModeTest {

    @Test
    void testGetArchiveMod_shouldReturnHour_whenArchiveModIsHour() {
        ArchiveMode archiveMode = ArchiveMode.getArchiveMod("HOUR");
        assertEquals(ArchiveMode.HOUR, archiveMode);
    }

    @Test
    void testGetArchiveMod_shouldReturnDay_whenArchiveModIsDay() {
        ArchiveMode archiveMode = ArchiveMode.getArchiveMod("DAY_SUM_UP");
        assertEquals(ArchiveMode.DAY_SUM_UP, archiveMode);
    }

    @Test
    void testGetArchiveMod_shouldReturnWeek_whenArchiveModIsWeek() {
        ArchiveMode archiveMode = ArchiveMode.getArchiveMod("ALL_LOGEMENTS");
        assertEquals(ArchiveMode.ALL_LOGEMENTS, archiveMode);
    }

  
    @Test
    void testGetArchiveMod_shouldLogErrorAndExit_whenArchiveModIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArchiveMode.getArchiveMod("INVALID");
        });
    }
}