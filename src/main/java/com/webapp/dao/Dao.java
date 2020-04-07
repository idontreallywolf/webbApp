package com.webapp.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Dao {
    protected JdbcTemplate dbh;

    public Dao(DataSource ds) {
        dbh = new JdbcTemplate(ds);
    }
}
