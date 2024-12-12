package com.yetanalytics.xapi.model;

import java.net.URI;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yetanalytics.xapi.model.deserializers.AbstractActorDeserializer;

/**
* Abstract Class for serialization and deserialization of xAPI Actors
*/
@JsonDeserialize(using = AbstractActorDeserializer.class)
public abstract class AbstractActor extends AbstractObject {
    
    private String name;

    //IFI
    private URI mbox;
    // TODO: Validate that mbox_sha1sum is a SHA1, 40-char hex string
    private String mbox_sha1sum;
    private URI openid;
    private Account account;
    
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
}
