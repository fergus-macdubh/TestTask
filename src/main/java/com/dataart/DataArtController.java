package com.dataart;

import com.dataart.db.JdbcDao;
import com.dataart.domain.Group;
import com.dataart.domain.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class DataArtController {
	private static final Logger log = Logger.getLogger(DataArtController.class);

    @Autowired
    public JdbcDao jdbcDao;

    @RequestMapping(method = RequestMethod.GET)
	public String index(
            @RequestParam(value = "group", required = false) Long groupId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "sortField", required = false, defaultValue = "id") String sortField,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "") String sortDirection,
            ModelMap model) {
        log.info("Got request. Params: group [" + groupId + "], page [" + page + "], sortField ["
                + sortField + "], sortDirection [" + sortDirection + "]");

        // get group list for the left panel
        List<Group> groups = jdbcDao.getGroups();
        model.addAttribute("groups", groups);
        log.info("Groups [" + groups + "]");

        // if some group is selected, then get the list of products
        if (groupId != null) {
            List<Product> products = jdbcDao.getProducts(groupId, page, sortField, sortDirection);
            model.addAttribute("products", products);
            log.info("Products [" + products + "]");
        }

		return "index";
	}
}