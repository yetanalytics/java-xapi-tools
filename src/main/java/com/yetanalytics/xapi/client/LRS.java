package com.yetanalytics.xapi.client;

import java.net.URI;

public class LRS {

    public LRS (String host, String key, String secret, Integer batchSize){
        
        if(key == null || key.isEmpty())
            throw new IllegalArgumentException(
                "LRS auth key must be present.");
        this.key = key;

        if(key == null || key.isEmpty())
            throw new IllegalArgumentException(
                "LRS auth secret must be present.");
        this.secret = secret;
        
        //Host Validation
        this.host = URI.create(host);
        if (this.host.getPath() == null) {
            throw new IllegalArgumentException(
                "LRS host must have path prefix.");
        } else if (!this.host.getPath().endsWith("/")) { 
            this.host = URI.create(host.concat("/"));
        }

        if(batchSize != null && batchSize > 0){
            this.batchSize = batchSize;
        }
    }

    public LRS (String host, String key, String secret){
        this(host, key, secret, null);
    }

    private URI host;

    private String key;

    private String secret;

    private Integer batchSize = 50;

    public URI getHost() {
        return host;
    }

    public void setHost(URI host) {
        this.host = host;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }
    
}
