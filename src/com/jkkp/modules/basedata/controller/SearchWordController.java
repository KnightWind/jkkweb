package com.jkkp.modules.basedata.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.modules.basedata.model.SearchWord;
import com.jkkp.modules.basedata.service.impl.SearchWordServiceImpl;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping(value = "/ba/se")
public class SearchWordController {
	@Autowired
	private SearchWordServiceImpl searchWordServiceImpl;
	@AccessMenu
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		request.setAttribute("top",searchWordServiceImpl.top());
		request.setAttribute("hu",searchWordServiceImpl.hu());
		request.setAttribute("feng",searchWordServiceImpl.feng());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/brand/searchword";
	}
	@AccessMenu
	@RequestMapping(value = "/up")
	public String lst(HttpServletRequest request,String[] word,String[] id){	
			for (int j = 0; j < id.length; j++) {
				SearchWord searchWord=searchWordServiceImpl.findById(CommonUtil.stringToInteger(id[j]));
				searchWord.setAdminId(1);
				searchWord.setCreateTime(new Date());
				searchWord.setWord(word[j]);
				searchWordServiceImpl.update(searchWord);
		}
		request.setAttribute("top",searchWordServiceImpl.top());
		request.setAttribute("hu",searchWordServiceImpl.hu());
		request.setAttribute("feng",searchWordServiceImpl.feng());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/brand/searchword";
	}
}
