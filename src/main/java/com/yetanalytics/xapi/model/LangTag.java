package com.yetanalytics.xapi.model;

import java.util.IllformedLocaleException;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Class representation of <a href="https://datatracker.ietf.org/doc/html/rfc5646">RFC 5646 Language Tags</a>
 * allowing for retrieval of both the original String and the corresponding Locale.
 */
@JsonInclude(Include.NON_NULL)
public class LangTag {
    private final String languageTagString;
    private final Locale languageTagLocale;

    /**
     * This constructor takes a RFC 5646 formatted String and converts it to a
     * LangTag object consisting of a java.util.Locale object and the original
     * String.
     * @param langTagStr The language tag String from an xAPI Statement language map.
     * @throws IllformedLocaleException when the String is not a valid language tag.
     */
    @JsonCreator
    public LangTag(String langTagStr) throws IllformedLocaleException {
        languageTagLocale = Locale.forLanguageTag(langTagStr);
        languageTagString = langTagStr;
    }

    /**
     * Static method to create a LangTag instance from the langTag string.
     * @param langTag - The String language tag.
     * @return The new LangTag instance.
     * @throws IllformedLocaleException when the String is not a valid language tag.
     * @see java.util.Locale#forLanguageTag(String str)
     */
    public static LangTag parse(String langTag) throws IllformedLocaleException {
        return new LangTag(langTag);
    }

    /**
     * Returns the original String version of the LangTag.
     * @return LangTag as a String.
     */
    @Override
    @JsonValue
    public String toString() {
        return languageTagString;
    }

    /** 
     * Returns the java.util.Locale instance corresponding to the LangTag.
     * @return The Locale.
     */
    public Locale toLocale() {
        return languageTagLocale;
    }

    // Needed due to the fact that LangTags are keys for LangMaps.

    /**
     * Checks that this LangTag is equal to the langTag based on the orignal
     * String (NOT the Locale).
     * @return true if the langTag is equal to this LangTag.
     */
    @Override
    public boolean equals(Object langTag) {
        if (langTag instanceof LangTag) {
            return languageTagString.equals(((LangTag) langTag).toString());
        } else {
            return false;
        }
    }

    /**
     * Hashes the LangTag based on its original String value (NOT the Locale).
     * @return the hash code of the original String.
     */
    @Override
    public int hashCode() {
        return languageTagString.hashCode();
    }
}
