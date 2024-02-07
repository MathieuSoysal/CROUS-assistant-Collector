package io.github.mathieusoysal.archivers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import io.github.mathieusoysal.Properties;

public class ArchiveHourTest {

    @Test
    @EnabledIfEnvironmentVariable(named = "CI", matches = "true")
    void testArchiveHour() {
        System.setProperty(Properties.MAIL.getName(), System.getenv("TEST_MAIL"));
        System.setProperty(Properties.PASSWORD.getName(), System.getenv("TEST_PASSWORD"));

        assertDoesNotThrow(() -> Archivers.ARCHIVER_HOUR.archive());
    }

}
