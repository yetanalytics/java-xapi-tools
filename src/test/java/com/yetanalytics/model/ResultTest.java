package com.yetanalytics.model;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yetanalytics.xapi.util.ValidationUtils;
import com.yetanalytics.xapi.model.Extensions;
import com.yetanalytics.xapi.model.Result;
import com.yetanalytics.xapi.model.Score;
import com.yetanalytics.xapi.model.XapiDuration;

import jakarta.validation.Validator;

public class ResultTest {
    private Validator validator;
    private Result result;
    private Score score;

    @BeforeEach
    public void init() {
        validator = ValidationUtils.getValidator();
        score = new Score();
        result = new Result();
    }

    @Test
    public void testResult() throws URISyntaxException {
        score.setScaled(new BigDecimal(0.5));
        result.setScore(score);
        result.setSuccess(false);
        result.setCompletion(true);
        result.setResponse("myResponse");
        Map<URI, Object> extMap = Map.of(
            new URI("http://yetanalytics.com/exts/1"), 1
        );
        result.setExtensions(new Extensions(extMap));
        result.setDuration(new XapiDuration("PT1H"));

        ValidationUtils.assertValid(validator, result);
    }

    @Test
    public void testInvalidScore() {
        score.setScaled(new BigDecimal(3.0));
        result.setScore(score);
        ValidationUtils.assertInvalid(validator, result);
    }
}
