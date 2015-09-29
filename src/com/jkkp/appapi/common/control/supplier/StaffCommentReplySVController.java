package com.jkkp.appapi.common.control.supplier;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.common.BaseController;
import com.jkkp.modules.supplier.mapper.StaffCommentMapper;
import com.jkkp.modules.supplier.mapper.StaffCommentReplyMapper;
import com.jkkp.modules.supplier.mapper.SupplierCommentMapper;
import com.jkkp.modules.supplier.view.VStaffComment;
import com.jkkp.modules.supplier.view.VStaffCommentReply;
import com.jkkp.modules.supplier.view.VSupplierComment;
import com.jkkp.utils.AllDao;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.Pager;

@Controller
public class StaffCommentReplySVController extends BaseController{
	public final String prefix="/staff_comment_reply_";
	@Autowired public StaffCommentReplyMapper dao;
	@ResponseBody @RequestMapping(prefix+"list.do")//员工评论列表ysc
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
					List<VStaffCommentReply> list=dao.getPageList(pager);
					AllDao.copyProperties(rs,pager);
					rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
				}
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(prefix+"detail.do")//员工评论详情ysc
	public Map<String,Object> detail(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;String id="";
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","验证失败");return rs;};
				JSONObject jobj=JSONObject.fromObject(json);
				id=request.getParameter("id");
				if(StringUtils.isBlank(id)){
					Object id_obj=jobj.get("id");
					if(id_obj!=null) id=id_obj.toString().trim();
				}
				if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
				if(!BaseTools.isNumber(id)){rs.put("mess","id不是数字");return rs;}
				VStaffCommentReply bean=dao.getBeanById(Integer.parseInt(id.trim()));
				if(bean==null){rs.put("mess","记录不存在");return rs;}
				rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
				rs.put("ver", "1.0");rs.put("ret", "");
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	public String getAndByJSON(HttpServletRequest request,JSONObject json){
		String and="";
		and+=BaseTools.getAndByJson(json,"spName","sp_name", "1");
		and+=BaseTools.getAndByJson(json,"supplierAddress","address", "1");
		and+=BaseTools.getAndByJson(json,"id","id", "1","1");
		and+=BaseTools.getAndByJson(json,"status","status", "1","1");
		and+=BaseTools.getAndByJson(json,"type","type", "1","1");
		and+=BaseTools.getAndByJson(json,"username","username", "1");
		Object order=json.get("order");
		if("1".equals(order)){
			and+=" order by contact_mobile";//排序方式   1-预算高优先   2-预算低优先【找不到预算字段，随便找了手机做排序】
		}
		System.out.println(BaseTools.getIP(request)+"."+getClass().getSimpleName()+".getAndByJSON.and=\n"+and);
		return and;
	}
}
