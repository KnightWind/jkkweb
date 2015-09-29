package com.jkkp.appapi.common.control.supplier;
import java.util.*;

import javax.servlet.http.*;

import net.sf.json.*;
import org.apache.commons.lang.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.jsonmap.*;
import com.jkkp.appapi.common.service.interfaces.IJtopicSV;
import com.jkkp.appapi.common.service.interfaces.ITopicCommentSV;
import com.jkkp.appapi.common.service.interfaces.ITopicLabelSV;
import com.jkkp.common.*;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.TopicComment;
import com.jkkp.modules.member.model.TopicLabel;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.supplier.mapper.*;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.model.JtopicReply;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.IJtopicReplyService;
import com.jkkp.modules.supplier.view.*;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.*;
@Controller
public class JtopicReplySVController extends BaseController{
	@Autowired public CommonJsonMap commonJsonMap;
    @Autowired public IJtopicReplyService iJtopicReplyService;
    @Autowired private IMemberService iMemberService;
	@ResponseBody
	@RequestMapping("/cases_jtopic_comment_add.do")
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
			JtopicReply jtopicReply=new JtopicReply();
			jtopicReply.setContent(map.get("content").toString());
			jtopicReply.setCreateTime(new Date());
			jtopicReply.setUserId(uid);
			jtopicReply.setCreateUser(member.getNickname());
			jtopicReply.setType(6);
			jtopicReply.setPid(tid);
			jtopicReply.setStorey(1);
			jtopicReply.setStatus(1);
			iJtopicReplyService.save(jtopicReply);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/cases_jtopic_comment_two_add.do")
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
			JtopicReply jtopicReply1=iJtopicReplyService.findById(plid);
			JtopicReply jtopicReply=new JtopicReply();
			jtopicReply.setContent(map.get("content").toString());
			jtopicReply.setCreateTime(new Date());
			jtopicReply.setUserId(uid);
			jtopicReply.setCreateUser(member.getNickname());
			jtopicReply.setType(6);
			jtopicReply.setPid(plid);
			jtopicReply.setStorey(2);
			jtopicReply.setStatus(1);
			jtopicReply.setTitle("@"+jtopicReply1.getCreateUser());
			iJtopicReplyService.save(jtopicReply);		
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
}
