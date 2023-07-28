package com.example.mapping;

import com.example.bind.SqlCommandType;
import com.example.session.BoundSql;
import com.example.session.Configuration;
import com.example.session.ParameterMapping;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import java.util.List;

public class MappedStatement {
    private String id;
    private Object parameterType;
    private Object resultType;
    private String sql;
    //    private SqlSource sqlSource;
    private SqlCommandType sqlCommandType;
    private Configuration configuration;

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

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public BoundSql getBoundSql(Object parameterObject) {
//        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
//        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
//        if (parameterMappings == null || parameterMappings.isEmpty()) {
//            boundSql = new BoundSql(configuration, boundSql.getSql(), parameterMap.getParameterMappings(), parameterObject);
//        }
//
//        // check for nested result maps in parameter mappings (issue #30)
//        for (ParameterMapping pm : boundSql.getParameterMappings()) {
//            String rmId = pm.getResultMapId();
//            if (rmId != null) {
//                ResultMap rm = configuration.getResultMap(rmId);
//                if (rm != null) {
//                    hasNestedResultMaps |= rm.hasNestedResultMaps();
//                }
//            }
//        }

        return null;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
