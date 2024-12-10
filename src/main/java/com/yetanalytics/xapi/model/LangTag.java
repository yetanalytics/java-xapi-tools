package com.yetanalytics.xapi.model;

import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.Locale.Builder;

public class LangTag {
    private final String languageTagString;
    private final Locale languageTagLocale;

    public LangTag(String langTag) throws IllformedLocaleException {
        Locale.getISOLanguages();
        Builder builder = new Builder();
        if (langTag == null || langTag.isEmpty()) {
            String errMsg = "Cannot parse null or empty language tag String!";
            throw new IllformedLocaleException(errMsg);
        } else {
            languageTagLocale = builder.setLanguageTag(langTag).build();
            languageTagString = langTag;
        }
    }

    public String getString() {
        return languageTagString;
    }

    public Locale getLocale() {
        return languageTagLocale;
    }

    @Override
    public String toString() {
        return getString();
    }
}
