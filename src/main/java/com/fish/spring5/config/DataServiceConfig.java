package com.fish.spring5.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.fish.spring5.constant.SystemConstants;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/8
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DataServiceConfig {

    public static final Logger logger = LoggerFactory.getLogger(DataServiceConfig.class);

    @Value("${datasource.druid.driverClassName}")
    private String driverClassName;

    @Value("${datasource.druid.url}")
    private String url;

    @Value("${datasource.druid.username}")
    private String userName;

    @Value("${datasource.druid.password}")
    private String password;

    @Value("${datasource.druid.initialSize}")
    private int initialSize;

    @Value("${datasource.druid.minIdle}")
    private int minIdle;

    @Value("${datasource.druid.maxActive}")
    private int maxActive;

    @Value("${datasource.druid.maxWait}")
    private long maxWait;

    @Value("${datasource.druid.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${datasource.druid.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    @Value("${datasource.druid.validationQuery}")
    private String validationQuery;

    @Value("${datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${datasource.druid.testOnReturn}")
    private boolean testOnReturn;

    @Value("${datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${datasource.druid.filters}")
    private String filters;


    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);

        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            logger.info("build datasource fail " + e.getMessage());
        }

        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory()
            throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(SystemConstants.ENTITY_SCAN_PACKAGE_NAME);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager()
            throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }

    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }
}
