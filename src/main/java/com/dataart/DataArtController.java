package com.dataart;

import com.dataart.db.JdbcDao;
import com.dataart.domain.Group;
import com.dataart.domain.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortDirection", required = false) String sortDirection,
            HttpSession session,
            ModelMap model) {
        log.info("Got request. Params: group [" + groupId + "], page [" + page + "], sortField ["
                + sortField + "], sortDirection [" + sortDirection + "]");

        // pass request params to model
        model.addAttribute("currentGroup", groupId);
        model.addAttribute("currentPage", page);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        // get group list for the left panel
        List<Group> groups = jdbcDao.getGroups();
        model.addAttribute("groups", groups);
        log.info("Groups [" + groups + "]");

        // if some group is selected, then get the list of products
        if (groupId != null) {
            // try to get products from session for this group
            PagedListHolder<Product> productList = (PagedListHolder<Product>) session.getAttribute("products#" + groupId);
            log.info("Product list from session [" + productList + "]");

            // check if it is null, then request the list from DB
            if (productList == null) {
                List<Product> products = jdbcDao.getProducts(groupId, page, sortField, sortDirection);
                log.info("Products [" + products + "]");
                productList = new PagedListHolder<Product>(products);
                session.setAttribute("products#" + groupId, productList);
            }

            if (sortField != null) {
                boolean isAsc = !"desc".equals(sortDirection);
                productList.setSort(new MutableSortDefinition(sortField, true, isAsc));
                productList.resort();
            }

            if (page == null) {
                productList.setPage(0);
            } else {
                productList.setPage(page);
            }

            model.addAttribute("products", productList);
        }

		return "index";
	}
}