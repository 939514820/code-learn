package com.example.dbrouter.config;

import com.example.dbrouter.dynamic.DynamicDataSource;
import com.example.util.PropertyUtil;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AutoConfiguration implements EnvironmentAware {

    private Map<String, Map<String, Object>> dataSourceMap = new HashMap<>();

    private int dbCount;  //分库数
    private int tbCount;  //分表数
    private String defaultDB;

    @Bean
    public DBRouterConfig dbRouterConfig() {
        DBRouterConfig config = new DBRouterConfig(dbCount, tbCount, defaultDB);
        config.setCutInfos(new ArrayList<>());
        DBRouterConfig.CutInfo info = new DBRouterConfig.CutInfo();
        info.setTable("user");
        info.setTbSeparate("_");
        info.setDb("db");
        info.setDbSeparate("");
        config.getCutInfos().add(info);
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

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "router.jdbc.datasource.";

        dbCount = Integer.parseInt(environment.getProperty(prefix + "dbCount"));
        tbCount = Integer.parseInt(environment.getProperty(prefix + "tbCount"));
        defaultDB = environment.getProperty(prefix + "defaultDB");
//        String cutinfo = environment.getProperty(prefix + "cutinfo");

        String dataSources = environment.getProperty(prefix + "list");
        assert dataSources != null;
        for (String dbInfo : dataSources.split(",")) {
            Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, prefix + dbInfo, Map.class);
            dataSourceMap.put(dbInfo, dataSourceProps);
        }
    }

}

