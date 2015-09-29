package com.jkkp.appapi.common.control.basedata;
import java.util.*;
import javax.servlet.http.*;
import net.sf.json.*;
import org.apache.commons.lang.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.jkkp.appapi.common.jsonmap.*;
import com.jkkp.common.*;
import com.jkkp.modules.basedata.mapper.*;
import com.jkkp.modules.basedata.view.*;
import com.jkkp.utils.*;

@Controller
public class EngineeringStageSVController extends BaseController {
	public static final String pfix="/engineering_stage_";
	@Autowired public CommonJsonMap commonJsonMap;
	@Autowired public EngineeringStageMapper dao;
	@ResponseBody @RequestMapping(pfix+"list.do")//验收阶段列表
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
				List<VEngineeringStage> list=dao.getPageList(pager);
				AllDao.copyProperties(rs,pager);
				for (int i = 0; i < list.size(); i++) {
					VEngineeringStage temp=list.get(i);
					String tand=" and stage_id='"+temp.id+"' ";
					String jlId=BaseTools.getValueByKey(json_obj,"jlId");
					if(StringUtils.isNotBlank(jlId)){
						tand+=" and exists(";
						tand+=" select yy.* from engineerings yy";
						tand+=" where yy.id=t.gcd_id and yy.jl_id='"+jlId+"'";
						tand+=" ) ";
					}
					String gcdId=BaseTools.getValueByKey(json_obj,"gcdId");
					if(StringUtils.isNotBlank(gcdId)) tand+=" and gcd_id='"+gcdId+"' ";
					temp.checkRequests=allDao.checkRequestMapper.getList(tand);
				}
				//rs.put("data",list);
				rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
			}
			//BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(pfix+"detail.do")//验收请求详情
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
				VEngineeringStage bean=dao.getBeanById(Integer.parseInt(id));
				if(bean==null){rs.put("mess","记录【"+id+"】不存在");return rs;}
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
		String and="",enter=" \n";
		and+=BaseTools.getAndByJson(json,"id","id", "1", "1");
		and+=BaseTools.getAndByJson(json,"abbreviation","abbreviation", "1", "1");
		String jlId=BaseTools.getValueByKey(json, "jlId");
		if(StringUtils.isNotBlank(jlId)){
			and+=" and exists("+enter;
			and+=" select xx.* from check_request xx"+enter;
			and+=" where xx.stage_id=t.id "+enter;
			and+=" and exists("+enter;
			and+=" select yy.* from  engineerings yy"+enter;
			and+=" where yy.id=xx.gcd_id and yy.jl_id='"+jlId+"'"+enter;
			and+=" ))"+enter;
		}
		String gcdId=BaseTools.getValueByKey(json, "gcdId");
		if(StringUtils.isNotBlank(gcdId)){
			and+=" and t.id in(select xx.stage_id from check_request xx where xx.gcd_id="+gcdId+")"+enter;
		}
		and+=BaseTools.getAndByJson(json,"status","status", "1", "1");
		and+=BaseTools.getAndByJson(json,"sub_type","sub_type", "1", "1");
		and+=BaseTools.getAndByJson(json,"subType","sub_type", "1", "1");
		and+=BaseTools.getAndByJson(json,"stagName","stagName", "1");
		String spId=BaseTools.getValueByKey(json,"spId");
		if(StringUtils.isNotBlank(spId)){
			and+="and exists( "+enter;
			and+=" select distinct(t1.stage_id) "+enter;
			and+=" from check_request t1 "+enter;
			and+=" where t1.stage_id is not null "+enter;
			and+=" and t.id=t1.stage_id "+enter;
			and+=" and exists( "+enter;
			and+=" select distinct(t2.sp_id) from engineerings t2 "+enter;
			and+=" where t2.sp_id is not null "+enter;
			and+=" and t2.sp_id='"+spId+"' "+enter;
			and+=" and t2.id=t1.gcd_id )"+enter;
			and+=")"+enter;
		}
		and+="";
		String orderBy1=BaseTools.getValueByKey(json, "orderBy1");
		String orderBy2=BaseTools.getValueByKey(json, "orderBy2");
		if(StringUtils.isNotBlank(orderBy1)) and+=" and ordr_by>="+orderBy1+" "+enter;
		if(StringUtils.isNotBlank(orderBy2)) and+=" and ordr_by<="+orderBy2+" "+enter;
		String pid=BaseTools.getValueByKey(json,"pid");
		if("0".equals(pid)){
			and+=" and (pid is null or pid=0) "+enter;
		}else{
			and+=BaseTools.getAndByJson(json,"pid","pid", "1", "1");
		}
		System.out.println(BaseTools.getIP(request)+"."+getClass()+".getAndByJSON.and=\n"+and);
		return and;
	}
}