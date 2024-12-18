package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* A concrete class representation of the Agent Component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2421-when-the-actor-objecttype-is-agent">9274.1.1 xAPI Specification</a>.
* This class has no fields because it only contains what it inherits from AbstractActor.
*/
@JsonInclude(Include.NON_NULL)
@JsonDeserialize
public class Agent extends AbstractActor {

}
