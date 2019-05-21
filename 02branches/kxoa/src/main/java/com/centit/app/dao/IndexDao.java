package com.centit.app.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: sx
 * Date: 13-7-29
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 */
public class IndexDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getContentTextToIndex(String sql) {
        String s = jdbcTemplate.queryForObject(sql, String.class);


        return s;
    }
}
