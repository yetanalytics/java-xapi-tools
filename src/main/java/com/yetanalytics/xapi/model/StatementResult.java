package com.yetanalytics.xapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotNull;

/**
* Class representation of a StatementResult from the <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#25-retrieval-of-statements">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class StatementResult {

    @NotNull
    private List<Statement> statements;

    private String more;

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }
}
