package com.webapp.dao;

import java.util.List;

import com.webapp.model.Account;

public interface AccountDao {
    public List<Account> accounts();
    public Account getAccountByUsername(String username);
    public Account getAccountByEmail(String email);
}
