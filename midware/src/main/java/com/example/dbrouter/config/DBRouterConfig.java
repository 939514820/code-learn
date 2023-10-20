package com.example.dbrouter.config;


import java.util.Map;

public class DBRouterConfig {
    private Map<String, SliceInfo> dbs;
    private Map<String, SliceInfo> tbs;

    public Map<String, SliceInfo> getDbs() {
        return dbs;
    }

    public void setDbs(Map<String, SliceInfo> dbs) {
        this.dbs = dbs;
    }

    public Map<String, SliceInfo> getTbs() {
        return tbs;
    }

    public void setTbs(Map<String, SliceInfo> tbs) {
        this.tbs = tbs;
    }


    public static class SliceInfo {
        private String namePrefix;
        private Integer count;

        public SliceInfo() {
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
