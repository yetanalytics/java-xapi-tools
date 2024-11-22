package com.yetanalytics.util;

import java.io.File;

public class TestFileUtils {
    public static File getJsonTestFile(String filename){
        String path = String.format("src/test/resources/statements/%s.json", filename);
        return new File(path);
    }
}
