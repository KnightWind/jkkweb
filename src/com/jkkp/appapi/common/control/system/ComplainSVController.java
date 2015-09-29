package com.jkkp.appapi.common.control.system;

import com.jkkp.common.BaseController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.modules.system.mapper.ComplainMapper;
import com.jkkp.modules.system.view.VComplain;
import com.jkkp.utils.*;
@Controller
public class ComplainSVController extends BaseController {
	public static final String head="/complain_";
	@Autowired public ComplainMapper dao;
	@ResponseBody @RequestMapping(head+"list.do")//投诉单列表
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
				List<VComplain> list=dao.getPageList(pager);
				AllDao.copyProperties(rs,pager);
				rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
			}
			BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(head+"detail.do")//投诉单详情
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
				VComplain bean=dao.getBeanById(Integer.parseInt(id.toString().trim()));
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
	public String getAndByJSON(HttpServletRequest request,JSONObject json){//ysc查询条件
		String and="",enter=" \n";
		and+=BaseTools.getAndByJson(json,"id","id", "1", "1");
		and+=BaseTools.getAndByJson(json,"community","community", "1");
		String wtype=request.getParameter("wtype");
		if("dkg".equals(wtype)){//待开工
			and+=" and start_time is null ";
		}else if("ykg".equals(wtype)){//已开工
			and+=" and start_time is not null ";
		}else if("yjs".equals(wtype)){//已结束
			and+=" and end_time is not null ";
		}
		Object pointx=json.get("pointx");Object pointy=json.get("pointy");
		boolean flag1=pointx!=null&&StringUtils.isNotBlank(pointx.toString())&&BaseTools.isNumber(pointx);
		boolean flag2=pointy!=null&&StringUtils.isNotBlank(pointy.toString())&&BaseTools.isNumber(pointy);
		if(flag1&&flag2){//查询周边工地
			Object distance=json.get("distance");
			if(distance==null) distance=2000;			//默认方圆2000米
			and="and power(pointx-"+pointx+",2)+power(pointy-"+pointy+",2)<=power("+distance+"/11111.1,2)"+enter;
		}else{
			if(flag1) and+=" and pointx="+pointx+" "+enter;
			if(flag2) and+=" and pointy="+pointy+" "+enter;
		}
		System.out.println(BaseTools.getIP(request)+"."+getClass().getSimpleName()+".getAndByJSON.and=\n"+and);
		return and;
	}

}
