package com.yetanalytics.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yetanalytics.xapi.util.ValidationUtils;
import com.yetanalytics.xapi.model.Attachment;
import com.yetanalytics.xapi.model.LangMap;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;
import jakarta.validation.Validator;

public class AttachmentTest {
    private Validator validator;
    private Attachment attachment;

    @BeforeEach
    public void init() {
        validator = ValidationUtils.getValidator();
        attachment = new Attachment();
    }

    private void populateValidAttachment() throws URISyntaxException, MimeTypeParseException {
        LangMap display = new LangMap(new HashMap<>());
        display.put("en-US", "Display");

        LangMap desc = new LangMap(new HashMap<>());
        desc.put("en-US", "Description");

        MimeType contentType = new MimeType("application/json");
        int length = 450;
        String sha2 = "426cf3a8b2864dd91201b989ba5728181da52bfff9a0489670e54cd8ec8b3a50";
        URI fileUrl = new URI("https://www.yetanalytics.com/files/file1.json");

        attachment.setUsageType(new URI("http://example.com/attachment"));
        attachment.setDisplay(display);
        attachment.setDescription(desc);
        attachment.setContentType(contentType);
        attachment.setLength(length);
        attachment.setSha2(sha2);
        attachment.setFileUrl(fileUrl);
    }

    @Test
    public void testAttachment() throws URISyntaxException, MimeTypeParseException {
        populateValidAttachment();
        ValidationUtils.assertValid(validator, attachment);
    }

    @Test
    public void testInvalidSha2Length() throws URISyntaxException, MimeTypeParseException {
        populateValidAttachment();
        attachment.setSha2("426cf3a8b2864dd91201b989ba5728181da52bfff9a0489670e54cd8ec8b3a5");
        ValidationUtils.assertInvalid(validator, attachment);
    }

    @Test
    public void testInvalidSha2Hex() throws URISyntaxException, MimeTypeParseException {
        populateValidAttachment();
        attachment.setSha2("z26cf3a8b2864dd91201b989ba5728181da52bfff9a0489670e54cd8ec8b3a50");
        ValidationUtils.assertInvalid(validator, attachment);
    }

    @Test
    public void testEmptyAttachment() {
        // One error for empty attachment, one error each for null properties
        ValidationUtils.assertInvalid(validator, attachment, 6);
    }
}
