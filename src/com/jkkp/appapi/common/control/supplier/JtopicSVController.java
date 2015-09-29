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
import com.jkkp.appapi.common.service.interfaces.ITopicLabelSV;
import com.jkkp.common.*;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.member.model.TopicLabel;
import com.jkkp.modules.supplier.mapper.*;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.view.*;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.*;
@Controller
public class JtopicSVController extends BaseController{
	@Autowired CommonJsonMap commonJsonMap;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	IJtopicSV iJtopicSV;
	@Autowired
	ITopicLabelSV iTopicLabelSV;
	
	
	public static final String head="/jtopic_";
	@Autowired public JtopicMapper dao;
	@ResponseBody @RequestMapping(head+"list.do")//监理投诉列表
	public Map<String,Object> list(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag){
					JSONObject json_obj=JSONObject.fromObject(json);
					String and=getAndByJSON(request,json_obj);
					Pager pager=new Pager(and,json_obj,dao.getCount(and));
					List<VJtopic> list=dao.getPageList(pager);
					AllDao.copyProperties(rs,pager);
					rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
				}
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(head+"detail.do")//装修公司详细信息
	public Map<String,Object> detail(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;String id="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag){
					JSONObject jobj=JSONObject.fromObject(json);
					id=request.getParameter("id");
					if(StringUtils.isBlank(id)){
						Object id_obj=jobj.get("id");
						if(id_obj!=null) id=id_obj.toString().trim();
					}
					if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
					VJtopic bean=dao.getBeanById(Integer.parseInt(id.trim()));
					if(bean==null){rs.put("mess","记录不存在");return rs;}
					rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
					rs.put("ver", "1.0");rs.put("ret", "");
				}
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	public String getAndByJSON(HttpServletRequest request,JSONObject json){
		String and="";
		and+=BaseTools.getAndByJson(json,"id","id", "1","1");
		and+=BaseTools.getAndByJson(json,"sp_id","sp_id", "1","1");
		and+=BaseTools.getAndByJson(json,"house_type","house_type", "1","1");
		and+=BaseTools.getAndByJson(json,"status","status", "1","1");
		and+=BaseTools.getAndByJson(json,"spId","sp_id", "1","1");
		System.out.println(BaseTools.getIP(request)+"."+getClass()+".getAndByJSON.and=\n"+and);
		return and;
	}
	
	
	@ResponseBody
	@RequestMapping("/addJtopic.do")
	public Object addJtopic(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Jtopic jtopic=new Jtopic();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		SupplierCompanyStaff staff=new SupplierCompanyStaff();

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);

			iJtopicSV.save(jtopic);
			int mainId=jtopic.getId();

			 
			//保存文件
			List<Attachment>   attachments = attachmentService.uploadMulti((MultipartRequest) request);
			Admin admin = new Admin();
			admin.setId(0);
			attachmentService.saveAttachment(attachments, admin, mainId, AttachmentConstant.TOPIC_TYPE);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(staff, mapBusi);
		}
		
	}
	@ResponseBody
	@RequestMapping("/delJtopicByid.do")
	public Object delJtopicByid(HttpServletRequest request) throws Exception {
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
			Integer jtopicId=Integer.valueOf((String)map.get("jtopicId"));
			if(jtopicId<=0){
				mapBusi.put("mess", "入参jtopicId不能为空！");
				mapBusi.put("doneCode", "9999");
			}
			iJtopicSV.deleteById(jtopicId);
 
			

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(mapBusi, mapBusi);
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/addJtopicDetail.do")
	public Object addJtopicDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		TopicLabel topicLabel=null;

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			//保存文件
			List<Attachment> attachment = null;
			
			Jtopic jtopic=new Jtopic();
			String title=(String) map.get("title");
			String label=(String) map.get("label");
			String decStage=(String) map.get("decStage");
			Integer engId=Integer.valueOf((String) map.get("engId"));
			jtopic.setTitle(title);
			jtopic.setGcdId(engId);
			jtopic.setStageId(Integer.valueOf(decStage));
			jtopic.setCreateTime(new Date());
			iJtopicSV.save(jtopic);
			String[] labels=label.split(",");
			for(int i=0;i<labels.length;i++ ){
				int labelV1=Integer.valueOf(labels[i]);
				topicLabel=new TopicLabel();
				topicLabel.setTid(jtopic.getId());
				topicLabel.setLid(labelV1);
				topicLabel.setType(7);
				iTopicLabelSV.save(topicLabel);
			}			
			Admin admin=new Admin();
			admin.setId(0);
			if(attachment.size()>0)
				attachmentService.saveAttachment(attachment, admin, jtopic.getId(), AttachmentConstant.TOPIC_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(mapBusi, mapBusi);
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/queryJtopicById.do")
	public Object queryJtopicById(HttpServletRequest request) throws Exception {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<VJtopic> jtopics=null;
 
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			String jtopicId=(String)map.get("jtopicId");

			if(jtopicId==null){
				mapBusi.put("mess", "入参jtopicId不能为空！");
				mapBusi.put("doneCode", "9999");
				return -1;
			}


			jtopics=iJtopicSV.queryJtopicById(map);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(jtopics, mapBusi);
		}
		
	}
	
}
