/**
 * Copyright (c) Coveo Solutions Inc.
 */
package com.example.circulardepexample;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class CompositeDatasource extends AbstractRoutingDataSource
{
    CompositeDatasource(DataSource primary, DataSource secondary)
    {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("primary", primary);
        dataSources.put("secondary", secondary);

        super.setTargetDataSources(dataSources);
        super.setDefaultTargetDataSource(primary);
    }

    @Override
    protected Object determineCurrentLookupKey()
    {
        return null;
    }

}
