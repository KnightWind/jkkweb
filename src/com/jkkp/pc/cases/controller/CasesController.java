package com.jkkp.pc.cases.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jkkp.common.BaseController;

import com.jkkp.pc.cases.service.ICasesService;
import com.jkkp.pc.cases.view.VCases;
import com.jkkp.utils.CheckedUtil;

@RequestMapping("/main/casePC")
@Controller
public class CasesController extends BaseController {

	@Autowired
	private ICasesService casesService;
	

	
	@RequestMapping("/queryXCases")
	public List<VCases> queryXCases(Integer id,Integer type,Integer count){
		if(CheckedUtil.isNotEmpty(id)){
			List<VCases> list = casesService.queryXCases(id,type,count);
			return list;
		}
		return null;
	}
	
}
