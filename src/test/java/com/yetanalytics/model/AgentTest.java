package com.yetanalytics.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.Account;
import com.yetanalytics.xapi.model.Agent;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class AgentTest {
    private Validator validator;
    private Agent agent;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        agent = new Agent();
    }

    @Test
    public void testMbox() {
        agent.setMbox("mailto:foo@example.com");
        assertTrue(validator.validate(agent).isEmpty());
    }

    @Test
    public void testMboxSha1Sum() {
        agent.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        assertTrue(validator.validate(agent).isEmpty());
    }

    @Test
    public void testOpenid() {
        agent.setOpenid("http://openid.example.com");
        assertTrue(validator.validate(agent).isEmpty());
    }

    @Test
    public void testAccount() {
        Account account = new Account();
        account.setHomePage("http://examplehomepage.com");
        account.setName("My Account");

        agent.setAccount(account);
        assertTrue(validator.validate(agent).isEmpty());
    }

    @Test
    public void testInvalidAccount() {
        Account account = new Account();
        agent.setAccount(account);
        var violations = validator.validate(agent);
        assertEquals(2, violations.size());
    }

    @Test
    public void testNoIFI() {
        var violations = validator.validate(agent);
        assertEquals(1, violations.size());
    }
    
    @Test
    public void testMultiIFI() {
        agent.setMbox("mailto:foo@example.com");
        agent.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        var violations = validator.validate(agent);
        assertEquals(1, violations.size());
    }
}
