package com.yetanalytics.model;

import java.util.IllformedLocaleException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

import com.yetanalytics.xapi.model.LangTag;

public class LangTagTest {

    @Test
    public void testLanguageTag() {
        LangTag langTag = new LangTag("en-WK");
        assertEquals("en-WK", langTag.getString());
        assertEquals("en-WK", langTag.getLocale().toLanguageTag());

        LangTag langTag2 = new LangTag("en-wk");
        assertEquals("en-wk", langTag2.getString());
        assertEquals("en-WK", langTag2.getLocale().toLanguageTag());

        LangTag langTag3 = new LangTag("yu-555");
        assertEquals("yu-555", langTag3.getString());
        assertEquals("yu-555", langTag3.getLocale().toLanguageTag());
    }

    @Test
    public void testInvalidLanguageTag() {
        IllformedLocaleException exn =
            assertThrows(IllformedLocaleException.class, () -> new LangTag("notalanguagetag"));
        assertNotNull(exn.getMessage());

        IllformedLocaleException exn2 =
            assertThrows(IllformedLocaleException.class, () -> new LangTag(""));
        assertEquals("Cannot parse null or empty language tag String!", exn2.getMessage());
    }
}
