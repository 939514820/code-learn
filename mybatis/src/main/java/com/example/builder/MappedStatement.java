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
}
