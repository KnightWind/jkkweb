package com.jkkp.appapi.common.control.supplier;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.jkkp.appapi.common.jsonmap.*;
import com.jkkp.common.*;
import com.jkkp.modules.supplier.mapper.*;
import com.jkkp.modules.supplier.view.*;
import com.jkkp.utils.*;

@Controller
public class SupplierCompanyStaffSVController extends BaseController{
	public static final String head="/supplier_company_staff_";
	@Autowired public SupplierCompanyStaffMapper dao;
	@ResponseBody @RequestMapping(head+"list.do")//设计师列表
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
					List<VSupplierCompanyStaff> list=dao.getPageList(pager);
					AllDao.copyProperties(rs,pager);
					rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
				}
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(head+"detail.do")//设计师详情
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
				VSupplierCompanyStaff bean=dao.getBeanById(Integer.parseInt(id));
				if(bean==null){rs.put("mess","记录不存在");return rs;}
				rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
				rs.put("ver", "1.0");rs.put("ret", "");
			}
			System.out.println(JSONObject.fromObject(rs,AllDao.jcfg));
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	public String getAndByJSON(HttpServletRequest request,JSONObject json){
		String and="";
		and+=BaseTools.getAndByJson(json,"id","id", "1","1");
		and+=BaseTools.getAndByJson(json,"sid","sid", "1","1");
		and+=BaseTools.getAndByJson(json,"sp_id","sp_id", "1","1");
		and+=BaseTools.getAndByJson(json,"spId","sp_id", "1","1");
		and+=BaseTools.getAndByJson(json,"name","name", "1");
		and+=BaseTools.getAndByJson(json,"job","job", "1");
		Object order=json.get("order");
		if("1".equals(order)){
			and+=" order by contact_mobile";//排序方式   1-预算高优先   2-预算低优先【找不到预算字段，随便找了手机做排序】
		}
		System.out.println(BaseTools.getIP(request)+"."+getClass().getSimpleName()+".getAndByJSON.and=\n"+and);
		return and;
	}
}
