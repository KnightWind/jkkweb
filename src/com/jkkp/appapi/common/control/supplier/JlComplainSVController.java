package com.jkkp.appapi.common.control.supplier;
import java.io.File;
import java.util.*;

import javax.servlet.http.*;
import net.sf.json.*;

import org.apache.commons.lang.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.jsonmap.*;
import com.jkkp.common.*;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.supplier.view.*;
import com.jkkp.utils.*;
@Controller
public class JlComplainSVController extends BaseController{
	public static final String pfix="/jl_complain_";
	@ResponseBody @RequestMapping(pfix+"list.do")	//监理投诉列表
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
					Pager pager=new Pager(and,json_obj,allDao.jlComplainMapper.getCount(and));
					List<VJlComplain> list=allDao.jlComplainMapper.getPageList(pager);
					AllDao.copyProperties(rs,pager);
					rs.put("data",JSONArray.fromObject(list,AllDao.jcfg));
				}
			}
			System.out.println(JSONObject.fromObject(rs,AllDao.jcfg));
			if(AllDao.DEBUG) BaseTools.showMessageByMap(rs,"");
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(pfix+"detail.do")//监理投诉详情
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
				if(StringUtils.isBlank(id)) id=BaseTools.getValueByKey(jobj,"id");
				if(StringUtils.isBlank(id)){rs.put("mess","没有id参数");return rs;}
				VJlComplain bean=allDao.jlComplainMapper.getBeanById(Integer.parseInt(id.trim()));
				if(bean==null){rs.put("mess","记录不存在");return rs;}
				rs.put("data",JSONObject.fromObject(bean,AllDao.jcfg));
				rs.put("ver", "1.0");rs.put("ret", "");
			}
		} catch (Exception ee) {
			rs.put("doneCode", "9999");
			rs.put("mess", ee.getMessage());
		}return rs;
	}
	@ResponseBody @RequestMapping(pfix+"tousu.do")//投诉
	public Map<String,Object> tousu(HttpServletRequest request) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		Map<String, Object> map =null;
		String json=request.getParameter("json");try {
			if(StringUtils.isBlank(json)){
				rs.put("doneCode", "0001");
				rs.put("mess","没有json参数");
			}else{
				boolean flag=ApiCommonUtil.dealCommonBuget(rs,map,request);
				if(flag==false){rs.put("mess","安全校验不通过");return rs;}
				JSONObject jobj=JSONObject.fromObject(json);
				VJlComplain bean=new VJlComplain();
				bean=(VJlComplain)AllDao.copyProperties(bean,jobj);
				if(bean.gcdId==null||bean.gcdId==0){rs.put("mess","工程单【gcdId】不能为空");return rs;}	
				if(bean.spId==null||bean.spId==0){rs.put("mess","投诉对象【spId】不能为空");return rs;}		
				VEngineerings engi=allDao.engineeringsMapper.getBeanById(bean.gcdId);		
				VSupplier supp=allDao.supplierMapper.getBeanById(bean.spId);
				if(engi==null||engi.id==null||engi.id==0){rs.put("mess","工程单id【"+bean.gcdId+"】不存在");return rs;}
				if(supp==null||supp.id==null||supp.id==0){rs.put("mess","投诉对象【"+bean.spId+"】不存在");return rs;}		
				bean.createTime=new Date();
				bean.status=2;		//状态1已解决2待处理
				//bean.type=1;		//投诉类型：1业主投诉监理2监理投诉业主
				//bean.source=2;	//投诉来源：1预约时2看工地3施工工程
				System.out.println(getClass()+".tousu.bean\n"+JSONObject.fromObject(bean,AllDao.notBlankJcfg)+"");
				System.out.println("request【"+request+"】");
				int iflag=allDao.jlComplainMapper.insert(bean);
				System.out.println("监理投诉.attach.fileType【"+AttachmentConstant.JL_COMPLAIN_TYPE+"】");
				if(iflag>0&&request instanceof MultipartRequest){//如果是带附件提交则执行
					MultipartRequest mrequest=(MultipartRequest)request;
					String rootPath=request.getSession().getServletContext().getRealPath(""); 
					File rootFile=new File(rootPath).getParentFile();
					Map<String, MultipartFile> fmap = mrequest.getFileMap();
					for (String kk : fmap.keySet()) {
						MultipartFile of = fmap.get(kk);
						if(of.isEmpty()) continue;
						String ftype=BaseTools.getFileType(of.getOriginalFilename());
						String fpath="/attachment/"+AttachmentConstant.FILE_PATH;
						fpath+="/"+BaseTools.getNowDate()+"/"+BaseTools.createTimePK()+"."+ftype;
						File dest=new File(rootFile,fpath);
						if(dest.getParentFile().exists()==false) dest.mkdirs();
						of.transferTo(dest);
						System.out.println("kk【"+kk+"】fpath【"+fpath+"】ftype【"+ftype+"】");
						Attachment attc=new Attachment();
						attc.filename=ftype;
						attc.filepath=fpath;
						attc.createTime=new Date();
						attc.mainid=bean.id;
						attc.adminId=bean.adminId;
						attc.memberId=BaseTools.getIntByKey(jobj, "memberId");
						attc.filetype=AttachmentConstant.JL_COMPLAIN_TYPE;
						allDao.attachmentMapper.insert(attc);
						System.out.println("添加附件【"+JSONObject.fromObject(attc,AllDao.notBlankJcfg)+"】");
					}
				}
				rs.put("mess",iflag>0?"添加成功":"添加失败");
				rs.put("ver", "1.0");rs.put("ret", "");
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
		and+=BaseTools.getAndByJson(json,"spId","sp_id", "1","1");
		and+=BaseTools.getAndByJson(json,"gcd_id","gcd_id", "1","1");
		and+=BaseTools.getAndByJson(json,"gcdId","gcd_id", "1","1");
		System.out.println(BaseTools.getIP(request)+"."+getClass()+".getAndByJSON.and=\n"+and);
		return and;
	}
	
}
