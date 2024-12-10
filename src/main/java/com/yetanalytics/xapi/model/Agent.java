package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;

/**
* A concrete class representation of the Agent Component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2421-when-the-actor-objecttype-is-agent">9274.1.1 xAPI Specification</a>.
* This class has no fields because it only contains what it inherits from AbstractActor.
*/
@JsonInclude(Include.NON_NULL)
@JsonDeserialize
public class Agent extends AbstractActor {

    // Validation

    @Override
    @JsonIgnore
    @AssertFalse
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     * Assertion that the Agent has only 1 Inverse Functional Identifier (IFI).
     * @return true if the Agent has exactly 1 IFI, false otherwise
     */
    @JsonIgnore
    @AssertTrue
    public boolean isIdentifiedAgent() {
        return countIFIs() == 1;
    }

    @Override
    @JsonIgnore
    public boolean isValidAuthority() {
        return true;
    }
}
