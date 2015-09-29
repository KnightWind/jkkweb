package com.jkkp.appapi.common.control.supplier;

import java.util.*;
import javax.servlet.http.*;
import net.sf.json.*;
import org.apache.commons.lang.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.*;
import com.jkkp.common.BaseController;
import com.jkkp.modules.supplier.mapper.*;
import com.jkkp.modules.supplier.view.*;
import com.jkkp.utils.*;

@Controller
public class SupplierUserSVController extends BaseController{
	public static final String head="/supplieruser";
	@Autowired public SupplierUserMapper dao;
	@ResponseBody @RequestMapping(head+"_list.do")//装修用户列表
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
					List<VSupplierUser> list=dao.getPageList(pager);
					AllDao.copyProperties(rs,pager);
					rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
				}
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
			and+=" order by contact_mobile  ";//排序方式   1-预算高优先   2-预算低优先【找不到预算字段，随便找了手机做排序】
		}
		System.out.println(BaseTools.getIP(request)+"."+getClass().getSimpleName()+".getAndByJSON.and=\n"+and);
		return and;
	}
}
