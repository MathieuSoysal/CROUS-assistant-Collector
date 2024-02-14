package io.github.mathieusoysal.archivers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.data.managment.convertors.Convertor;

class ArchivedResidencesManagerTest {
    private static File jsonTestFile1 = new File("src/test/java/io/github/mathieusoysal/resources/test-hash1.txt");
    private static File jsonTestFile2 = new File("src/test/java/io/github/mathieusoysal/resources/test-hash2.txt");
    private static File jsonTestFile3 = new File("src/test/java/io/github/mathieusoysal/resources/test-hash3.txt");

    @Test
    void testGetHashOfArchivedFile_shouldBeEqual_IfContentIsEquals() throws NoSuchAlgorithmException, IOException {
        String hash1 = ArchiverAllResidences.getHashOfArchivedFile(jsonTestFile3);
        String hash2 = ArchiverAllResidences.getHashOfArchivedFile(jsonTestFile2);
        assertEquals(hash1, hash2);
    }

    @Test
    void testGetHashOfArchivedFile_shouldBeDifferent_IfContentIsDifferent()
            throws NoSuchAlgorithmException, IOException {
        String hash1 = ArchiverAllResidences.getHashOfArchivedFile(jsonTestFile1);
        String hash2 = ArchiverAllResidences.getHashOfArchivedFile(jsonTestFile2);
        assertEquals(false, hash1.equals(hash2));
    }

    @Test
    void testOrderOfResidences() {
        var residences = ArchivedResidences
                .generateArchivedResidencesFromLinkArchive(Archiver.getLinkToArchive());
        String expectedJsonString = Convertor.convertResidencesToJson(residences.getResidences());

        var newResidences = new DataCollectorFromArchive(Archiver.DEFAULT_LINK_TO_ARCHIVE).getAllResidences();
        residences.addResidences(newResidences);
        String actualJsonString = Convertor.convertResidencesToJson(residences.getResidences());

        assertEquals(expectedJsonString, actualJsonString);
    }
}