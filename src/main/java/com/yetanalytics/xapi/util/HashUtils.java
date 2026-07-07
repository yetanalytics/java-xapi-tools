package com.yetanalytics.xapi.util;

import java.util.regex.Pattern;

public final class HashUtils {
    private static final Pattern SHA1_HEX_PATTERN = Pattern.compile("^[0-9a-fA-F]{40}$");

    private HashUtils() {}

    public static boolean isSha1Hex(String value) {
        return value != null && SHA1_HEX_PATTERN.matcher(value).matches();
    }
}
