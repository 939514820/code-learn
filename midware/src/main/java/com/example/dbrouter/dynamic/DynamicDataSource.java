package com.example.dbrouter.dynamic;

import com.example.dbrouter.DBContextHolder;
import com.example.dbrouter.config.DBRouterConfig;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Resource
    private DBRouterConfig dbRouterConfig;

    @Override
    protected Object determineCurrentLookupKey() {
        List<String> list = new ArrayList(dbRouterConfig.getDbs().keySet());
        return (StringUtils.isEmpty(DBContextHolder.getDBKey()) ? list.get(0) : DBContextHolder.getDBKey());
    }

}
