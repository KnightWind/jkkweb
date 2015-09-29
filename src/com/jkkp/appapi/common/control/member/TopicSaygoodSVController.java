package com.jkkp.appapi.common.control.member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.ITopicSV;
import com.jkkp.appapi.modules.mapper.VIAllTopicSV;
import com.jkkp.appapi.modules.mapper.VISMember;
import com.jkkp.appapi.modules.mapper.VITopicMjkSV;
import com.jkkp.appapi.modules.mapper.VITopicSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.member.mapper.TopicMapper;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.TopicSaygood;
import com.jkkp.modules.member.model.VTopic;
import com.jkkp.modules.member.service.ITopicSaygoodService;
import com.jkkp.modules.supplier.service.IMemberCollectService;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pager;

@Controller
@RequestMapping("/")
public class TopicSaygoodSVController  extends BaseController {
	@Autowired public CommonJsonMap commonJsonMap;
    @Autowired public ITopicSaygoodService iTopicSaygoodService;
	@ResponseBody
	@RequestMapping("/addtopic_saygood.do")
	public Object topicAllDetail(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer uid=CommonUtil.stringToInteger(map.get("uid").toString());
			Integer tpid=CommonUtil.stringToInteger(map.get("tpid").toString());
			String name=map.get("name").toString();	
			TopicSaygood topicSaygood=new TopicSaygood();
			topicSaygood.setCrateTime(new Date());
			topicSaygood.setUname(name);
			topicSaygood.setUid(uid);
			topicSaygood.setTpid(tpid);
			topicSaygood.setTblname("topic");
			topicSaygood.setType(6);
			iTopicSaygoodService.save(topicSaygood);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/deletetopic_saygood.do")
	public Object topicDeleteDetail(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer uid=CommonUtil.stringToInteger(map.get("uid").toString());
			Integer tpid=CommonUtil.stringToInteger(map.get("tpid").toString());
			String name=map.get("name").toString();	
			TopicSaygood topicSaygood=new TopicSaygood();
			topicSaygood.setCrateTime(new Date());
			topicSaygood.setUname(name);
			topicSaygood.setUid(uid);
			topicSaygood.setTpid(tpid);
			topicSaygood.setTblname("topic");
			topicSaygood.setType(6);
			iTopicSaygoodService.delete(topicSaygood);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
}