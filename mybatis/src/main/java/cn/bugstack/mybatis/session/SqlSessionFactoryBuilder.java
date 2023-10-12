package cn.bugstack.mybatis.session;

import cn.bugstack.mybatis.builder.xml.XMLConfigBuilder;
import cn.bugstack.mybatis.io.Resources;
import cn.bugstack.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
