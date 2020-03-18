package com.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.webapp.model.Account;

public class AccountDaoImpl implements AccountDao {

	private JdbcTemplate dbh;
	
	public AccountDaoImpl(DataSource ds) {
		dbh = new JdbcTemplate(ds);
	}
	
	@Override
	public List<Account> accList() {
		List<Account> accounts = dbh.query("SELECT * FROM `accounts`", new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account acc = new Account();
				
				acc.setId(rs.getInt("id"));
				acc.setFirstname(rs.getString("firstname"));
				acc.setLastname(rs.getString("lastname"));
				acc.setUsername(rs.getString("username"));
				acc.setEmail(rs.getString("email"));
				
				return acc;
			}
			
		});
		return accounts;
	}

}
