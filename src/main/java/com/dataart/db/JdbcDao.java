package com.dataart.db;

import com.dataart.domain.Group;
import com.dataart.domain.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class JdbcDao {
    private final static Logger log = Logger.getLogger(JdbcDao.class);

    private String dbUrl = "jdbc:mysql://vdb2.dataart.net:3306/hr_test";
    private String dbPassword = "12gh56qw";
    private String dbLogin = "hr_test";

    public List<Product> getProducts(Long id, long page, String sortField, String sortDirection) {
        return null;
    }

    public List<Group> getGroups() {
        Connection connection = getConnection();

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();

            List<Group> groups = jdbcTemplate.query(
                    "select first_name, surname from t_actor",
                    new RowMapper<Group>() {
                        public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return new Group(
                                    rs.getLong(1),
                                    rs.getString(2),
                                    rs.getInt(3));
                        }
                    });
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            log.info("Trying to get a connection...");
            connection = DriverManager.getConnection(dbUrl, dbLogin, dbPassword);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

        log.info("Connection [" + connection + "]");
        return connection;
    }
}
