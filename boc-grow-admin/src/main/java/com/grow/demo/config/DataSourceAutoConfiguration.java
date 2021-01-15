package com.grow.demo.config;

import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 手动数据源配置
 * @author liuxw
 * @since 1.0
 */
@Configuration
public class DataSourceAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(DataSourceAutoConfiguration.class);

    public DataSourceAutoConfiguration(){

    }

    @Bean(name="dataSource")
    public DataSource dataSource(){

        if (logger.isDebugEnabled()){
            logger.debug("-------config  dataSources-----------");
        }

        DataSource dataSource = null;

        Properties dataSourcesProperties = new Properties();
        dataSourcesProperties.put("driverClassName","com.mysql.jdbc.Driver");
        dataSourcesProperties.put("url","jdbc:mysql://127.0.0.1:3306/plat_looker?useUnicode=true&characterEncoding=UTF8&useTimezone=true&serverTimezone=GMT%2b8");
        dataSourcesProperties.put("username","root");
        dataSourcesProperties.put("password","123456");

        DataSourceFactory dataSourceFactory = new DataSourceFactory();

        try{
            dataSource = dataSourceFactory.createDataSource(dataSourcesProperties);
        }catch (Exception e){
            logger.error("---config data source error ---" ,e);
        }

        return dataSource;
    }

}
