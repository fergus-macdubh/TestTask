package com.dataart;

import com.dataart.domain.Group;
import org.apache.log4j.Logger;
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

    @RequestMapping(method = RequestMethod.GET)
	public String index(
            @RequestParam(value = "groupId", required = false) Long groupId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortDirection", required = false) String sortDirection,
            ModelMap model) {
        List<Group> groups = new ArrayList<Group>();
        groups.add(new Group(0, "Cars", 2));
        groups.add(new Group(1, "Bikes", 5));
        model.addAttribute("groups", groups);
        log.info("Groups [" + groups + "]");
        log.info("Group Id [" + groupId + "]");

        model.addAttribute("product", "Hello product!");
		return "index";
	}
}