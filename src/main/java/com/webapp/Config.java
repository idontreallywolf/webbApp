package com.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.webapp.dao.AccountDao;
import com.webapp.dao.AccountDaoImpl;

@Configuration
@ComponentScan(basePackages = { "com.webapp.controller", "com.webapp.dao" })
public class Config {
	public static String mainTitle = "PepeSite";
	public static String rootDir = System.getProperty("user.dir");
	
	private static class Database {
		private static String host = "127.0.0.1";
		private static String user = "root";
		private static String pass = "root";
		private static String dbname = "memedb";
		private static int port = 3306;

		public static String getUrl() {
			return "jdbc:mysql://"+host+":"+port+"/"+dbname;
		}
	}

	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl(Database.getUrl());
		ds.setUsername(Database.user);
		ds.setPassword(Database.pass);
		return ds;
	}

	@Bean
	public AccountDao getEmployeeDao() {
		return new AccountDaoImpl(getDataSource());
	}
}
