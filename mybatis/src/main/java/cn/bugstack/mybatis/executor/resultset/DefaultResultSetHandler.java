package cn.bugstack.mybatis.executor.resultset;

import cn.bugstack.mybatis.executor.Executor;
import cn.bugstack.mybatis.mapping.BoundSql;
import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.reflection.MetaClass;
import cn.bugstack.mybatis.reflection.MetaObject;
import cn.bugstack.mybatis.reflection.SystemMetaObject;
import cn.bugstack.mybatis.reflection.factory.DefaultObjectFactory;
import cn.bugstack.mybatis.reflection.factory.ObjectFactory;
import cn.bugstack.mybatis.type.TypeHandlerRegistry;
import cn.bugstack.mybatis.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @author 小傅哥，微信：fustack
 * @description 默认Map结果处理器
 * @github https://github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Slf4j
public class DefaultResultSetHandler implements ResultSetHandler {

    private final BoundSql boundSql;
    private final MappedStatement mappedStatement;

    public DefaultResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        this.boundSql = boundSql;
        this.mappedStatement = mappedStatement;
    }

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        ResultSet resultSet = stmt.getResultSet();
        return resultSet2Obj(resultSet, mappedStatement.getResultType());
    }

    protected ObjectFactory objectFactory = new DefaultObjectFactory();

    private Object createResultObject(Class<?> type) throws SQLException {
        final Class<?> resultType = type;
        final MetaClass metaType = MetaClass.forClass(resultType);
        if (resultType.isInterface() || metaType.hasDefaultConstructor()) {
            // 普通的Bean对象类型
            return objectFactory.create(resultType);
        }
        throw new RuntimeException("Do not know how to create an instance of " + resultType);
    }

    // https://mp.weixin.qq.com/s/0v2ugaiGEwZcFiG04k3-hg
    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                T obj = (T) createResultObject(clazz);
//                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    // TODO 返回值不一定是object

                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
//                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
//                    Method method;
//                    // 属性值的设置用 策略模式替代
//                    if (value instanceof Timestamp) {
//                        method = clazz.getMethod(setMethod, Date.class);
//                    } else {
//                        method = clazz.getMethod(setMethod, value.getClass());
//                    }
//                    method.invoke(obj, value);
                    MetaObject metaObject = SystemMetaObject.forObject(obj);
                    if (StringUtil.isNotEmpty(columnName) && metaObject.hasSetter(columnName)) {

                        Class<?> attributeType = metaObject.getSetterType(columnName);
//                        TypeHandlerRegistry
                        TypeHandlerRegistry handlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
                        handlerRegistry.getTypeHandler(attributeType);
                        // TODO 获取类型处理器
                        metaObject.setValue(columnName, value);
                    }
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
