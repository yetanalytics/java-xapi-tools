package com.yetanalytics.model;

import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.util.ValidationUtils;
import com.yetanalytics.xapi.model.Account;
import com.yetanalytics.xapi.model.Agent;

import jakarta.validation.Validator;

public class AgentTest {
    private Validator validator;
    private Agent agent;

    @Before
    public void init() {
        validator = ValidationUtils.getValidator();
        agent = new Agent();
    }

    @Test
    public void testMbox() {
        agent.setMbox("mailto:foo@example.com");
        ValidationUtils.assertValid(validator, agent);
    }

    @Test
    public void testMboxSha1Sum() {
        agent.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        ValidationUtils.assertValid(validator, agent);
    }

    @Test
    public void testOpenid() {
        agent.setOpenid("http://openid.example.com");
        ValidationUtils.assertValid(validator, agent);
    }

    @Test
    public void testAccount() {
        Account account = new Account();
        account.setHomePage("http://examplehomepage.com");
        account.setName("My Account");

        agent.setAccount(account);
        ValidationUtils.assertValid(validator, agent);
    }

    @Test
    public void testInvalidAccount() {
        Account account = new Account();
        agent.setAccount(account);
        // One error for empty account, one error each for missing properties
        ValidationUtils.assertInvalid(validator, agent, 3);
    }

    @Test
    public void testNoIFI() {
        // One error for empty agent, one error for missing IFI
        ValidationUtils.assertInvalid(validator, agent, 2);
    }
    
    @Test
    public void testMultiIFI() {
        agent.setMbox("mailto:foo@example.com");
        agent.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        ValidationUtils.assertInvalid(validator, agent);
    }
}
