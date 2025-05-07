package com.yetanalytics.xapi.client.filters;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yetanalytics.xapi.model.AbstractActor;
import com.yetanalytics.xapi.util.Mapper;

public class StatementFilters {

    private URI verb;

    private String agent;

    private URI activity;

    private UUID statementId;

    private UUID voidedStatementId;

    private UUID registration;

    private Boolean relatedActivities;

    private Boolean relatedAgents;

    private ZonedDateTime since;

    private ZonedDateTime until;

    private Integer limit;

    private StatementFormat format;

    private Boolean ascending;

    public URI addQueryToUri(URI uri) {
        URIBuilder builder = new URIBuilder(uri);
        
        if(verb != null) builder.addParameter("verb", verb.toString());

        if(agent != null) builder.addParameter("agent", agent);

        if(activity != null) 
            builder.addParameter("activity", activity.toString());
        
        if(statementId != null) 
            builder.addParameter("statementId", statementId.toString());

        if(voidedStatementId != null) 
            builder.addParameter("voidedStatementId", 
                voidedStatementId.toString());

        if(registration != null) 
            builder.addParameter("registration", registration.toString());

        if(relatedActivities != null && relatedActivities) 
            builder.addParameter("related_activities", "true");

        if(relatedAgents != null && relatedAgents) 
            builder.addParameter("related_agents", "true");

        if(since != null) builder.addParameter("since", since.toString());

        if(until != null) builder.addParameter("until", until.toString());

        if(limit != null) builder.addParameter("limit", limit.toString());

        if(format != null) builder.addParameter("format", format.toString());

        if(ascending != null && ascending)
            builder.addParameter("ascending", "true");

        try {
            return builder.build();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Could not form query params: ", e);
        }
    }

    public URI getVerb() {
        return verb;
    }

    public void setVerb(URI verb) {
        this.verb = verb;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public void setAgent(AbstractActor actor) throws JsonProcessingException {
        this.agent = Mapper.getMapper().writeValueAsString(actor); 
    }

    public URI getActivity() {
        return activity;
    }

    public void setActivity(URI activity) {
        this.activity = activity;
    }

    public UUID getStatementId() {
        return statementId;
    }

    public void setStatementId(UUID statementId) {
        this.statementId = statementId;
    }

    public UUID getVoidedStatementId() {
        return voidedStatementId;
    }

    public void setVoidedStatementId(UUID voidedStatementId) {
        this.voidedStatementId = voidedStatementId;
    }

    public UUID getRegistration() {
        return registration;
    }

    public void setRegistration(UUID registration) {
        this.registration = registration;
    }

    public Boolean getRelatedActivities() {
        return relatedActivities;
    }

    public void setRelatedActivities(Boolean relatedActivities) {
        this.relatedActivities = relatedActivities;
    }

    public Boolean getRelatedAgents() {
        return relatedAgents;
    }

    public void setRelatedAgents(Boolean relatedAgents) {
        this.relatedAgents = relatedAgents;
    }

    public ZonedDateTime getSince() {
        return since;
    }

    public void setSince(ZonedDateTime since) {
        this.since = since;
    }

    public ZonedDateTime getUntil() {
        return until;
    }

    public void setUntil(ZonedDateTime until) {
        this.until = until;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public StatementFormat getFormat() {
        return format;
    }

    public void setFormat(StatementFormat format) {
        this.format = format;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

}
