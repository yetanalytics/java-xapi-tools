package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.AbstractActorDeserializer;

/**
* Abstract Class for serialization and deserialization of xAPI Actors
*/
@JsonDeserialize(using = AbstractActorDeserializer.class)
public abstract class AbstractActor extends AbstractObject {
    
    private String name;

    // IFIs
    private String mbox;
    private String mbox_sha1sum;
    private String openid;
    private Account account;
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMbox() {
        return mbox;
    }
    public void setMbox(String mbox) {
        this.mbox = mbox;
    }
    
    public String getMbox_sha1sum() {
        return mbox_sha1sum;
    }
    public void setMbox_sha1sum(String mbox_sha1sum) {
        this.mbox_sha1sum = mbox_sha1sum;
    }
    
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
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
}
