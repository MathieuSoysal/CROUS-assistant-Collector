package io.github.mathieusoysal.pojo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConvertorTest {
    private static File jsonTestFile = new File("src/test/java/io/github/mathieusoysal/resources/test.json");

    @Test
    void testGetItemsFromJsonFile_isNotEmpty() throws IOException {
        // Act
        List<Item> items = Convertor.getItemsFromJsonFile(jsonTestFile);

        // Assert
        Assertions.assertEquals(1, items.size());
    }

    @Test
    void testGetItemsFromJsonFile_containTheGoodId() throws IOException {
        // Act
        List<Item> items = Convertor.getItemsFromJsonFile(jsonTestFile);

        // Assert
        Assertions.assertEquals(227, items.get(0).getId());
    }

    @Test
    void testGetItemsFromJsonFile_containTheGoodBedCount() throws IOException {
        // Act
        List<Item> items = Convertor.getItemsFromJsonFile(jsonTestFile);

        // Assert
        Assertions.assertEquals(1, items.get(0).getBedCount());
    }

    @Test
    void testGetItemsFromJsonFile_containTheGoodBedroomCount() throws IOException {
        // Act
        List<Item> items = Convertor.getItemsFromJsonFile(jsonTestFile);

        // Assert
        Assertions.assertEquals(1, items.get(0).getBedroomCount());
    }

}