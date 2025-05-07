package com.yetanalytics.xapi.client.filters;

public enum StatementFormat {
    IDS("ids"),
    EXACT("exact"),
    CANONICAL("canonical");

    private String displayName;

    StatementFormat(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
