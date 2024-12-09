package com.yetanalytics.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.util.ValidationUtils;
import com.yetanalytics.xapi.model.Account;
import com.yetanalytics.xapi.model.Agent;
import com.yetanalytics.xapi.model.Group;

import jakarta.validation.Validator;

public class GroupTest {
    private Validator validator;
    private Group group;

    @Before
    public void init() {
        validator = ValidationUtils.getValidator();
        group = new Group();
    }

    @Test
    public void testAnomyousGroup() {
        List<Agent> member = new ArrayList<>();
        Agent memberAgent = new Agent();
        memberAgent.setMbox("mailto:mem@example.com");
        member.add(memberAgent);
        group.setMember(member);
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testMbox() {
        group.setMbox("mailto:foo@example.com");
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testMboxSha1Sum() {
        group.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testOpenid() {
        group.setOpenid("http://openid.example.com");
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testAccount() {
        Account account = new Account();
        account.setHomePage("http://examplehomepage.com");
        account.setName("My Account");

        group.setAccount(account);
        ValidationUtils.assertValid(validator, group);
    }

    @Test
    public void testInvalidAccount() {
        Account account = new Account();
        group.setAccount(account);
        ValidationUtils.assertInvalid(validator, group, 2);
    }

    @Test
    public void testNoIFI() { // No member array => identified group
        ValidationUtils.assertInvalid(validator, group);
    }
    
    @Test
    public void testMultiIFI() {
        group.setMbox("mailto:foo@example.com");
        group.setMbox_sha1sum("767e74eab7081c41e0b83630511139d130249666");
        ValidationUtils.assertInvalid(validator, group);
    }
}
