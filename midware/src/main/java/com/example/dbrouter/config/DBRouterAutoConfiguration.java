package com.example.dbrouter.config;

import com.example.dbrouter.dynamic.DynamicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ConditionalOnClass(RouterProperties.class)
@EnableConfigurationProperties(RouterProperties.class)

@Configuration
public class DBRouterAutoConfiguration implements EnvironmentAware {

    private Map<String, RouterProperties.DataSourceCfg> dataSourceMap = new HashMap<>();

    private Map<String, DBRouterConfig.SliceInfo> dbsMap = new HashMap<>();  //分库数
    private Map<String, DBRouterConfig.SliceInfo> tbsMap = new HashMap<>();  //分表数
    @Resource
    private RouterProperties routerProperties;

    @Bean("routerProperties")
    @ConditionalOnMissingBean
    public RouterProperties routerProperties(RouterProperties routerProperties) {
        return routerProperties;
    }

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
            RouterProperties.DataSourceCfg objMap = dataSourceMap.get(dbName);
            targetDataSources.put(dbName,
                    new DriverManagerDataSource(objMap.getUrl(), objMap.getUsername(), objMap.getPassword()));
        }
        // 设置数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "router.jdbc.datasources.";
        String dataSources = environment.getProperty(prefix + "list");
        assert dataSources != null;
        for (RouterProperties.DataSourceCfg dbInfo : routerProperties.getDatasources()) {
            dataSourceMap.put(dbInfo.getName(), dbInfo);
        }

        List<RouterProperties.RulesCfg> rules = routerProperties.getRules();
        for (RouterProperties.RulesCfg rule : rules) {
            String[] actualDataNodes = rule.getActualDataNodes().split("\\.");
            // db{1..2}.user_{1..4}

            String regex1 = "(\\w+)\\{(\\d+),(\\d+)}";
            Pattern pattern = Pattern.compile(regex1);
            Matcher matcher = pattern.matcher(actualDataNodes[1]);
            if (matcher.find()) {
                DBRouterConfig.SliceInfo cur = new DBRouterConfig.SliceInfo();
                cur.setCount(Integer.parseInt(matcher.group(3)) - Integer.parseInt(matcher.group(2)) + 1);
                cur.setNamePrefix(matcher.group(1));
                tbsMap.put(rule.getTable(), cur);
            }
            Matcher matcher1 = pattern.matcher(actualDataNodes[0]);
            if (matcher1.find()) {
                DBRouterConfig.SliceInfo cur = new DBRouterConfig.SliceInfo();
                cur.setCount(Integer.parseInt(matcher1.group(3)) - Integer.parseInt(matcher1.group(2)) + 1);
                cur.setNamePrefix(matcher1.group(1));
                dbsMap.put(rule.getDatabase(), cur);
            }
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

