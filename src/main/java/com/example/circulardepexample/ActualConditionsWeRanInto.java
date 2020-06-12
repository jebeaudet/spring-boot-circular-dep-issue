/**
 * Copyright (c) Coveo Solutions Inc.
 */
package com.example.circulardepexample;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class ActualConditionsWeRanInto
{
    @Bean
    @Primary
    public DataSource routingDataSource()
    {
        return new CompositeDatasource(dataSource(primaryDataSourceProperties()),
                                       dataSource(secondaryDataSourceProperties()));
    }

    @Bean(autowireCandidate = false)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource(DataSourceProperties dataSourceProperties)
    {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Primary
    public DataSourceProperties primaryDataSourceProperties()
    {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSourceProperties secondaryDataSourceProperties()
    {
        return new DataSourceProperties();
    }
}
