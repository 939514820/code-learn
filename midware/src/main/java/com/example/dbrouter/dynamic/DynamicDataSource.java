package com.example.dbrouter.dynamic;

import com.example.dbrouter.DBContextHolder;
import com.example.dbrouter.config.DBRouterConfig;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Resource
    private DBRouterConfig dbRouterConfig;

    @Override
    protected Object determineCurrentLookupKey() {
        return (StringUtils.isEmpty(DBContextHolder.getDBKey()) ? dbRouterConfig.getDefaultDB() : DBContextHolder.getDBKey());
    }

}
