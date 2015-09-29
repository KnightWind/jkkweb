package com.jkkp.appapi.common.control.member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.jkkp.appapi.modules.mapper.VCaseShare;
import com.jkkp.appapi.modules.mapper.VIAllTopicSV;
import com.jkkp.appapi.modules.mapper.VISMember;
import com.jkkp.appapi.modules.mapper.VITopicList;
import com.jkkp.appapi.modules.mapper.VITopicMjkSV;
import com.jkkp.appapi.modules.mapper.VITopicSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.view.VCase;
import com.jkkp.modules.member.mapper.TopicMapper;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;
import com.jkkp.modules.supplier.service.IMemberCollectService;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.DateUtil;
import com.jkkp.utils.Pager;

@Controller
@RequestMapping("/")
public class TopicSVController  extends BaseController {
	public static final String head="/topic_";
	@Autowired public TopicMapper dao;
	@Autowired public ITopicSV itopService;
	@Autowired public CommonJsonMap commonJsonMap;
    @Autowired public IMemberCollectService iMemberCollectService;
    @ResponseBody @RequestMapping(head+"list.do")//预约列表
	public Map<String,Object> list(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过");return rs;}
				JSONObject json_obj=JSONObject.fromObject(json);
				String and=getAndByJSON(request,json_obj);
				Pager pager=new Pager(and,json_obj,dao.getCount(and));
				List<VTopic> list=dao.getPageList(pager);
				AllDao.copyProperties(rs,pager);
				rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(head+"detail.do")//预约详情
	public Map<String,Object> detail(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;String id="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过");return rs;}
				JSONObject jobj=JSONObject.fromObject(json);
				id=request.getParameter("id");
				if(StringUtils.isBlank(id)){
					Object id_obj=jobj.get("id");
					if(id_obj!=null) id=id_obj.toString().trim();
				}
				if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
				VTopic bean=dao.getBeanById(Integer.parseInt(id));
				if(bean==null){rs.put("mess","记录不存在");return rs;}
				rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
				rs.put("ver", "1.0");rs.put("ret", "");
			}
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	public String getAndByJSON(HttpServletRequest request,JSONObject json){
		String and="";
		and+=BaseTools.getAndByJson(json,"id","id", "1", "1");
		and+=BaseTools.getAndByJson(json,"sp_id","id", "1", "1");
		and+=BaseTools.getAndByJson(json,"houseType","house_type", "1", "1");
		and+=BaseTools.getAndByJson(json,"style","style", "1", "1");
		System.out.println(BaseTools.getIP(request)+"."+getClass()+".getAndByJSON.and=\n"+and);
		return and;
	}
	
	@ResponseBody @RequestMapping("topic_add.do")
	public Object addTopic(HttpServletRequest request){
		Map<String, Object> data=new HashMap<String, Object>();
		Topic topic=new Topic();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
			
			String topicSubjectc = (String) map.get("topicSubjectc");
			String topicBudget = (String) map.get("topicBudget");
			String topicSpace = (String) map.get("topicSpace");
			String topicStyle = (String) map.get("topicStyle");
			String topicHouseType = (String) map.get("topicHouseType");
			String topicUid = (String) map.get("topicUid");
			String topicCreateTime = (String) map.get("topicCreateTime");
			String topicCityDomain = (String) map.get("topicCityDomain");
			String topicStatus = (String) map.get("topicStatus");
			String topicCheckTime = (String) map.get("topicCheckTime");
			String topicUpdateTime = (String) map.get("topicUpdateTime");
			String topicCloseTime = (String) map.get("topicCloseTime");
			String topicCommunity = (String) map.get("topicCommunity");
			String topicForman = (String) map.get("topicForman"); 
			
			topic.setSubject(topicSubjectc);
			topic.setBudget(Integer.valueOf(topicBudget));
			topic.setSpace(Integer.valueOf(topicSpace));
			topic.setStyle(Byte.valueOf(topicStyle));
			topic.setHouseType(Byte.valueOf(topicHouseType));
			topic.setUid(Integer.valueOf(topicUid));
			topic.setCreateTime(sim.parse((topicCreateTime)));
			topic.setCityDomain(topicCityDomain);
			topic.setStatus(Byte.valueOf(topicStatus));
			topic.setCheckTime(sim.parse(topicCheckTime));
			topic.setUpdateTime(sim.parse(topicUpdateTime));
			topic.setCloseTime(sim.parse(topicCloseTime));
			topic.setCommunity(topicCommunity);
			topic.setForman(topicForman);
			
			
			itopService.save(topic);
			

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}
	}
	
	@ResponseBody @RequestMapping("topic_delete.do")
	public Object deleteByPk(HttpServletRequest request){
		Map<String, Object> data=new HashMap<String, Object>();
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
			itopService.deleteByPk(map);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}

	}


	@ResponseBody
	@RequestMapping("topic_update.do")
	public Object upadteTopic(HttpServletRequest request) {

		Topic topic =  null;
		Map<String, Object> data=new HashMap<String, Object>();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
			
			topic=itopService.findById(map);
			if(topic==null){
				mapBusi.put("mess", "修改数据不存在！");
				mapBusi.put("doneCod", "0092");
				return -1;
			}

			String topicSubjectc = (String) map.get("topicSubjectc");
			String topicBudget = (String) map.get("topicBudget");
			String topicSpace = (String) map.get("topicSpace");
			String topicStyle = (String) map.get("topicStyle");
			String topicHouseType = (String) map.get("topicHouseType");
			String topicUid = (String) map.get("topicUid");
			String topicCreateTime = (String) map.get("topicCreateTime");
			String topicCityDomain = (String) map.get("topicCityDomain");
			String topicStatus = (String) map.get("topicStatus");
			String topicCheckTime = (String) map.get("topicCheckTime");
			String topicUpdateTime = (String) map.get("topicUpdateTime");
			String topicCloseTime = (String) map.get("topicCloseTime");
			String topicCommunity = (String) map.get("topicCommunity");
			String topicForman = (String) map.get("topicForman");

			topic.setSubject(topicSubjectc);
			topic.setBudget(Integer.valueOf(topicBudget));
			topic.setSpace(Integer.valueOf(topicSpace));
			topic.setStyle(Byte.valueOf(topicStyle));
			topic.setHouseType(Byte.valueOf(topicHouseType));
			topic.setUid(Integer.valueOf(topicUid));
			topic.setCreateTime(sim.parse((topicCreateTime)));
			topic.setCityDomain(topicCityDomain);
			topic.setStatus(Byte.valueOf(topicStatus));
			topic.setCheckTime(sim.parse(topicCheckTime));
			topic.setUpdateTime(sim.parse(topicUpdateTime));
			topic.setCloseTime(sim.parse(topicCloseTime));
			topic.setCommunity(topicCommunity);
			topic.setForman(topicForman);

			itopService.update(topic);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}
	}
    
	@ResponseBody
	@RequestMapping("/topic_shoucan.do")//查询当前C端登录的收藏日记
	public Object shouCanDetail(HttpServletRequest request) {
		List<VITopicSV> list = new ArrayList<VITopicSV>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		Integer uid=0;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			uid=CommonUtil.stringToInteger((String) map.get("uid"));
			pagination.put("currentPage", currentPage);
			pagination.put("uid",uid);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=itopService.queryTopic(map);
			map= PaginationInterceptor.nextPagination(map);
			if(itopService.queryTopic(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list, mapBusi);
		}
	}
	@ResponseBody @RequestMapping("/topic_datail_delete.do")
	public Object sjs_datail_delete(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String mm=(String) map.get("mid");
			String[] id=mm.split(",");
			for (int i = 0; i < id.length; i++) {
				iMemberCollectService.deleteById(Integer.valueOf(id[i]));
			}		
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}		
	}
	
	@ResponseBody
	@RequestMapping("/topic_list_two.do")
	public Object topicDetail(HttpServletRequest request) {
		List<VITopicSV> list = new ArrayList<VITopicSV>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=itopService.queryTopicList(map);
			VITopicSV vSv =itopService.queryAd();
			 String name=vSv.getMid();
			map= PaginationInterceptor.nextPagination(map);
			if(itopService.queryTopicList(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
			data.put("list", list);
			data.put("name", name);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/member_detail.do")
	public Object memberDetail(HttpServletRequest request) {
		 VISMember visMember=new VISMember();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
            Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
             visMember=itopService.getById(uid);            
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(visMember,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/topic_list_two_detail.do")
	public Object topicAllDetail(HttpServletRequest request) {
		List<VIAllTopicSV> list = new ArrayList<VIAllTopicSV>();
		VITopicMjkSV list1 = new VITopicMjkSV();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			Integer uid=CommonUtil.stringToInteger(map.get("uid").toString());
			Integer cid=CommonUtil.stringToInteger(map.get("cid").toString());
			pagination.put("currentPage",currentPage);
			pagination.put("cid",cid);
			pagination.put("uid", uid);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=itopService.getAllTopic(map);
			Integer aid=CommonUtil.stringToInteger((String) map.get("aid"));
			list1=itopService.getAllTopicDetails(uid, aid);
			map= PaginationInterceptor.nextPagination(map);
			if(itopService.getAllTopic(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
			data.put("topiclist", list);
			data.put("details", list1);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/topic_list_jtopic_detail.do")
	public Object topicAllJtopicDetail(HttpServletRequest request) {
		List<VIAllTopicSV> list = new ArrayList<VIAllTopicSV>();
		VCaseShare list1 = new VCaseShare();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			Integer spid=CommonUtil.stringToInteger(map.get("spid").toString());
			Integer cid=CommonUtil.stringToInteger(map.get("cid").toString());
			pagination.put("currentPage",currentPage);
			pagination.put("cid",cid);
			pagination.put("spid", spid);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=itopService.getAllJtopic(map);
			list1=(VCaseShare) itopService.getCommonnJtopic(spid);
			map= PaginationInterceptor.nextPagination(map);
			if(itopService.getAllJtopic(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
			data.put("jtopiclist", list);
			data.put("jl", list1);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/cases_topic_list.do")
	public Object queryCasesTopicList(HttpServletRequest request) {
		List<VITopicList> list = new ArrayList<VITopicList>();
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");
			pagination.put("currentPage",currentPage);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=itopService.queryCasesTopicList(map);
			
			map= PaginationInterceptor.nextPagination(map);
			if(itopService.queryCasesTopicList(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(list,mapBusi);
		}
	}
}