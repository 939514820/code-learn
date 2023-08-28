package com.example.dbrouter.config;


import java.util.List;
import java.util.Map;

public class DBRouterConfig {
    private int dbCount;  //分库数
    private int tbCount;  //分表数
    /**
     * 默认db
     */
    private String defaultDB;

    public DBRouterConfig() {
    }

    public DBRouterConfig(int dbCount, int tbCount, String defaultDB) {
        this.dbCount = dbCount;
        this.tbCount = tbCount;
        this.defaultDB = defaultDB;
    }

    public int getDbCount() {
        return dbCount;
    }

    public void setDbCount(int dbCount) {
        this.dbCount = dbCount;
    }

    public int getTbCount() {
        return tbCount;
    }

    public void setTbCount(int tbCount) {
        this.tbCount = tbCount;
    }

    public String getDefaultDB() {
        return defaultDB;
    }

    public void setDefaultDB(String defaultDB) {
        this.defaultDB = defaultDB;
    }
}
