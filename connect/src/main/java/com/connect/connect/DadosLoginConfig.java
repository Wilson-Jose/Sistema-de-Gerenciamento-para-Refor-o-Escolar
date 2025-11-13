package com.connect.connect;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@Configuration
public class DadosLoginConfig {

	@Bean
	 DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/connect?useTimezone=true&serverTimezone=UTC&createDatabaseIfNotExist=true");
		dataSource.setUsername("root");
		dataSource.setPassword("27032005");
		return dataSource;
	}
	
	@Bean
	 JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect"); 
		adapter.setPrepareConnection(true);
        adapter.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "update");

		return adapter;
	}
}
