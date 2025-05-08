package com.yetanalytics.xapi.model;

import java.util.IllformedLocaleException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.Test;

public class LangTagTest {

    @Test
    public void testLanguageTag() {
        LangTag langTag = new LangTag("en-WK");
        assertEquals("en-WK", langTag.toString());
        assertEquals("en-WK", langTag.toLocale().toLanguageTag());

        LangTag langTag2 = new LangTag("en-wk");
        assertEquals("en-wk", langTag2.toString());
        assertEquals("en-WK", langTag2.toLocale().toLanguageTag());

        LangTag langTag3 = new LangTag("yu-555");
        assertEquals("yu-555", langTag3.toString());
        assertEquals("yu-555", langTag3.toLocale().toLanguageTag());
        assertEquals("yu", langTag3.toLocale().getLanguage());
        assertEquals("555", langTag3.toLocale().getCountry());
    }

    @Test
    public void testInvalidLanguageTag() {
        IllformedLocaleException exn =
            assertThrows(IllformedLocaleException.class, () -> new LangTag("notalanguagetag"));
        assertNotNull(exn.getMessage());

        IllformedLocaleException exn2 =
            assertThrows(IllformedLocaleException.class, () -> new LangTag(""));
        assertNotNull(exn2.getMessage());
    }
}
