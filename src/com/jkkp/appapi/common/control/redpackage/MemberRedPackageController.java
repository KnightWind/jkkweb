package com.jkkp.appapi.common.control.redpackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.MemberRedPackageVO;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.common.BaseController;
import com.service.MemberRedPackageService;

/**
 * 获取会员我的红包列表
 * @author bruce
 */
@Controller
@RequestMapping("/")
public class MemberRedPackageController extends BaseController {
	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired
	MemberRedPackageService memberRedPackageService;

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/redPackageList.do")
	public Object getMyActiivityOrderList(HttpServletRequest request) {
		Map<String, Object> map = null;
		List<MemberRedPackageVO> list = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {

			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			mapBusi.put("memberId", map.get("memberId"));
			list = memberRedPackageService.findMemberPurseRedPackgeList(mapBusi);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}
	}
	
}
