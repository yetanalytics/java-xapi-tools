package com.yetanalytics;

import static org.junit.Assert.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yetanalytics.xapi.model.ActivityDefinition;
import com.yetanalytics.xapi.model.Attachment;
import com.yetanalytics.xapi.model.Result;
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.model.Verb;
import com.yetanalytics.xapi.util.Mapper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ValueExceptionTest extends TestCase {
    public ValueExceptionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ValueExceptionTest.class);
    }

    public <T> void assertJsonProcessingException(String original, Class<T> toConvert) {
        ObjectMapper mapper = Mapper.getMapper();
        JsonProcessingException exception = assertThrows(JsonProcessingException.class,
            () -> mapper.readValue(original, toConvert));

        System.out.println(exception.getOriginalMessage());
    }

    public void testInvalidUUID() {
        // Extra digit at front
        String uuidStr = "{\"id\": \"100000000-4000-8000-0000-000000000000\"}";
        String errMsg = "Cannot deserialize value of type `java.util.UUID` from String \"100000000-4000-8000-0000-000000000000\": UUID has to be represented by standard 36-char representation";
        JsonProcessingException exception = assertThrows(JsonProcessingException.class,
            () -> Mapper.getMapper().readValue(uuidStr, Statement.class));
        assertEquals(errMsg, exception.getOriginalMessage());
    }

    public void testInvalidUri() {
        // Curly braces are not allowed in URIs
        String uriStr = "{\"id\": \"{http://example.com}\"}";
        String errMsg = "Cannot deserialize value of type `java.net.URI` from String \"{http://example.com}\": not a valid textual representation, problem: Illegal character in scheme name at index 0: {http://example.com}";
        JsonProcessingException exception = assertThrows(JsonProcessingException.class,
            () -> Mapper.getMapper().readValue(uriStr, Verb.class));
        assertEquals(errMsg, exception.getOriginalMessage());
    }

    public void testInvalidTimestamp() {
        // Spaces in timestamp
        String timestampStr = "{\"timestamp\": \"2023 10 27T09:03:21.722Z\"}";
        String errMsg = "Cannot deserialize value of type `java.time.ZonedDateTime` from String \"2023 10 27T09:03:21.722Z\": Failed to deserialize java.time.ZonedDateTime: (java.time.format.DateTimeParseException) Text '2023 10 27T09:03:21.722Z' could not be parsed at index 4";
        JsonProcessingException exception = assertThrows(JsonProcessingException.class,
            () -> Mapper.getMapper().readValue(timestampStr, Statement.class));
        assertEquals(errMsg, exception.getOriginalMessage());
    }

    public void testInvalidDuration() {
        // Mispelled "PT" as "PR"
        String durationStr = "{\"duration\": \"PR4H35M59.14S\"}";
        String errMsg = "Cannot construct instance of `com.yetanalytics.xapi.model.XapiDuration`, problem: Text cannot be parsed to a Duration";
        JsonProcessingException exception = assertThrows(JsonProcessingException.class,
            () -> Mapper.getMapper().readValue(durationStr, Result.class));
        assertEquals(errMsg, exception.getOriginalMessage());
    }

    public void testInvalidMimeType() {
        // No subtype
        String mimeTypeStr = "{\"contentType\": \"text\"}";
        String errMsg = "jakarta.activation.MimeTypeParseException: Unable to find a sub type.";
        JsonProcessingException exception = assertThrows(JsonProcessingException.class,
            () -> Mapper.getMapper().readValue(mimeTypeStr, Attachment.class));
        assertEquals(errMsg, exception.getOriginalMessage());
    }

    public void testInvalidLangTag() {
        String langTagStr = "{\"description\": {\"{en-us}\": \"Foo\"}}";
        String errMsg = "Cannot deserialize Map key of type `com.yetanalytics.xapi.model.LangTag` from String \"{en-us}\": not a valid representation, problem: (java.lang.reflect.InvocationTargetException) Invalid subtag: {en [at index 0]";
        JsonProcessingException exception = assertThrows(JsonProcessingException.class,
            () -> Mapper.getMapper().readValue(langTagStr, ActivityDefinition.class));
        assertEquals(errMsg, exception.getOriginalMessage());
    }

    public void testInvalidVersion() {
        String versionStr = "{\"version\": \"1-0-0\"}";
        String errMsg = "Cannot construct instance of `org.semver4j.Semver`, problem: Version [1-0-0] is not valid semver.";
        JsonProcessingException exception = assertThrows(JsonProcessingException.class,
            () -> Mapper.getMapper().readValue(versionStr, Statement.class));
        assertEquals(errMsg, exception.getOriginalMessage());
    }
}
