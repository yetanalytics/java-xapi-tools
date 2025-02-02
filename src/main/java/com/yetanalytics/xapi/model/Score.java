package com.yetanalytics.xapi.model;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* Class representation of the Score component of the 
* <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#2451-score">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class Score {

    private BigDecimal raw;
    private BigDecimal min;
    private BigDecimal max;
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
}
