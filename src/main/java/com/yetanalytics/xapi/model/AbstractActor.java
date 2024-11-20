package com.yetanalytics.xapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonIgnoreProperties(ignoreUnknown = true)

@JsonTypeInfo(use = Id.NAME, property = "objectType", include = As.PROPERTY, defaultImpl = Agent.class)
@JsonSubTypes(value = { 
        @JsonSubTypes.Type(value = Agent.class, name = "Agent"),
        @JsonSubTypes.Type(value = Group.class, name = "Group") 
})
public abstract class AbstractActor {
    
    private String name;
    
    private ActorObjectType objectType;

    //IFI
    private String mbox;
    private String mbox_sha1sum;
    private String openid;
    private Account account;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ActorObjectType getObjectType() {
        return objectType;
    }
    public void setObjectType(ActorObjectType objectType) {
        this.objectType = objectType;
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
}
