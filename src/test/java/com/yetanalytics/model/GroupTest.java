package com.yetanalytics.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yetanalytics.xapi.util.ValidationUtils;
import com.yetanalytics.xapi.model.Account;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.Group;

import jakarta.validation.Validator;

public class GroupTest {
    private Validator validator;
    private Group group;

    @BeforeEach
    public void init() {
        validator = ValidationUtils.getValidator();
        group = new Group();
    }

    @Test
    public void testAnomyousGroup() throws URISyntaxException {
        List<Agent> member = new ArrayList<>();
        Agent memberAgent = new Agent();
        memberAgent.setMbox(new URI("mailto:mem@example.com"));
        member.add(memberAgent);
        group.setMember(member);
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testMbox() throws URISyntaxException {
        group.setMbox(new URI("mailto:foo@example.com"));
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testMboxSha1Sum() {
        group.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testOpenid() throws URISyntaxException {
        group.setOpenid(new URI("http://openid.example.com"));
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testAccount() throws URISyntaxException {
        Account account = new Account();
        account.setHomePage(new URI("http://examplehomepage.com"));
        account.setName("My Account");

        group.setAccount(account);
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testInvalidAccount() {
        Account account = new Account();
        group.setAccount(account);
        // One error for empty account, one error each for missing properties
        ValidationUtils.assertInvalid(validator, group, 3);
    }

    @Test
    public void testNoIFI() { // No member array => identified group
        // One error for empty group object, one error for no IFI
        ValidationUtils.assertInvalid(validator, group, 2);
    }
    
    @Test
    public void testMultiIFI() throws URISyntaxException {
        group.setMbox(new URI("mailto:foo@example.com"));
        group.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        ValidationUtils.assertInvalid(validator, group);
    }
}
