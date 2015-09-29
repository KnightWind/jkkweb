package com.jkkp.pc.other.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jkkp.common.BaseController;


@Controller
@RequestMapping("/main/merchantJoinPC")
public class MerchantJoinPcController extends BaseController {
    
	
	@RequestMapping("index")
	public String index(){
		return "/pc/other/merchantJoin";
	}
}
