package com.example.dbrouter.config;


import java.util.List;

public class DBRouterConfig {

    private int dbCount;  //分库数
    private int tbCount;  //分表数
    private List<CutInfo> cutInfos;
    /**
     * 默认db
     */
    private String defaultDB;

    public List<CutInfo> getCutInfos() {
        return cutInfos;
    }

    public void setCutInfos(List<CutInfo> cutInfos) {
        this.cutInfos = cutInfos;
    }

    public static class CutInfo {
        private String db;
        private String dbSeparate;
        private String table;
        private String tbSeparate;

        public String getTbSeparate() {
            return tbSeparate;
        }

        public void setTbSeparate(String tbSeparate) {
            this.tbSeparate = tbSeparate;
        }

        public String getDb() {
            return db;
        }

        public void setDb(String db) {
            this.db = db;
        }

        public String getDbSeparate() {
            return dbSeparate;
        }

        public void setDbSeparate(String dbSeparate) {
            this.dbSeparate = dbSeparate;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }
    }

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
