package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Class representation of the Attachment Component of the 
 * <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2411-attachments">9274.1.1 xAPI Specification</a>.
 */
@JsonInclude(Include.NON_NULL)
public class Attachment {

    private String usageType;
    private LangMap display;
    private LangMap description;
    private String contentType;
    private Integer length;
    private String sha2;
    private String fileUrl;

    
    public String getUsageType() {
        return usageType;
    }
    public void setUsageType(String usageType) {
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
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
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
    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    
}
