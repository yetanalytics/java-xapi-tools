package com.yetanalytics.xapi.validation;

import com.yetanalytics.xapi.util.HashUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Sha1SumValidator implements ConstraintValidator<Sha1Sum, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || HashUtils.isSha1Hex(value);
    }
}
