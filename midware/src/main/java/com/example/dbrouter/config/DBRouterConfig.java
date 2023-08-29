package com.example.dbrouter.config;


import java.util.Map;

public class DBRouterConfig {
    // db: name count
    // tb:name count
    /**
     * 默认db
     */
    private Map<String, DBInfo> dbs;
    private Map<String, DBInfo> tbs;

    public Map<String, DBInfo> getDbs() {
        return dbs;
    }

    public void setDbs(Map<String, DBInfo> dbs) {
        this.dbs = dbs;
    }

    public Map<String, DBInfo> getTbs() {
        return tbs;
    }

    public void setTbs(Map<String, DBInfo> tbs) {
        this.tbs = tbs;
    }


    public static class DBInfo {
        private String namePrefix;
        private Integer count;

        public DBInfo() {
        }

        public String getNamePrefix() {
            return namePrefix;
        }

        public void setNamePrefix(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }

    public DBRouterConfig() {
    }
}
