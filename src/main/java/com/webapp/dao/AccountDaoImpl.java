package com.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.webapp.model.Account;

public class AccountDaoImpl implements AccountDao {

	private JdbcTemplate dbh;

	public AccountDaoImpl(DataSource ds) {
		dbh = new JdbcTemplate(ds);
	}

    /**
    *   Retrieves a specific account by given <b>username</b>
    *
    *   @param username - Takes a username which was submitted from login/register form
    *   @return an Account object if there was a match, otherwise <b>null</b>
    */
    @Override
    public Account getAccountByUsername(String username) {
    	Object[] params = {username};

    	try {
    		Account account = dbh.queryForObject("SELECT * FROM `accounts` WHERE `username` = ?", params, new RowMapper<Account>() {

            	@Override
            	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            		try {
            			Account acc = new Account();
                		acc.setId(rs.getInt("id"));
                    	acc.setFirstname(rs.getString("firstname"));
            			acc.setLastname(rs.getString("lastname"));
            			acc.setUsername(rs.getString("username"));
                        acc.setPassword(rs.getString("password"));
            			acc.setEmail(rs.getString("email"));
            			return acc;
            		} catch(SQLException e) {
                		System.err.println("["+e.getErrorCode()+"] "+e.getMessage());
                		return null;
                	}
            	}
            });
            return account;
    	} catch(EmptyResultDataAccessException e) {
    		return null;
    	}
    }

    /**
    *   Retrieves a specific account by given <b>email</b>
    *
    *   @param email - Takes an email which was submitted from login/register form
    *   @return an Account object if there was a match, otherwise <b>null</b>
    */
    @Override
    public Account getAccountByEmail(String email) {
        Object[] params = {email};

        try {
            Account account = dbh.queryForObject("SELECT * FROM `accounts` WHERE `email` = ?", params, new RowMapper<Account>() {

                @Override
                public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                    try {
                        Account acc = new Account();
                        acc.setId(rs.getInt("id"));
                        acc.setFirstname(rs.getString("firstname"));
                        acc.setLastname(rs.getString("lastname"));
                        acc.setUsername(rs.getString("username"));
                        acc.setPassword(rs.getString("password"));
                        acc.setEmail(rs.getString("email"));
                        return acc;
                    } catch(SQLException e) {
                        System.err.println("["+e.getErrorCode()+"] "+e.getMessage());
                        return null;
                    }
                }
            });
            return account;
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

	@Override
	public List<Account> accounts() {

		List<Account> accounts = dbh.query("SELECT * FROM `accounts`", new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account acc = new Account();

				acc.setId(rs.getInt("id"));
				acc.setFirstname(rs.getString("firstname"));
				acc.setLastname(rs.getString("lastname"));
				acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
				acc.setEmail(rs.getString("email"));

				return acc;
			}

		});
		return accounts;
	}

	@Override
	public void registerAccount(String firstname, String lastname, String username, String password, String email) {
        String sqlInsertQuery = "INSERT INTO `accounts`(`firstname`,`lastname`,`username`,`password`,`email`) VALUES(?, ?, ?, ?, ?)";
		dbh.update(sqlInsertQuery, firstname, lastname, username, password, email);
	}

}
