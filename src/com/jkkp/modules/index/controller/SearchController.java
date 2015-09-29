package com.jkkp.modules.index.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jkkp.modules.index.service.ISupplierSearchService;

@Controller
@RequestMapping("/index/search")
public class SearchController {

	@Autowired
	private ISupplierSearchService supplierSearchService;

	@RequestMapping("index")
	public String index() {
		return "/index/search";
	}

	@RequestMapping("commonSearch")
	public String commonSearch(HttpServletRequest request) {
		String queryString = request.getParameter("queryString");
		List<?> list = supplierSearchService.search(queryString);
		System.out.println(list.size());
		request.setAttribute("resultList", list);
		return "/index/search";
	}
}
