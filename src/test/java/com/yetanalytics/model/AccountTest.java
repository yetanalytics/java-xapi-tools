package com.yetanalytics.model;

import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.util.ValidationUtils;
import com.yetanalytics.xapi.model.Account;

import jakarta.validation.Validator;

public class AccountTest {
    private Validator validator;
    private Account account;

    @Before
    public void init() {
        validator = ValidationUtils.getValidator();
        account = new Account();
    }

    @Test
    public void testValidAccount() {
        account.setHomePage("http://examplehomepage.com");
        account.setName("My Account");
        ValidationUtils.assertValid(validator, account);
    }

    @Test
    public void testEmptyAccount() {
        ValidationUtils.assertInvalid(validator, account, 2);
    }
}
