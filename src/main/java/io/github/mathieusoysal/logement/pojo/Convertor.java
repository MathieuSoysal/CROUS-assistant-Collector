package io.github.mathieusoysal.pojo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Convertor {

    private Convertor() {
    }

    static List<Item> getItemsFromJsonFile(File file) throws StreamReadException, DatabindException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input results = objectMapper.readValue(file, Input.class);
        return results.getResults().getItems();
    }

}
