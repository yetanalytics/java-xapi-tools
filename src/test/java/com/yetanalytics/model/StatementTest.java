package com.yetanalytics.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semver4j.Semver;

import com.yetanalytics.xapi.util.ValidationUtils;
import com.yetanalytics.xapi.model.Account;
import com.yetanalytics.xapi.model.Activity;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.Context;
import com.yetanalytics.xapi.model.Group;
import com.yetanalytics.xapi.model.Statement;
import com.yetanalytics.xapi.model.StatementRef;
import com.yetanalytics.xapi.model.Verb;

import jakarta.validation.Validator;

public class StatementTest {
    private Validator validator;
    private Statement statement;

    private StatementRef genericStatementRef() {
        UUID id = UUID.fromString("00000000-4000-8000-0000-000000000000");
        StatementRef statementRef = new StatementRef();
        statementRef.setId(id);
        return statementRef;
    }

    @BeforeEach
    public void init() throws URISyntaxException {
        validator = ValidationUtils.getValidator();
        statement = new Statement();

        // Valid statement by default
        Agent actor = new Agent();
        actor.setMbox(new URI("mailto:foo@example.com"));

        Verb verb = new Verb();
        verb.setId(new URI("http://example.org/verb"));

        Activity object = new Activity();
        object.setId(new URI("http://example.org/object"));

        statement.setActor(actor);
        statement.setVerb(verb);
        statement.setObject(object);
    }

    @Test
    public void testStatement() {
        ValidationUtils.assertValid(validator, statement);
    }

    @Test
    public void testEmptyStatement() {
        statement.setActor(null);
        statement.setVerb(null);
        statement.setObject(null);
        // One error for empty statement, one error each for missing property
        
        ValidationUtils.assertInvalid(validator, statement, 4);
    }

    @Test
    public void testVoidingStatement() throws URISyntaxException {
        Verb voidingVerb = new Verb();
        voidingVerb.setId(new URI(Verb.VOIDING_VERB_IRI));

        statement.setVerb(voidingVerb);
        ValidationUtils.assertInvalid(validator, statement);

        StatementRef statementRef = genericStatementRef();
        statement.setObject(statementRef);

        ValidationUtils.assertValid(validator, statement);
    }

    @Test
    public void testValidContextRevision() {
        Context context = new Context();
        context.setRevision("myRevision");
        statement.setContext(context);
        ValidationUtils.assertValid(validator, statement);
        assertTrue(validator.validate(statement).isEmpty());

        StatementRef statementRef = genericStatementRef();
        statement.setObject(statementRef);

        ValidationUtils.assertInvalid(validator, statement);
    }

    @Test
    public void testValidContextPlatform() {
        Context context = new Context();
        context.setRevision("myPlatform");
        statement.setContext(context);
        assertTrue(validator.validate(statement).isEmpty());

        StatementRef statementRef = genericStatementRef();
        statement.setObject(statementRef);

        ValidationUtils.assertInvalid(validator, statement);
    }

    @Test
    public void testValidAuthority() throws URISyntaxException {
        Account authAccount = new Account();
        authAccount.setHomePage(new URI("http://myauthority.com"));
        authAccount.setName("My Authority");
        
        // Agent authority is always valid
        Agent agentAuthority = new Agent();
        agentAuthority.setAccount(authAccount);
        statement.setAuthority(agentAuthority);
        assertTrue(validator.validate(statement).isEmpty());

        // Group authority fails if there is not two members
        Group groupAuthority = new Group();
        List<Agent> groupAuthMember = new ArrayList<>();
        groupAuthMember.add(agentAuthority);
        groupAuthority.setMember(groupAuthMember);
        statement.setAuthority(groupAuthority);
        ValidationUtils.assertInvalid(validator, statement);

        // Need to add second member
        Agent nonConsumer = new Agent();
        nonConsumer.setMbox(new URI("mailto:someagent@example.com"));
        groupAuthMember.add(nonConsumer);
        assertTrue(validator.validate(statement).isEmpty());

        // At least one member needs to have an Account
        groupAuthMember.set(0, nonConsumer);
        ValidationUtils.assertInvalid(validator, statement);

        // Cannot have three (or more) members
        groupAuthMember.set(0, agentAuthority);
        groupAuthMember.add(nonConsumer);
        ValidationUtils.assertInvalid(validator, statement);
    }

    @Test
    public void testValidSubStatement() throws URISyntaxException {
        Statement subStatement = new Statement();
        Agent actor = new Agent();
        actor.setMbox(new URI("mailto:bar@example.com"));
        Verb verb = new Verb();
        verb.setId(new URI("http://example.org/verb2"));
        Activity object = new Activity();
        object.setId(new URI("http://example.org/object2"));
        subStatement.setActor(actor);
        subStatement.setVerb(verb);
        subStatement.setObject(object);
        statement.setObject(subStatement);
        assertTrue(statement.getObject() instanceof Statement);
        assertTrue(validator.validate(statement).isEmpty());

        UUID id = UUID.fromString("00000000-4000-8000-0000-000000000000");
        subStatement.setId(id);
        ValidationUtils.assertInvalid(validator, statement);

        // TODO: test Stored presence

        Semver version = new Semver("1.0.3");
        subStatement.setId(null);
        subStatement.setVersion(version);
        ValidationUtils.assertInvalid(validator, statement);

        Agent authority = new Agent();
        authority.setMbox(new URI("mailto:myauthority@example.com"));
        subStatement.setVersion(null);
        subStatement.setAuthority(authority);
        ValidationUtils.assertInvalid(validator, statement);

        Statement subSubStatement = new Statement();
        Agent actor2 = new Agent();
        actor2.setMbox(new URI("mailto:baz@example.com"));
        Verb verb2 = new Verb();
        verb2.setId(new URI("http://example.org/verb3"));
        Activity object2 = new Activity();
        object2.setId(new URI("http://example.org/object3"));
        subSubStatement.setActor(actor2);
        subSubStatement.setVerb(verb2);
        subSubStatement.setObject(object2);
        subStatement.setAuthority(null);
        subStatement.setObject(subStatement);
        // TODO: Dig deeper why this is 2 errors and not 1
        ValidationUtils.assertInvalid(validator, statement, 2);
    }
}
