package com.example.dbrouter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;


@ConfigurationProperties("router.jdbc")
public class RouterProperties {

    private List<DataSourceCfg> datasources;
    private List<RulesCfg> rules;

    public List<DataSourceCfg> getDatasources() {
        return datasources;
    }

    public void setDatasources(List<DataSourceCfg> datasources) {
        this.datasources = datasources;
    }

    public List<RulesCfg> getRules() {
        return rules;
    }

    public void setRules(List<RulesCfg> rules) {
        this.rules = rules;
    }


    public static class DataSourceCfg {
        private String name;
        private String driverClassName;
        private String url;
        private String username;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class RulesCfg {
        private String table;
        private String database;
        private String actualDataNodes;

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getActualDataNodes() {
            return actualDataNodes;
        }

        public void setActualDataNodes(String actualDataNodes) {
            this.actualDataNodes = actualDataNodes;
        }
    }
}
