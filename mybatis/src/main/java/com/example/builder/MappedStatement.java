package com.example.builder;

public class MappedStatement {
    private String id;
    private Object parameterType;
    private Object resultType;
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getParameterType() {
        return parameterType;
    }

    public void setParameterType(Object parameterType) {
        this.parameterType = parameterType;
    }

    public Object getResultType() {
        return resultType;
    }

    public void setResultType(Object resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
