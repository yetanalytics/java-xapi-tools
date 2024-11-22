package com.yetanalytics.xapi.model;

public class Score {

    private Double raw;
    private Double min;
    private Double max;
    private Double scaled;

    public Double getRaw() {
        return raw;
    }
    public void setRaw(Double raw) {
        this.raw = raw;
    }
    public Double getMin() {
        return min;
    }
    public void setMin(Double min) {
        this.min = min;
    }
    public Double getMax() {
        return max;
    }
    public void setMax(Double max) {
        this.max = max;
    }
    public Double getScaled() {
        return scaled;
    }
    public void setScaled(Double scaled) {
        this.scaled = scaled;
    }
}
