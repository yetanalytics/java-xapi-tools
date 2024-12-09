package com.yetanalytics.model;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.util.ValidationUtils;
import com.yetanalytics.xapi.model.Score;

import jakarta.validation.Validator;

public class ScoreTest {
    private Validator validator;
    private Score score;

    @Before
    public void init() {
        validator = ValidationUtils.getValidator();
        score = new Score();
    }

    @Test
    public void testScore() {
        ValidationUtils.assertValid(validator, score); // TODO: Make invalid

        score.setMin(new BigDecimal(0));
        score.setMax(new BigDecimal(100));
        score.setRaw(new BigDecimal(50));
        score.setScaled(new BigDecimal(0));
        ValidationUtils.assertValid(validator, score);
    }

    @Test
    public void testScaledTooSmall() {
        score.setScaled(new BigDecimal(-2.0));
        ValidationUtils.assertInvalid(validator, score);
    }

    @Test
    public void testScaledTooBig() {
        score.setScaled(new BigDecimal(2.0));
        ValidationUtils.assertInvalid(validator, score);
    }

    @Test
    public void testMinBiggerThanMax() {
        score.setMax(new BigDecimal(0.4));
        score.setMin(new BigDecimal(0.6));
        ValidationUtils.assertInvalid(validator, score);
    }

    @Test
    public void testRawBiggerThanMax() {
        score.setMax(new BigDecimal(0.4));
        score.setRaw(new BigDecimal(0.6));
        ValidationUtils.assertInvalid(validator, score);
    }

    @Test
    public void testMinBiggerThanRaw() {
        score.setRaw(new BigDecimal(0.4));
        score.setMin(new BigDecimal(0.6));
        ValidationUtils.assertInvalid(validator, score);
    }
}
