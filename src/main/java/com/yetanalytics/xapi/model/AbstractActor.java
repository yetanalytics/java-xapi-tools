package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.net.URI;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.AbstractActorDeserializer;
import com.yetanalytics.xapi.validation.MboxUri;
import com.yetanalytics.xapi.validation.Sha1Sum;

import jakarta.validation.Valid;

/**
* Abstract Class for serialization and deserialization of xAPI Actors
*/
@JsonDeserialize(using = AbstractActorDeserializer.class)
public abstract class AbstractActor extends AbstractObject {
    
    private String name;

    // IFIs

    @MboxUri
    private URI mbox;
    @Sha1Sum
    private String mbox_sha1sum;
    
    private URI openid;
    
    @Valid
    private Account account;
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public URI getMbox() {
        return mbox;
    }
    public void setMbox(URI mbox) {
        this.mbox = mbox;
    }
    
    public String getMbox_sha1sum() {
        return mbox_sha1sum;
    }
    public void setMbox_sha1sum(String mbox_sha1sum) {
        this.mbox_sha1sum = mbox_sha1sum;
    }
    
    public URI getOpenid() {
        return openid;
    }
    public void setOpenid(URI openid) {
        this.openid = openid;
    }
    
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    // Validation
    protected int countIFIs() {
        int notNullCount = 0;
        if (mbox != null) {
            ++notNullCount;
        }
        if (mbox_sha1sum != null) {
            ++notNullCount;
        }
        if (openid != null) {
            ++notNullCount;
        }
        if (account != null) {
            ++notNullCount;
        }
        return notNullCount;
    }

    public abstract boolean isValidAuthority();

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return (
            mbox == null && mbox_sha1sum == null &&
            openid == null && account == null
        );
    }
}
