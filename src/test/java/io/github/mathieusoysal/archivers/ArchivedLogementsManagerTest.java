package io.github.mathieusoysal.archivers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

class ArchivedLogementsManagerTest {
    private static File jsonTestFile1 = new File("src/test/java/io/github/mathieusoysal/resources/test-hash1.txt");
    private static File jsonTestFile2 = new File("src/test/java/io/github/mathieusoysal/resources/test-hash2.txt");
    private static File jsonTestFile3 = new File("src/test/java/io/github/mathieusoysal/resources/test-hash3.txt");

    @Test
    void testGetHashOfArchivedFile_shouldBeEqual_IfContentIsEquals() throws NoSuchAlgorithmException, IOException {
        String hash1 = ArchivedLogementsManager.getHashOfArchivedFile(jsonTestFile3);
        String hash2 = ArchivedLogementsManager.getHashOfArchivedFile(jsonTestFile2);
        assertEquals(hash1, hash2);
    }

    @Test
    void testGetHashOfArchivedFile_shouldBeDifferent_IfContentIsDifferent() throws NoSuchAlgorithmException, IOException {
        String hash1 = ArchivedLogementsManager.getHashOfArchivedFile(jsonTestFile1);
        String hash2 = ArchivedLogementsManager.getHashOfArchivedFile(jsonTestFile2);
        assertEquals(false, hash1.equals(hash2));
    }
}