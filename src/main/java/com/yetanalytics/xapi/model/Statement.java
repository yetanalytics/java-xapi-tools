package com.yetanalytics.xapi.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yetanalytics.xapi.model.serializers.DateTimeSerializer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

/**
* Class representation of an Statement from the <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#24-statement-properties">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
@JsonDeserialize
public class Statement extends AbstractObject {

    private UUID id;

    @NotNull
    @Valid
    private AbstractObject actor;

    @NotNull
    @Valid
    private Verb verb;

    @Valid
    private Result result;

    @Valid
    private Context context;

    @NotNull
    @Valid
    private AbstractObject object;

    @Valid
    private AbstractActor authority;

    @JsonSerialize(using = DateTimeSerializer.class)
    private ZonedDateTime timestamp;

    @JsonSerialize(using = DateTimeSerializer.class)
    private ZonedDateTime stored;

    private String version;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    // Validation

    @JsonIgnore
    @AssertTrue
    public boolean isValidVoidingStatement() {
        if (verb != null && verb.isVoiding()) {
            return object instanceof StatementRef;
        } else {
            return true;
        }
    }

    private boolean isObjectActivity() {
        if (object != null) {
            return object instanceof Activity;
        } else {
            return false; // Invalid statement anyways
        }
    }

    @JsonIgnore
    @AssertTrue
    public boolean isValidContextRevision() {
        return (
            isObjectActivity() ||
            context == null ||
            context.getRevision() == null
        );
    }

    @JsonIgnore
    @AssertTrue
    public boolean isValidContextPlatform() {
        return (
            isObjectActivity() ||
            context == null ||
            context.getPlatform() == null
        );
    }

    // TODO: Somehow validate this on the Authority object itself
    @JsonIgnore
    @AssertTrue
    public boolean isValidAuthority() {
        return authority == null || authority.isValidAuthority();
    }

    private boolean isValidSubStmt() {
        return (
            id == null &&
            stored == null &&
            version == null &&
            authority == null &&
            !(object instanceof Statement)
        );
    }

    // TODO: Validate this on the SubStatement itself
    // (e.g. setting the objectType field)
    @JsonIgnore
    @AssertTrue
    public boolean isValidSubStatement() {
        // System.out.println("Object is Statement: " + (object instanceof Statement));
        // TODO: If object is true...
        if (object instanceof Statement) {
            Statement subStatement = (Statement) object;
            return subStatement.isValidSubStmt();
        } else {
            return true;
        }
    }

    @Override
    @JsonIgnore
    @AssertFalse
    public boolean isEmpty() {
        return (
            id == null &&
            actor == null &&
            verb == null &&
            object == null &&
            context == null &&
            result == null &&
            authority == null &&
            timestamp == null &&
            stored == null &&
            version == null &&
            attachments == null
        );
    }
}
