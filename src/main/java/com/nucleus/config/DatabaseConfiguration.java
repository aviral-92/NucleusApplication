package com.nucleus.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfiguration {

	@Value("${db.mysql.driver:com.mysql.jdbc.Driver}")
	private String dbDriver;
	@Value("${db.mysql.url:jdbc:mysql://localhost:3306/nucleus}")
	private String dbURL;
	@Value("${db.mysql.username:root}")
	private String dbUsername;
	@Value("${db.mysql.password:root}")
	private String dbPassword;
	
	@Bean
	public DataSource getDataSource() throws SQLException {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbURL);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() throws SQLException {
		DataSource dataSource = getDataSource();
		System.out.println("conn===" + dataSource.getConnection());
		return new JdbcTemplate(getDataSource());
	}

}
