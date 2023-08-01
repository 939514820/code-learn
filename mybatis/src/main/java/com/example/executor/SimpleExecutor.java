/*
 *    Copyright 2009-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.example.executor;

import com.example.datasource.Connector;
import com.example.mapping.MappedStatement;
import com.example.session.Configuration;
import com.example.session.RowBounds;
import com.example.transaction.Transaction;
import com.example.type.JdbcType;
import lombok.extern.slf4j.Slf4j;
import sun.plugin2.main.server.ResultHandler;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j
/**
 * @author Clinton Begin
 */
public class SimpleExecutor extends BaseExecutor {
    private final List<String> columnNames = new ArrayList<>();
    private final List<String> classNames = new ArrayList<>();
    private final List<JdbcType> jdbcTypes = new ArrayList<>();

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    public int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
        Statement stmt = null;
//    try {
//      Configuration configuration = ms.getConfiguration();
//      StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, RowBounds.DEFAULT, null, null);
//      stmt = prepareStatement(handler, ms.getStatementLog());
//      return handler.update(stmt);
//    } finally {
//      closeStatement(stmt);
//    }
        return 0;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
        Statement stmt = null;
        try {
            // 获取数据源的连接
            Connector connector = new Connector();
            PreparedStatement statement = connector.getConnection().prepareStatement(ms.getSql());
            ResultSet resultSet = statement.executeQuery();
            // handler抽象出来去处理
            return  resultSet2Obj(resultSet, ms.getResultType().getClass());
        }
        finally {
            closeStatement(stmt);
        }
    }


    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                log.info(clazz.getName());
                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method;
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    // 执行实例的方法
                    method.invoke(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }


}
