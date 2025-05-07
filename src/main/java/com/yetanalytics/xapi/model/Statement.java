package com.yetanalytics.xapi.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.semver4j.Semver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yetanalytics.xapi.model.serializers.DateTimeSerializer;
import com.yetanalytics.xapi.model.serializers.SemverSerializer;

/**
* Class representation of an Statement from the <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#24-statement-properties">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
@JsonDeserialize
public class Statement extends AbstractObject {

    private UUID id;

    private AbstractObject actor;

    private Verb verb;

    private Result result;

    private Context context;

    private AbstractObject object;

    private AbstractActor authority;

    @JsonSerialize(using = DateTimeSerializer.class)
    private ZonedDateTime timestamp;

    @JsonSerialize(using = DateTimeSerializer.class)
    private ZonedDateTime stored;

    @JsonSerialize(using = SemverSerializer.class)
    private Semver version;

    private List<Attachment> attachments;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AbstractObject getActor() {
        return actor;
    }

    public void setActor(AbstractObject actor) {
        this.actor = actor;
    }

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public AbstractObject getObject() {
        return object;
    }

    public void setObject(AbstractObject object) {
        this.object = object;
    }

    public AbstractActor getAuthority() {
        return authority;
    }

    public void setAuthority(AbstractActor authority) {
        this.authority = authority;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ZonedDateTime getStored() {
        return stored;
    }

    public void setStored(ZonedDateTime stored) {
        this.stored = stored;
    }

    public Semver getVersion() {
        return version;
    }

    public void setVersion(Semver version) {
        this.version = version;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
