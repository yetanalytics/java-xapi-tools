package com.yetanalytics.model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.Account;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.Group;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class GroupTest {
    private Validator validator;
    private Group group;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        group = new Group();
    }

    @Test
    public void testAnomyousGroup() {
        List<Agent> member = new ArrayList<>();
        Agent memberAgent = new Agent();
        memberAgent.setMbox("mailto:mem@example.com");
        member.add(memberAgent);
        group.setMember(member);
        assertTrue(validator.validate(group).isEmpty());
    }

    @Test
    public void testMbox() {
        group.setMbox("mailto:foo@example.com");
        assertTrue(validator.validate(group).isEmpty());
    }

    @Test
    public void testMboxSha1Sum() {
        group.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        assertTrue(validator.validate(group).isEmpty());
    }

    @Test
    public void testOpenid() {
        group.setOpenid("http://openid.example.com");
        assertTrue(validator.validate(group).isEmpty());
    }

    @Test
    public void testAccount() {
        Account account = new Account();
        account.setHomePage("http://examplehomepage.com");
        account.setName("My Account");

        group.setAccount(account);
        assertTrue(validator.validate(group).isEmpty());
    }

    @Test
    public void testNoIFI() { // No member array => identified group
        var violations = validator.validate(group);
        assertEquals(1, violations.size());
    }
    
    @Test
    public void testMultiIFI() {
        group.setMbox("mailto:foo@example.com");
        group.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        var violations = validator.validate(group);
        assertEquals(1, violations.size());
    }

}
