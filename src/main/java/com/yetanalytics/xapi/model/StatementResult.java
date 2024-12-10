package com.yetanalytics.xapi.model;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* Class representation of a StatementResult from the <a href="https://github.com/adlnet/xAPI-Spec/blob/master/xAPI-Data.md#25-retrieval-of-statements">9274.1.1 xAPI Specification</a>.
*/
@JsonInclude(Include.NON_NULL)
public class StatementResult {

    private List<Statement> statements;

    private URI more;

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public URI getMore() {
        return more;
    }

    public void setMore(URI more) {
        this.more = more;
    }
}
