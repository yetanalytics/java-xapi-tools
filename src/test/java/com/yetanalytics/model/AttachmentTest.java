package com.yetanalytics.model;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.util.ValidationUtils;
import com.yetanalytics.xapi.model.Attachment;
import com.yetanalytics.xapi.model.LangMap;

import jakarta.validation.Validator;

public class AttachmentTest {
    private Validator validator;
    private Attachment attachment;

    @Before
    public void init() {
        validator = ValidationUtils.getValidator();
        attachment = new Attachment();
    }

    @Test
    public void testAttachment() {
        LangMap display = new LangMap(new HashMap<>());
        display.put("en-US", "Display");

        LangMap desc = new LangMap(new HashMap<>());
        desc.put("en-US", "Description");

        String contentType = "application/json";
        int length = 450;
        String sha2 = "426cf3a8b2864dd91201b989ba5728181da52bfff9a0489670e54cd8ec8b3a50";
        String fileUrl = "https://www.yetanalytics.com/files/file1.json";

        attachment.setUsageType("http://example.com/attachment");
        attachment.setDisplay(display);
        attachment.setDescription(desc);
        attachment.setContentType(contentType);
        attachment.setLength(length);
        attachment.setSha2(sha2);
        attachment.setFileUrl(fileUrl);

        ValidationUtils.assertValid(validator, attachment);
    }

    @Test
    public void testEmptyAttachment() {
        ValidationUtils.assertInvalid(validator, attachment, 5);
    }
}
