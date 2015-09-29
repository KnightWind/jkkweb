package com.jkkp.appapi.common.control.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IMenuSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.system.view.VAdminMenu;

@Controller 
@RequestMapping("/")
public class MenuSVController extends BaseController{
	
	@Autowired
	IMenuSV imenuSV;
	
	@Autowired
	CommonJsonMap commonJsonMap;
	
	
	@ResponseBody
	@RequestMapping("qryMenuByUser.do")
	public Object qryMenuByUser(HttpServletRequest request){
		List<VAdminMenu> object=null;
		String userName=null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			userName=(String) map.get("username");
			if(userName==null)
			{
				mapBusi.put("doneCode", "0007");
				mapBusi.put("mess", "用户不能为空！");
				return -1;
			}
			object=imenuSV.qryMenuByUser(map);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( object,mapBusi);
		}
	}

}
