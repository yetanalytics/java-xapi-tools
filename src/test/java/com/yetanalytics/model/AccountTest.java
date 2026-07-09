package com.yetanalytics.model;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yetanalytics.xapi.util.ValidationUtils;
import com.yetanalytics.xapi.model.Account;

import jakarta.validation.Validator;

public class AccountTest {
    private Validator validator;
    private Account account;

    @BeforeEach
    public void init() {
        validator = ValidationUtils.getValidator();
        account = new Account();
    }

    @Test
    public void testValidAccount() throws URISyntaxException {
        account.setHomePage(new URI("http://examplehomepage.com"));
        account.setName("My Account");
        ValidationUtils.assertValid(validator, account);
    }

    @Test
    public void testEmptyAccount() {
        ValidationUtils.assertInvalid(validator, account, 3);
    }
}
