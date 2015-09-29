package com.jkkp.appapi.common.control.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.ITopicSV;
import com.jkkp.appapi.modules.mapper.VISMember;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.VMember;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/")
public class MemberSVController  extends BaseController {
	@Autowired private IAttachmentService attachmentService;
	@Autowired private CommonJsonMap commonJsonMap;
	@Autowired private IMemberService iMemberService;
	@Autowired private ITopicSV itopService;
	@Autowired private IBaseinf IBaseinfService;
	@ResponseBody @RequestMapping("/member_addimg.do")
	public Object queryDetail(HttpServletRequest request) {
		VMember vmember = new VMember();
		Member member =null;
		Map<String, Object> map = null;
		List<VAttachment> imgurlpathList=null;
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
			Integer uid=CommonUtil.stringToInteger((String)map.get("uid"));
			//保存文件
			List<Attachment> attachment = attachmentService.uploadMulti((MultipartRequest) request);
			if(attachment==null){
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess","保存文件失败");
				return -1;
			}
			Admin admin =new Admin();
			imgurlpathList=attachmentService.findAttachment(uid,AttachmentConstant.MEMBER_TYPE);
			for (Attachment delattachment : imgurlpathList) {
				//删除之前个人头像
				attachmentService.delete(delattachment);
			}
			attachmentService.saveAttachment(attachment,admin,uid,AttachmentConstant.MEMBER_TYPE);
			member=iMemberService.findById(uid);
			BaseTools.CopyBean(member, vmember);
			vmember.setHeadImg(IBaseinfService.getHeadimg(uid, AttachmentConstant.MEMBER_TYPE));
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(vmember, mapBusi);
		}
	}
	@ResponseBody @RequestMapping("/member_update.do")
	public Object update(HttpServletRequest request) {
		VMember vMember=new VMember();
		Integer uid = null;
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
			uid=CommonUtil.stringToInteger((String)map.get("uid"));
			vMember=iMemberService.updateMember(uid, map);
	        /*String name=(String)map.get("name");
	        Integer uid=CommonUtil.stringToInteger((String)map.get("uid"));
	        Member member=iMemberService.findById(uid);
	        member.setNickname(name);
	        iMemberService.update(member);
			BaseTools.CopyBean(member, vmember);
			vmember.setHeadImg(IBaseinfService.getHeadimg(uid, AttachmentConstant.MEMBER_TYPE));
			*/
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(vMember, mapBusi);
		}
	}
	@ResponseBody @RequestMapping("/member_city_update.do")
	public Object updateCity(HttpServletRequest request) {
		VMember vmember = new VMember();
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
	        String name=(String)map.get("city");
	        Integer uid=CommonUtil.stringToInteger((String)map.get("uid"));
	        Member member=iMemberService.findById(uid);
	        member.setCityDomain(name);
	        iMemberService.update(member);
			BaseTools.CopyBean(member, vmember);
			vmember.setHeadImg(IBaseinfService.getHeadimg(uid, AttachmentConstant.MEMBER_TYPE));
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(vmember, mapBusi);
		}
	}
	@ResponseBody @RequestMapping("/member_check_password.do")
	public Object checkPwd(HttpServletRequest request) {
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
	        String pwd=(String)map.get("pwd");
	        Integer uid=CommonUtil.stringToInteger((String)map.get("uid"));
	        Member member=iMemberService.findById(uid);
	        String password=CommonUtil.md5(pwd);
	        if(member.getPassword().equals(password)){
	        	mapBusi.put("mess", "操作成功");
	        }else {
	        	mapBusi.put("mess","密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null, mapBusi);
		}
	}
}