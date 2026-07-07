package com.yetanalytics.xapi.validation;

import java.net.URI;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MboxUriValidator implements ConstraintValidator<MboxUri, URI> {
    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"
    );

    @Override
    public boolean isValid(URI value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String scheme = value.getScheme();
        String address = value.getSchemeSpecificPart();

        return (
            "mailto".equalsIgnoreCase(scheme) &&
            value.getFragment() == null &&
            value.getQuery() == null &&
            address != null &&
            EMAIL_ADDRESS_PATTERN.matcher(address).matches()
        );
    }
}
