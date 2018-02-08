package com.configurations;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class DataBaseConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
	BasicDataSource dSource = new BasicDataSource();
	dSource.setDriverClassName(env.getProperty("db.driver"));
	dSource.setUrl(env.getProperty("db.url"));
	dSource.setUsername(env.getProperty("db.username"));
	dSource.setPassword(env.getProperty("db.password"));
	return dSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
	LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
	factoryBean.setDataSource(getDataSource());

	Properties property = new Properties();
	property.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
	property.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
	property.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
	property.put("hibernate.use_sql_commments", env.getProperty("hibernate.use_sql_commments"));
	property.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

	factoryBean.setHibernateProperties(property);
	factoryBean.setPackagesToScan("com.hibernate.model");
	return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	transactionManager.setSessionFactory(getSessionFactory().getObject());
	return transactionManager;
    }
}
