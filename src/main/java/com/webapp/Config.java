package com.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.webapp.dao.AccountDao;
import com.webapp.dao.AccountDaoImpl;
import com.webapp.dao.Dao;
import com.webapp.dao.PostDao;
import com.webapp.dao.PostDaoImpl;
import com.webapp.model.Post;

@Configuration
@ComponentScan(basePackages = { "com.webapp.controller", "com.webapp.dao" })
public class Config {
	public static String mainTitle = "PepeSite";
	public static String rootDir = System.getProperty("user.dir");

    /**
    *   Configuration for Accounts
    */
    public static class Account {
        public static int minNameLength = 4;
        public static int maxNameLength = 20;

        public static int minUnameLength = 4;
        public static int maxUnameLength = 30;

        public static int minPassLength = 8;
        public static int maxPassLength = 60;

        public static int minEmailLength = 6;
    }

    /**
    *   Database Config
    */
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

    /**
    *   BEANS
    */

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
    public AccountDao getAccDao() {
    	return new AccountDaoImpl(getDataSource());
    }
    
    @Bean
    public PostDao getPostDao() {
    	return new PostDaoImpl(getDataSource());
    }
    

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
