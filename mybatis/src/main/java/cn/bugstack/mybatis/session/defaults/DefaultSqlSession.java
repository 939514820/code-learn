package cn.bugstack.mybatis.session.defaults;

import cn.bugstack.mybatis.executor.Executor;
import cn.bugstack.mybatis.mapping.BoundSql;
import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.session.Configuration;
import cn.bugstack.mybatis.session.RowBounds;
import cn.bugstack.mybatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return this.selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement(statement);
        BoundSql boundSql = ms.getSqlSource().getBoundSql(parameter);

        List<T> list = executor.query(ms, parameter, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER, boundSql);
        return list.get(0);
    }

    @Override
    public <T> List<T> selectList(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement(statement);
        BoundSql boundSql = ms.getSqlSource().getBoundSql(parameter);

        List<T> list = executor.query(ms, parameter, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER, boundSql);
        return list;
    }

    @Override
    public int insert(String statement, Object parameter) throws SQLException {
        MappedStatement ms = configuration.getMappedStatement(statement);
        return executor.update(ms, parameter);
    }

    @Override
    public int update(String statement, Object parameter) throws SQLException {
        MappedStatement ms = configuration.getMappedStatement(statement);
        return executor.update(ms, parameter);
    }

    @Override
    public Object delete(String statement, Object parameter) throws SQLException {
        MappedStatement ms = configuration.getMappedStatement(statement);
        return executor.update(ms, parameter);
    }

    @Override
    public void commit() throws SQLException {
        executor.commit(true);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

}