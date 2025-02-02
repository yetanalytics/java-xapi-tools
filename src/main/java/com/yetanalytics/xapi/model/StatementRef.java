package com.yetanalytics.xapi.model;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* Class representation of the StatementRef component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#statement-references">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
@JsonDeserialize
public class StatementRef extends AbstractObject {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
