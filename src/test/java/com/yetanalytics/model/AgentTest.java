package com.yetanalytics.model;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yetanalytics.xapi.util.ValidationUtils;
import com.yetanalytics.xapi.model.Account;
import com.yetanalytics.xapi.model.Agent;

import jakarta.validation.Validator;

public class AgentTest {
    private Validator validator;
    private Agent agent;

    @BeforeEach
    public void init() {
        validator = ValidationUtils.getValidator();
        agent = new Agent();
    }

    @Test
    public void testMbox() throws URISyntaxException {
        agent.setMbox(new URI("mailto:foo@example.com"));
        ValidationUtils.assertValid(validator, agent);
    }

    @Test
    public void testInvalidMboxScheme() throws URISyntaxException {
        agent.setMbox(new URI("http://foo@example.com"));
        ValidationUtils.assertInvalid(validator, agent);
    }

    @Test
    public void testInvalidMboxAddress() throws URISyntaxException {
        agent.setMbox(new URI("mailto:not-an-email"));
        ValidationUtils.assertInvalid(validator, agent);
    }

    @Test
    public void testMboxSha1Sum() {
        agent.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        ValidationUtils.assertValid(validator, agent);
    }

    @Test
    public void testInvalidMboxSha1SumLength() {
        agent.setMbox_sha1sum("767e74eab7081c41e0b83630511139d13024966");
        ValidationUtils.assertInvalid(validator, agent);
    }

    @Test
    public void testInvalidMboxSha1SumHex() {
        agent.setMbox_sha1sum("z67e74eab7081c41e0b83630511139d130249666");
        ValidationUtils.assertInvalid(validator, agent);
    }

    @Test
    public void testOpenid() throws URISyntaxException {
        agent.setOpenid(new URI("http://openid.example.com"));
        ValidationUtils.assertValid(validator, agent);
    }

    @Test
    public void testAccount() throws URISyntaxException {
        Account account = new Account();
        account.setHomePage(new URI("http://examplehomepage.com"));
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
    public void testMultiIFI() throws URISyntaxException {
        agent.setMbox(new URI("mailto:foo@example.com"));
        agent.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        ValidationUtils.assertInvalid(validator, agent);
    }
}
