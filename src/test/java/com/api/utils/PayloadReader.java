package com.api.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayloadReader {
    public static String loadPayload(String filename) {
        String path = "src/test/resources/testdata/" + filename;
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
