package cn.bugstack.mybatis.executor;

import cn.bugstack.mybatis.executor.statement.StatementHandler;
import cn.bugstack.mybatis.mapping.BoundSql;
import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.session.Configuration;
import cn.bugstack.mybatis.session.ResultHandler;
import cn.bugstack.mybatis.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description 简单执行器
 * @github https://github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Slf4j
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement stmt = handler.prepare(connection);
            // 处理参数
            handler.parameterize(stmt);
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            log.error("",e);
            return null;
        }
    }

}
