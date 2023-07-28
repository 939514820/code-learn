package com.example.session.defaults;

import com.example.executor.Executor;
import com.example.session.Configuration;
import com.example.session.ExecutorType;
import com.example.session.SqlSession;
import com.example.session.SqlSessionFactory;
import com.example.transaction.Transaction;
import com.example.transaction.TransactionFactory;
import com.example.transaction.enums.TransactionIsolationLevel;
import com.mysql.cj.exceptions.ExceptionFactory;


public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
         return openSessionFromDataSource(configuration.getDefaultExecutorType(), null, false);
    }
    private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level,
                                                 boolean autoCommit) {
        Transaction tx = null;
//        try {
//            final Environment environment = configuration.getEnvironment();
//            final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
//            tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
            final Executor executor = configuration.newExecutor(tx, execType);
            return new DefaultSqlSession(configuration, executor, autoCommit);
//        } catch (Exception e) {
//            closeTransaction(tx); // may have fetched a connection so lets call close()
//            throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
//        } finally {
//            ErrorContext.instance().reset();
//        }
    }
}