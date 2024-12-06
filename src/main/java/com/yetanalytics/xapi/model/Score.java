package com.yetanalytics.xapi.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

/**
* Class representation of the Score component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2451-score">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class Score {

    private BigDecimal raw;
    private BigDecimal min;
    private BigDecimal max;

    @DecimalMax(value = "1.0")
    @DecimalMin(value = "-1.0")
    private BigDecimal scaled;

    public BigDecimal getRaw() {
        return raw;
    }
    public void setRaw(BigDecimal raw) {
        this.raw = raw;
    }
    public BigDecimal getMin() {
        return min;
    }
    public void setMin(BigDecimal min) {
        this.min = min;
    }
    public BigDecimal getMax() {
        return max;
    }
    public void setMax(BigDecimal max) {
        this.max = max;
    }
    public BigDecimal getScaled() {
        return scaled;
    }
    public void setScaled(BigDecimal scaled) {
        this.scaled = scaled;
    }

    // Validation

    @AssertTrue
    public boolean isMinLessThanRaw() {
        if (raw != null && min != null) {
            return min.compareTo(raw) < 0;
        } else {
            return true;
        }
    }

    @AssertTrue
    public boolean isRawLessThanMax() {
        if (raw != null && max != null) {
            return raw.compareTo(max) < 0;
        } else {
            return true;
        }
    }

    @AssertTrue
    public boolean isMinLessThanMax() {
        if (min != null && max != null) {
            return min.compareTo(max) < 0;
        } else {
            return true;
        }
    }
}
