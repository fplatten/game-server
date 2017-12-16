package com.culture.api.gameserver.repository;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JpaConfig {
	
	private DataSource ed;
	
	@Bean(name="hsqlInMemory")
	public DataSource hsqlInMemory(){
		
//		if(this.ed == null){
//			
//			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//			this.ed = builder.setType(EmbeddedDatabaseType.H2).build();
//		}
//		
//		return this.ed;
		
		
		if(this.ed == null){
			org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
			ds.setDriverClassName("org.h2.Driver");
			ds.setUrl("jdbc:h2:mem:testdb;MODE=Oracle");
			ds.setUsername("sa");
			ds.setPassword("sa");
			ds.setInitialSize(3);
			ds.setMaxActive(5);
			ds.setMaxIdle(5);
			ds.setMinIdle(3);
			ds.setDefaultAutoCommit(false);
			this.ed = ds;
		}
		
		
		
		return this.ed;
		
		
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		
		lcemfb.setDataSource(this.hsqlInMemory());
		lcemfb.setPackagesToScan(new String[] {"com.culture.api.gameserver.domain"});
		lcemfb.setPersistenceUnitName("eventPU");
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		lcemfb.setJpaVendorAdapter(va);
		
		Properties prop = new Properties();
		prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		prop.put("hibernate.hbm2ddl.auto", "create");
		lcemfb.setJpaProperties(prop);
		lcemfb.afterPropertiesSet();
		
		return lcemfb;		
		
	}
	
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(){
		
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(this.entityManagerFactory().getObject());
		
		return tm;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
