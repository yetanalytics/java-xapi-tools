package com.yetanalytics.xapi.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.AssertFalse;

/**
* Class representation of the Context Component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#246-context">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class Context implements JSONObject {

    private UUID registration;
    private AbstractActor instructor;
    private Group team;
    private ContextActivities contextActivities;
    private String revision;
    private String platform;
    private String language;
    private StatementRef statement;
    private Extensions extensions;
    
    public UUID getRegistration() {
        return registration;
    }
    public void setRegistration(UUID registration) {
        this.registration = registration;
    }
    public AbstractActor getInstructor() {
        return instructor;
    }
    public void setInstructor(AbstractActor instructor) {
        this.instructor = instructor;
    }
    public Group getTeam() {
        return team;
    }
    public void setTeam(Group team) {
        this.team = team;
    }
    public ContextActivities getContextActivities() {
        return contextActivities;
    }
    public void setContextActivities(ContextActivities contextActivities) {
        this.contextActivities = contextActivities;
    }
    public String getRevision() {
        return revision;
    }
    public void setRevision(String revision) {
        this.revision = revision;
    }
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public StatementRef getStatement() {
        return statement;
    }
    public void setStatement(StatementRef statement) {
        this.statement = statement;
    }
    public Extensions getExtensions() {
        return extensions;
    }
    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    @Override
    @AssertFalse
    public boolean isEmpty() {
        return (
            registration == null &&
            instructor == null &&
            team == null &&
            contextActivities == null &&
            revision == null &&
            platform == null &&
            language == null &&
            statement == null &&
            extensions == null
        );
    }
}
