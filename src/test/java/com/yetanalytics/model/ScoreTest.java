package com.yetanalytics.model;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.Score;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ScoreTest {
    private Validator validator;
    private Score score;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        score = new Score();
    }

    @Test
    public void testScore() {
        assertTrue(validator.validate(score).isEmpty());

        score.setMin(new BigDecimal(0));
        score.setMax(new BigDecimal(100));
        score.setRaw(new BigDecimal(50));
        score.setScaled(new BigDecimal(0));
        assertTrue(validator.validate(score).isEmpty());
    }

    @Test
    public void testScaledTooSmall() {
        score.setScaled(new BigDecimal(-2.0));
        var violations = validator.validate(score);
        assertEquals(1, violations.size());
    }

    @Test
    public void testScaledTooBig() {
        score.setScaled(new BigDecimal(2.0));
        var violations = validator.validate(score);
        assertEquals(1, violations.size());
    }

    @Test
    public void testMinBiggerThanMax() {
        score.setMax(new BigDecimal(0.4));
        score.setMin(new BigDecimal(0.6));
        var violations = validator.validate(score);
        assertEquals(1, violations.size());
    }

    @Test
    public void testRawBiggerThanMax() {
        score.setMax(new BigDecimal(0.4));
        score.setRaw(new BigDecimal(0.6));
        var violations = validator.validate(score);
        assertEquals(1, violations.size());
    }

    @Test
    public void testMinBiggerThanRaw() {
        score.setRaw(new BigDecimal(0.4));
        score.setMin(new BigDecimal(0.6));
        var violations = validator.validate(score);
        assertEquals(1, violations.size());
    }
}
