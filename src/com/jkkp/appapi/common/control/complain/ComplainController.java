package com.jkkp.appapi.common.control.complain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.druid.sql.ast.statement.SQLCreateViewStatement.Level;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IAgreementImgService;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.common.BaseController;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAgreementImg;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.order.model.AgreementImg;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Complain;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.modules.system.service.IComplainService;


@Controller
@RequestMapping("/")
public class ComplainController extends BaseController{
	//装入自动组装返回报文格式
	@Autowired 
	CommonJsonMap commonJsonMap;
	@Autowired
	IComplainService complaintsService;
	@Autowired
	private IAttachmentService attachmentService;
	
	@ResponseBody
	@RequestMapping("/user_complain_add.do")
	public Object user_complain_add(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		
		Complain com=new Complain();
		List<Object> ret=new ArrayList<Object>();
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			String sp_id=(String)map.get("sp_id");//可以区分投诉监理或者公司
			String mid=(String)map.get("mid");
			String title=(String)map.get("title");
			String content=(String)map.get("content");
			String level="";
			String aid="";
			String gcdId="";
			String admin_id="";
			if(map.containsKey("level"))
				level=(String)map.get("level");
			if(map.containsKey("admin_id"))
				admin_id=(String)map.get("admin_id");
			if(map.containsKey("aid"))//假如有预约单id就是投诉预约单
				aid=(String)map.get("aid");
			if(map.containsKey("gcdId"))//假如有工程单id就是投诉工程单
				gcdId=(String)map.get("gcdId");
			
			
			com.setSpId(Integer.valueOf(sp_id));
			com.setMid(Integer.valueOf(mid));
			com.setTitle(title);
			com.setContent(content);
			com.setStatus(2);//待解决
			if(aid!="")
			com.setAid(Integer.valueOf(aid));
			if(gcdId!="")
			com.setGcdId(Integer.valueOf(gcdId));
			if(admin_id!=null&&!admin_id.equals(""))
			com.setAdminId(Integer.valueOf(admin_id));
			if(level!=null&&!level.equals(""))
			com.setLevel(Integer.valueOf(level));
			else {
				com.setLevel(1);//默认低级
			}
		
			com.setCreateTime(StringAndDate.getTime());
			com.setUpdateTime(StringAndDate.getTime());
			complaintsService.save(com);
			
			
			//保存文件
			List<Attachment> attachment = attachmentService.uploadMulti((MultipartRequest) request);
 
			if(attachment==null){
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess","保存文件失败");
				return -1;
			}
			Admin admin = new Admin();
			admin.setId(1);
			if(aid!="")
				attachmentService.saveAttachment(attachment, admin, com.getId(), AttachmentConstant.COMPLAINS_TYPE);
			if(gcdId!="")
				attachmentService.saveAttachment(attachment, admin, com.getId(), AttachmentConstant.COMPLAINS_ENG_TYPE);
			//List<VAttachment> imgurlpathList;
			//imgurlpathList=attachmentService.findAttachment(com.getId(), AttachmentConstant.COMPLAINS_TYPE);
			
			//ret.add(com);
			//ret.add(imgurlpathList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(com,mapBusi);
		}
	}
	
	@ResponseBody
	@RequestMapping("/user_complain_qry.do")
	public Object user_complain_qry(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		
		Complain com=new Complain();
		List<Complain> retcomComplain=null;
		Map<String, Object> ret=new HashMap<String, Object>();
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String aid="";
			String gcdId="";
			if(map.containsKey("aid"))//假如有预约单id就是投诉预约单
				aid=(String)map.get("aid");
			if(map.containsKey("gcdId"))//假如有工程单id就是投诉工程单
				gcdId=(String)map.get("gcdId");
			
	
			if(aid!="")
			com.setAid(Integer.valueOf(aid));
			if(gcdId!="")
			com.setGcdId(Integer.valueOf(gcdId));
			
			retcomComplain=complaintsService.select(com);
			
			
 

			List<String> imgurlpathList;
			imgurlpathList=attachmentService.findForDownload(com.getId(), AttachmentConstant.COMPLAINS_TYPE);
			
			if(retcomComplain.size()>0){
				ret.put("com", retcomComplain.get(0));
			}
			else {
				ret.put("com", "");
			}
			ret.put("img", imgurlpathList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(ret,mapBusi);
		}
	}
	
}
