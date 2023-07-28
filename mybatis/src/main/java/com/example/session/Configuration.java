package com.example.session;

import com.example.bind.MapperRegistry;
import com.example.executor.Executor;
import com.example.executor.SimpleExecutor;
import com.example.mapping.MappedStatement;
import com.example.transaction.Transaction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Configuration {


    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();


    public boolean hasStatement(String statementId) {
        return mappedStatements.containsKey(statementId);
    }

    public MappedStatement getMappedStatement(String statementId) {
        return mappedStatements.get(statementId);
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void addMappers(String packageName, Class<?> superType) {
        mapperRegistry.addMappers(packageName, superType);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    /**
     * 存储代理类对象
     *
     * @param type
     * @param <T>
     */
    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public Collection<String> getMappedStatementNames() {
        buildAllStatements();
        return mappedStatements.keySet();
    }

    public Collection<MappedStatement> getMappedStatements() {
        buildAllStatements();
        return mappedStatements.values();
    }

    private void buildAllStatements() {
    }
    protected ExecutorType defaultExecutorType = ExecutorType.SIMPLE;
    public ExecutorType getDefaultExecutorType() {
        return defaultExecutorType;
    }
    //...
    public Executor newExecutor(Transaction transaction, ExecutorType executorType) {
//        executorType = executorType == null ? defaultExecutorType : executorType;
        Executor executor;
//        if (ExecutorType.BATCH == executorType) {
//            executor = new BatchExecutor(this, transaction);
//        } else if (ExecutorType.REUSE == executorType) {
//            executor = new ReuseExecutor(this, transaction);
//        } else {
        executor = new SimpleExecutor(this, transaction);
//        }
//        if (cacheEnabled) {
//            executor = new CachingExecutor(executor);
//        }
//        return (Executor) interceptorChain.pluginAll(executor);
        return executor;
    }

}
