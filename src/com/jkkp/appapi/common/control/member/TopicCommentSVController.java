package com.jkkp.appapi.common.control.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.ITopicCommentSV;
import com.jkkp.appapi.modules.mapper.VITopicCommentDetailsSV;
import com.jkkp.appapi.modules.mapper.VITopicCommentSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.TopicComment;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/")
public class TopicCommentSVController  extends BaseController {
	@Autowired public CommonJsonMap commonJsonMap;
    @Autowired public ITopicCommentSV iTopicCommentSV;
    @Autowired private IMemberService iMemberService;
	@ResponseBody
	@RequestMapping("/cases_topic_comment_list.do")
	public Object queryCasesTopicList(HttpServletRequest request) {
		List<VITopicCommentDetailsSV> list = new ArrayList<VITopicCommentDetailsSV>();
		VITopicCommentSV viTopicCommentSV=new VITopicCommentSV();
		
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
			Integer tid=CommonUtil.stringToInteger((String) map.get("tid"));
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
			pagination.put("currentPage",currentPage);
			pagination.put("tid",tid);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=iTopicCommentSV.queryCaseTopicComment(map);
			viTopicCommentSV=iTopicCommentSV.getTopicById(tid,uid);
			map= PaginationInterceptor.nextPagination(map);
			if(iTopicCommentSV.queryCaseTopicComment(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
			data.put("pllist", list);
			data.put("topic", viTopicCommentSV);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/cases_topic_comment_add.do")
	public Object addCasesTopicList(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer tid=CommonUtil.stringToInteger((String) map.get("tid"));
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
			Member member=iMemberService.findById(uid);
			TopicComment topicComment =new TopicComment();
			topicComment.setCreateTime(new Date());
			topicComment.setUserId(uid);
			topicComment.setCreateUser(member.getNickname());
			topicComment.setContent(map.get("content").toString());
			topicComment.setType(6);
			topicComment.setPid(tid);
			topicComment.setStorey(1);
			topicComment.setStatus(1);
			iTopicCommentSV.save(topicComment);			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/cases_topic_comment_two_add.do")
	public Object addCasesTopicListTwo(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer plid=CommonUtil.stringToInteger((String) map.get("plid"));
			Integer uid=CommonUtil.stringToInteger((String) map.get("uid"));
			Member member=iMemberService.findById(uid);
			TopicComment topicComment1=iTopicCommentSV.findById(plid);	
			TopicComment topicComment =new TopicComment();
			topicComment.setCreateTime(new Date());
			topicComment.setUserId(uid);
			topicComment.setCreateUser(member.getNickname());
			topicComment.setContent(map.get("content").toString());
			topicComment.setType(6);
			topicComment.setPid(plid);
			topicComment.setStorey(2);
			topicComment.setStatus(1);
			topicComment.setTitle("@"+topicComment1.getCreateUser());
			iTopicCommentSV.save(topicComment);			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
}