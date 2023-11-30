package com.example.plugin;

import cn.bugstack.mybatis.executor.statement.StatementHandler;
import cn.bugstack.mybatis.mapping.BoundSql;
import cn.bugstack.mybatis.plugin.Interceptor;
import cn.bugstack.mybatis.plugin.Intercepts;
import cn.bugstack.mybatis.plugin.Invocation;
import cn.bugstack.mybatis.plugin.Signature;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.Properties;

@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class UserStatementPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取StatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 获取SQL信息
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        // 输出SQL
        log.info("拦截SQL：" + sql);
        // 放行
        return invocation.proceed();
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("参数输出：" + properties.getProperty("test00"));
    }

}
