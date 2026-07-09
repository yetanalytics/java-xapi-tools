package com.yetanalytics.xapi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFileUtils {

    private static final Logger log = LoggerFactory.getLogger(TestFileUtils.class);

    public static File getJsonTestFile(String filename) throws IOException {
        Path filepath = getTestFilePath(filename);

        return filepath.toFile();
    }

    public static String getJsonTestFileString(String filename){
        Path filepath = getTestFilePath(filename);
        try {
            return Files.readString(filepath);
        } catch (Exception e) {
            log.error("Failed to read test file: " + filepath.toString(), e);
            return null;
        }
    }

    private static Path getTestFilePath(String filename) {
        String filePathString = String.format("src/test/resources/statements/%s.json", filename);
        Path filepath = Path.of(filePathString);

        // handle badly formatted json in a way that doesnt make them flag errors in IDEs
        if (filepath == null || !Files.exists(filepath))
            filepath = Path.of(String.format("%s.err", filePathString));

        return filepath;
    }


}
