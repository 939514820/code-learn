package com.example.dbrouter.config;

import com.example.dbrouter.dynamic.DynamicDataSource;
import com.example.util.PropertyUtil;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class DBRouterAutoConfiguration implements EnvironmentAware {

    private Map<String, Map<String, Object>> dataSourceMap = new HashMap<>();

    private Map<String, DBRouterConfig.DBInfo> dbsMap = new HashMap<>();  //分库数
    private Map<String, DBRouterConfig.DBInfo> tbsMap = new HashMap<>();  //分表数

    @Bean
    public DBRouterConfig dbRouterConfig() {
        DBRouterConfig config = new DBRouterConfig();
        config.setTbs(tbsMap);
        config.setDbs(dbsMap);
        return config;
    }

    @Bean
    public DataSource dataSource() {
        // 创建数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (String dbName : dataSourceMap.keySet()) {
            Map<String, Object> objMap = dataSourceMap.get(dbName);
            targetDataSources.put(dbName, new DriverManagerDataSource(objMap.get("url").toString(), objMap.get("username").toString(), objMap.get("password").toString()));
        }
        // 设置数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    public void setEnvironment(Environment environment) {
        String prefix = "router.jdbc.datasources.";
        String dataSources = environment.getProperty(prefix + "list");
        assert dataSources != null;
        for (String dbInfo : dataSources.split(",")) {
            Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, prefix + dbInfo, Map.class);
            dataSourceMap.put(dbInfo, dataSourceProps);
        }

        String prefixRule = "router.jdbc.rules.";
        String property = environment.getProperty(prefixRule + "user.actualDataNodes");
        String[] actualDataNodes = property.split("\\.");
        // db{1..2}.user_{1..4}

        String regex1 = "(\\w+)\\{(\\d+),(\\d+)}";
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(actualDataNodes[1]);
        if (matcher.find()) {
            DBRouterConfig.DBInfo cur = new DBRouterConfig.DBInfo();
            cur.setCount(Integer.parseInt(matcher.group(3)) - Integer.parseInt(matcher.group(2)) + 1);
            cur.setNamePrefix(matcher.group(1));
            tbsMap.put(cur.getNamePrefix(), cur);
        }
        Matcher matcher1 = pattern.matcher(actualDataNodes[0]);
        if (matcher1.find()) {
            DBRouterConfig.DBInfo cur = new DBRouterConfig.DBInfo();
            cur.setCount(Integer.parseInt(matcher1.group(3)) - Integer.parseInt(matcher1.group(2)) + 1);
            cur.setNamePrefix(matcher1.group(1));
            dbsMap.put(cur.getNamePrefix(), cur);
        }

    }

    public static void main(String[] args) {
        String[] actualDataNodes = "db{1..2}.user_{1..4}".split(",");


        String regex1 = "(\\w+)\\{(\\d+)..(\\d+)}";
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(actualDataNodes[0]);
        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
        }
    }
}

