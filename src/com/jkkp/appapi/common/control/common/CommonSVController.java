package com.jkkp.appapi.common.control.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.common.BaseController;

@Controller
@RequestMapping("/")
public class CommonSVController extends BaseController{
	
	//装入自动组装返回报文格式
	@Autowired 
	CommonJsonMap commonJsonMap;
	

	
	@ResponseBody
	@RequestMapping("/file_del.do")
	public Object designAddDel(HttpServletRequest request) {	
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

			String fileUrl=(String) map.get("photoUrl");
			
			if(fileUrl!=null&&!fileUrl.equals("")){
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess", "图片路径不能为空！");
				return -1;
			}
//			String fileName=HttpFileTools.getPhotoUrl(fileUrl);
//
//			HttpFileTools.deleteFile(fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( null,mapBusi);
		}
	}

}
