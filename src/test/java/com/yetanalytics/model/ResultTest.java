package com.yetanalytics.model;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.yetanalytics.xapi.model.Result;
import com.yetanalytics.xapi.model.Score;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ResultTest {
    private Validator validator;
    private Result result;
    private Score score;

    @Before
    public void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        score = new Score();
        result = new Result();
    }

    @Test
    public void testResult() {
        score.setScaled(new BigDecimal(0.5));
        result.setScore(score);
        result.setSuccess(false);
        result.setCompletion(true);
        result.setResponse("myResponse");
        // TODO: setExtensions
        // TODO: setDuration

        assertTrue(validator.validate(result).isEmpty());
    }

    @Test
    public void testInvalidScore() {
        score.setScaled(new BigDecimal(3.0));
        result.setScore(score);
        assertEquals(1, validator.validate(result).size());
    }

}
