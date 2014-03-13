package com.dataart.db;

import com.dataart.domain.Group;
import com.dataart.domain.Product;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@Service
public class JdbcDao {
    private final static Logger log = Logger.getLogger(JdbcDao.class);

    private final static int PRODUCTS_PER_PAGE = 10;

    @Autowired
    private DataSource dataSource;

    public List<Product> getProducts(Long groupId, Integer page, String sortField, String sortDirection) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Product> products = null;
        groupId = 2l;
        page = 0;
        sortField = "name";
        sortDirection = "";

        try {
            products = jdbcTemplate.query(
                    "SELECT\n" +
                            "   p.PRODUCT_ID as id,\n" +
                            "   p.PRODUCT_NAME as name,\n" +
                            "   p.GROUP_ID as groupId,\n" +
                            "   p.PRODUCT_PRICE as price\n" +
                            "from  T_PRODUCT p\n" +
                            "where p.GROUP_ID=?\n" +
                            "order by name\n" +
                            "limit 0,10",
                    new BigDecimal[]{BigDecimal.valueOf(groupId)},
                    new BeanPropertyRowMapper(Product.class));

//            products = jdbcTemplate.query(
//                    "SELECT\n" +
//                            "   p.PRODUCT_ID as id,\n" +
//                            "   p.PRODUCT_NAME as name,\n" +
//                            "   p.GROUP_ID as groupId,\n" +
//                            "   p.PRODUCT_PRICE as price\n" +
//                            "from  T_PRODUCT p\n" +
//                            "where p.GROUP_ID=?\n" +
//                            "order by ? ?\n" +
//                            "limit ?,?",
//                    new Object[]{
//                            groupId,
//                            StringUtils.isNotEmpty(sortField) ? sortField : "id",
//                            StringUtils.isNotEmpty(sortDirection) ? sortDirection : "asc",
//                            Long.valueOf((page != null ? page : 0) * PRODUCTS_PER_PAGE),
//                            Long.valueOf((page != null ? page : 0) * PRODUCTS_PER_PAGE + 10)},
//                    new BeanPropertyRowMapper(Product.class));
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }

        return products;
    }

    public List<Group> getGroups() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Group> groups = null;

        try {
            groups = jdbcTemplate.query(
                    "select \n" +
                            "\tg.GROUP_ID as id,\n" +
                            "\tg.GROUP_NAME as name,\n" +
                            "\t(select count(*) from T_PRODUCT p where p.GROUP_ID=id) as productCount\n" +
                            "from T_GROUP g\n" +
                            "order by name",
                    new BeanPropertyRowMapper(Group.class));
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }

        return groups;
    }
}
