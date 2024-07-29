package com.ex.initproj.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.apache.tomcat.jdbc.pool.PoolProperties;
import javax.sql.DataSource;
@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {
    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/qlynhansu");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("011002");
        return dataSourceBuilder.build();
    }
}
