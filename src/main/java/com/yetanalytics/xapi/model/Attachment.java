package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yetanalytics.xapi.model.deserializers.MimeTypeDeserializer;
import com.yetanalytics.xapi.validation.Sha256Sum;

import jakarta.activation.MimeType;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;

/**
 * Class representation of the Attachment Component of the 
 * <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2411-attachments">9274.1.1 xAPI Specification</a>.
 */
@JsonInclude(Include.NON_NULL)
public class Attachment implements JSONObject {

    @NotNull
    private URI usageType;
    @NotNull
    private LangMap display;
    private LangMap description;

    @JsonDeserialize(using = MimeTypeDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull
    private MimeType contentType;
    @NotNull
    private Integer length;

    @NotNull
    @Sha256Sum
    private String sha2;
    
    private URI fileUrl;
    
    public URI getUsageType() {
        return usageType;
    }
    public void setUsageType(URI usageType) {
        this.usageType = usageType;
    }
    public LangMap getDisplay() {
        return display;
    }
    public void setDisplay(LangMap display) {
        this.display = display;
    }
    public LangMap getDescription() {
        return description;
    }
    public void setDescription(LangMap description) {
        this.description = description;
    }
    public MimeType getContentType() {
        return contentType;
    }
    public void setContentType(MimeType contentType) {
        this.contentType = contentType;
    }
    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }
    public String getSha2() {
        return sha2;
    }
    public void setSha2(String sha2) {
        this.sha2 = sha2;
    }
    public URI getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(URI fileUrl) {
        this.fileUrl = fileUrl;
    }

    // Validation

    @Override
    @JsonIgnore
    @AssertFalse
    public boolean isEmpty() {
        return (
            usageType == null &&
            display == null &&
            description == null &&
            contentType == null &&
            length == null &&
            sha2 == null &&
            fileUrl == null
        );
    }
}
