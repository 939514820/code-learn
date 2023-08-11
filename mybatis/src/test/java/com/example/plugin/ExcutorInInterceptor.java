package com.example.plugin;

import cn.bugstack.mybatis.executor.Executor;
import cn.bugstack.mybatis.executor.SimpleExecutor;
import cn.bugstack.mybatis.executor.statement.StatementHandler;
import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.plugin.Interceptor;
import cn.bugstack.mybatis.plugin.Intercepts;
import cn.bugstack.mybatis.plugin.Invocation;
import cn.bugstack.mybatis.plugin.Signature;
import cn.bugstack.mybatis.session.ResultHandler;
import cn.bugstack.mybatis.session.RowBounds;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;

@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "update",
        args = {MappedStatement.class, Object.class})})
public class ExcutorInInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("执行器拦截");
        SimpleExecutor executor = (SimpleExecutor) invocation.getTarget();
        Method method = executor.getClass().getMethod("update",MappedStatement.class, Object.class);
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                log.info(parameter.getName(),parameter.getType(),parameter.toString());
            }


        return invocation.proceed();
    }
}
