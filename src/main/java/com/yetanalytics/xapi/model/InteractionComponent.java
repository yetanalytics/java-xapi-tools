package com.yetanalytics.xapi.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InteractionComponent {

    private String id;

    private LangMap description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LangMap getDescription() {
        return description;
    }

    public void setDescription(LangMap description) {
        this.description = description;
    }


}
