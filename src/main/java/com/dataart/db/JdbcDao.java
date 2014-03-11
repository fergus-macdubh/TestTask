package com.dataart.db;

import com.dataart.domain.Group;
import com.dataart.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class JdbcDao {
    @Value("#{db['github.oauth.clientId']}")

    public List<Product> getProducts(Long id, long page, String sortField, String sortDirection) {
        return null;
    }

    public List<Group> getGroups() {
        return null;
    }

    private Connection getConnection() {

    }
}
