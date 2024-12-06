package com.yetanalytics.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.Account;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class AccountTest {
    private Validator validator;
    private Account account;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        account = new Account();
    }

    @Test
    public void testValidAccount() {
        account.setHomePage("http://examplehomepage.com");
        account.setName("My Account");
        assertTrue(validator.validate(account).isEmpty());
    }

    @Test
    public void testEmptyAccount() {
        var violations = validator.validate(account);
        assertEquals(2, violations.size());
    }

}
