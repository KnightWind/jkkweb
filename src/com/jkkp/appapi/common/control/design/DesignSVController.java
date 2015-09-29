package com.jkkp.appapi.common.control.design;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IAgreementSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IDesignImageSV;
import com.jkkp.appapi.common.service.interfaces.IDesignSV;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.appapi.common.service.interfaces.ISuppComStaffSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.VDesign3img;
import com.jkkp.appapi.modules.mapper.VIDesign;
import com.jkkp.appapi.modules.mapper.VIEngneeringsV1;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.model.Case;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.ICaseService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.model.DesignImage;
import com.jkkp.modules.design.service.IDesignCateService;
import com.jkkp.modules.design.service.IDesignService;
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.JsonUtil;

@Controller
@Scope("prototype")
@RequestMapping("/")
public class DesignSVController extends BaseController{
	@Autowired 
	ISuppComStaffSV suppComStaffSV; 
	//装入自动组装返回报文格式
	@Autowired 
	CommonJsonMap commonJsonMap;
	@Autowired
	IAppointmentPushSV appointmentSV;
	@Autowired
	IDesignService designService;
	@Autowired
	IDesignSV designSV;
	@Autowired
	IDesignImageSV designImageSV;
	@Autowired
	IAppointmentService appAppointmentService;
	@Autowired
	IDesignCateService designcatesv;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private ICaseService caseService;
	@Autowired 
	private IAppointmentPushService appointmentpushservice;
	@Autowired
	ISupplierSV suppsv;
	@Autowired private IAgreementSV aservice;
	
	@Autowired IEngineeringsService engservice;// 
	
	
	@Autowired IPaymentRecordService ipay;
	@ResponseBody
	@RequestMapping("/uploadDemo.do")
	public Object uploadDemo(HttpServletRequest request) {	
		Map<String, Object> paramMap = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("mess", "操作成功");
		resultMap.put("doneCode", "0000");
		try{
			List<Attachment> attachment = attachmentService.uploadMulti((MultipartRequest) request);
			if (attachment != null) {
				int mainid = 2;
				Admin admin = new Admin();
				admin.setId(1);
				attachmentService.saveAttachment(attachment, admin, mainid, AttachmentConstant.DESIGN_TYPE);
				
				List<String> path = attachmentService.findForDownload(mainid, AttachmentConstant.DESIGN_TYPE);
				for (String string : path) {
					System.out.println(string);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("doneCode", "9999");
			resultMap.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(null,resultMap);
		}
	}
	
	
	/**
	 * 保存或更新装修方案
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/design_add.do")
	public Object designAdd(HttpServletRequest request) {	
		Map<String, Object> paramMap = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("mess", "操作成功");
		resultMap.put("doneCode", "0000");
		String fileName="";
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(resultMap,paramMap, request))
			{				
				return -1;
			}
			paramMap = commonJsonMap.setRequestMap(request);
			//if(request instanceof MultipartRequest){
				resultMap = designService.doTranscAddDesign(resultMap,request, paramMap);
//			}else{
//				resultMap.put("doneCode", "9999");
//				resultMap.put("mess", "不是合法的文件上传表单request");
//			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("doneCode", "9999");
			resultMap.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,resultMap);
		}
		
	}


	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/design_del.do")
	public Object designDel(HttpServletRequest request) {	
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		ArrayList<Integer> pid=null;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		Integer fPid;
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);			
			pid =  (ArrayList<Integer>) JsonUtil.jsonToList(map.get("pid").toString(),Integer.class);
			String fileBuffer = "";
			for(int i=0 ;i<pid.size();i++){
				fPid=pid.get(i);
				//删除图片
				List<DesignImage> designImages=designImageSV.selectByProperty("pid", fPid);
				Attachment attachment=attachmentService.findById(fPid);
				//删除表记录
				for(int j=0;j<designImages.size();j++)
					designImageSV.delete(designImages.get(j));
				//删除服务器文件
				if(attachment!=null){
					attachmentService.delete(attachment);
					HttpFileTools.deleteFile(attachment.getFilepath());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/design_image_del.do")
	public Object designImgeDel(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		ArrayList<String> urls = null;
		String url;
		Map dataMap = null;
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			urls = (ArrayList<String>) JsonUtil.jsonToList(map.get("urls")
					.toString(), String.class);
			String designId =(String)map.get("designId");
			
			String fileBuffer = "";
			Map pmap = new HashMap();
			pmap.put("mainid", designId);
			dataMap = attachmentService.doTransacDelImge(urls, pmap);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(dataMap, mapBusi);
		}
	}


	
	/**
	 * 查询appopintment_push表
	 * 查询design表
	 * 查询图片信息表

	 * 如果传了工程单id：	engineerId，则查询工程单id关联的设计方案
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/design_query_detail.do")
	public Object designQueryByPushId(HttpServletRequest request) { 
		List<VIDesign> vDesign = null;
		Appointment app = null;
		List<Design> ds1 = null;
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<VAttachment> desimg = null;
		List<VAttachment> locimg = null;
		List<VAttachment> headimg = null;//头像
		List<String> desimgs = new ArrayList<String>();
		List<String> locimgs = new ArrayList<String>();
		List<String> headimgs = new ArrayList<String>();
		Double quote = null;
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			Integer appointmentPushId=null;
			Integer engineerId=null;
			SupplierCompanyStaff designStaff=null;
			Integer spId = null;
			Integer appointmentId = null;
			if(map.get("appointmentPushId")!=null&&!"".equals(map.get("appointmentPushId"))){
				appointmentPushId = Integer.valueOf(map.get("appointmentPushId")
						.toString());
			}
			if(map.get("engineerId")!=null&&!"".equals(map.get("engineerId"))){
				engineerId = Integer.valueOf(map.get("engineerId")
						.toString());
			}
			
			
			 //如果有工程单id 查询出工程单的信息
			if(engineerId!=null){
				Engineerings engr = engservice.findById(engineerId);
				if(engr==null){
					mapBusi.put("mess", "没有查到工程单信息");
					mapBusi.put("doneCode", "0009");
					return -1;
				}
				appointmentPushId = engr.getAppPushId();
				map.put("designId", engr.getDesignId());
			}
			//----pushid不为空 查询出push单的信息
			AppointmentPush push = appointmentSV.findById(appointmentPushId);
			if (push != null){
				quote = push.getQuote();// 报价
				spId = push.getSpId();
				appointmentId = push.getAid();
			}
			String bn=Sysconfig.CONFIG_PARAM.get(ConfigConstant.PHOTO_PREFIX_URL);
			// 公司头像
			List<VAttachment> comimg = attachmentService.findAttachment(spId,AttachmentConstant.SUPPLIER_COMPANY_TYPE);
			if (comimg.size() > 0)
				data.put("companyimg",bn+comimg.get(0).getFilepath());// 公司头像
			else {
				data.put("companyimg", "");
			}
			Supplier supplier = suppsv.findById(spId);
			data.put("companyname", supplier.getSpName());// 商家名字
			data.put("spid", spId);// 商家id
			Design design = new Design();
			design.setAid(appointmentId);
			design.setSpId(spId);
			ds1 = designService.select(design);
			//假如含有designId就根据designId查询 
			if(map.containsKey("designId")){
				Design design1=designService.findById(Integer.valueOf(map.get("designId").toString()));
				if(design1!=null){
					ds1.add(design1);
				}
				data.put("did",design1.getId());
			}
			
			//先保存空串
			data.put("agreementid",null);
			//
			
			
			if (!ds1.isEmpty()) {
				//查询设计师的信息
				List designStaffList = suppComStaffSV.selectByProperty(new String[]{"id","sid"}, new Integer[]{ds1.get(0).getStaffid(),1});
				if(designStaffList!=null&&designStaffList.size()>0){
					designStaff = (SupplierCompanyStaff)designStaffList.get(0);
					data.put("designerJob", designStaff.getJob());// 设计师职位
				}else{
					data.put("designerJob", "");// 设计师职位
				}
				//
				desimg = attachmentService.findAttachment(ds1.get(0).getId(),
						AttachmentConstant.DESIGN_TYPE);
				locimg = attachmentService.findAttachment(ds1.get(0).getId(),
						AttachmentConstant.LOCALE_TYPE);
				//新增设计师头像返回
				headimg =  attachmentService.findAttachment(ds1.get(0).getStaffid(),
						AttachmentConstant.SUPPLIER_STAFF_TYPE);
				
				for (VAttachment va : desimg) {
					desimgs.add(bn+va.getFilepath());
				}
				for (VAttachment va : locimg) {
					locimgs.add(bn+va.getFilepath());
				}
				for (VAttachment va : headimg) {
					headimgs.add(bn+va.getFilepath());
				}
				data.put("desimg", desimgs);// 小区信息
				data.put("locimg", locimgs);// 小区信息
				data.put("designerHeadimg", headimgs);// 头像
				data.put("totaltime",
						((ds1.get(0).getStartWork()==null?0:ds1.get(0).getStartWork())+ 
					     (ds1.get(0).getWhiteFuel()==null?0:ds1.get(0).getWhiteFuel())+
						 (ds1.get(0).getTileWood() ==null?0:ds1.get(0).getTileWood()) + 
						 (ds1.get(0).getCompletion()==null?0:ds1.get(0).getCompletion())));
				// vDesign=designImageSV.queryDesignDetail(map);

				data.put("desrooms",
						designcatesv.findname(ds1.get(0).getHuxing()));// 户型
				data.put("desstyle",
						designcatesv.findname(ds1.get(0).getFengge()));// 风格
				if(ds1.get(0).getMethod()==1){
					data.put("desmethod", "半包");// 方式
				}else if(ds1.get(0).getMethod()==2){
					data.put("desmethod", "全包");// 方式
				}else{
					data.put("desmethod", "");// 方式
				}
				data.put("desspace", ds1.get(0).getSpace());// 面积
				data.put("desremark", ds1.get(0).getRemark());// 备注

				data.put("designername", ds1.get(0).getDesigner());// 设计师名字
				List<String> headString = attachmentService.findForDownload(ds1
						.get(0).getStaffid(),
						AttachmentConstant.SUPPLIER_STAFF_TYPE);
				if (headString.size() > 0)
					data.put("designerheadimg", headString.get(0));// 商家名字
				data.put("designerid", ds1.get(0).getStaffid());// 设计师ID
                data.put("work_time",ds1.get(0).getWorkTime());
				data.put("su_type", ds1.get(0).getSuType());// 房型
				data.put("start_work", ds1.get(0).getStartWork());// 开工工期
				data.put("white_fuel", ds1.get(0).getWhiteFuel());// 水电工期(天)
				data.put("tile_wood", ds1.get(0).getTileWood());// 瓦木工期(天)
				data.put("completion", ds1.get(0).getCompletion());// 竣工工期(天)
				data.put("expectedDuration", ds1.get(0).getDuration());//预计工期
				data.put("designId", ds1.get(0).getId());//方案id
				// 合同
//				Agreement agreement = new Agreement();
//				agreement.setGcdId(ds1.get(0).getId());
//				List<Agreement> alist = aservice.select(agreement);
//				if (alist.size() > 0)
				//直接从design表取  bypanqiong
				List<Agreement> ag =aservice.selectByProperty("appointmentpushId", push.getId());
				if(ag.size()>0)
					data.put("agreementid",  ag.get(0).getId());
				else {
					data.put("agreementid",  ds1.get(0).getAgreementId());
				}
			} else {
				data.put("desimg", desimgs);// 设计图片
				data.put("locimg", locimgs);// 实际图片
				data.put("designerHeadimg", headimgs);// 头像
				data.put("totaltime", "");
				// vDesign=designImageSV.queryDesignDetail(map);  
				data.put("desrooms", "");// 户型
				data.put("desstyle", "");// 风格
				data.put("desmethod", "");// 装修方式
				data.put("desspace", "");// 面积
				data.put("desremark", "");// 备注

				data.put("designername", "");// 设计师名字
				data.put("designerheadimg", "");
				data.put("designerid", "");// 设计师ID

				data.put("agreementid", "");
				data.put("designId", "");//方案id
				data.put("designerJob", "");// 设计师职位
			}
			app = appAppointmentService.findById(appointmentId);
			if (app != null) {
				// data.put("appointment", app);//预约信息

				data.put("acommunity", app.getCommunity());// 小区信息
				data.put("aspace", app.getSpace());// 小区信息
				if (app.getHousestyle() != null)
					data.put("arooms",
							designcatesv.findname(app.getHousestyle()));// 户型
				else {
					data.put("arooms", "");
				}
				if (app.getHtBedroom() != null)
					data.put("astyle",
							designcatesv.findname(app.getHtBedroom()));// 风格
				else {
					data.put("astyle", "");
				}
				data.put("amethod", app.getHousestyle());// 半包全包
				data.put("aquote", app.getBudget());// 半包全包
			}
			data.put("payDepositSatus", appointmentSV.queryToPayDeposit(appointmentPushId));// 支付定金
			data.put("payJianLiSatus", appointmentSV.queryToPayProject(appointmentPushId));// 支付监管款
			if(push.getStatus()==ConstantAppStatus.PUSH_YI_FU_KUAN){
				data.put("payJianLiSatus", 2);//如果已经付监管款
			}

			data.put("paymoney",appointmentpushservice.calculatePayAmount(push));//如果有交定金就减去，实际付款

			data.put("deposit", push.getMoney());//查询固定的定金大小
			data.put("quote", quote);// 报价
			data.put("duemoney", appointmentpushservice.calculatePayDue(push));//应付款
			data.put("paystatus", app.getStatus());// 

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
			data.clear();
		} finally {
			return commonJsonMap.autoMap(data, mapBusi);
		}
	}
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/qryalldesign.do")
	public Object qryalldesign(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage="";
		List<VIDesign> sclist=null;
		Map<String, Object> pagination= new HashMap<String, Object>();
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
     		currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
     		sclist=designImageSV.queryAllDesign(map);
     		for(VIDesign onesc:sclist){//设置商家头像
     			List<String> url=attachmentService.findForDownload(onesc.getDesignerid(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
     			if(!url.isEmpty())
     				onesc.setHeadurl(url.get(0));
     		}
			map= PaginationInterceptor.nextPagination(map);
			if(designImageSV.queryAllDesign(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());			
		} finally {
			return commonJsonMap.autoMap(sclist,mapBusi); 
		}
	}
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/qryonedesign.do")
	public Object qryonedesign(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage="";
		List<VIDesign> sclist=null;
		Map<String, Object> pagination= new HashMap<String, Object>();
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
  /*   		currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			map= PaginationInterceptor.pagination(map);*/
			//查询下一个页面数否有数据，如无则返回hastnest为false
     		sclist=designImageSV.queryOneDesign(map);
	/*		map= PaginationInterceptor.nextPagination(map);
			if(designImageSV.queryOneDesign(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);*/
			Design ds=designService.findById(Integer.valueOf((String) map.get("designid")));
			if(ds!=null)
			{
				data.put("design", ds);
			}
			data.put("designimg", sclist);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());			
		} finally {
			return commonJsonMap.autoMap(data,mapBusi); 
		}
	}
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/qryonedesigndetial.do")
	public Object qryonedesigndetial(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		Design ds=null;
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			ds=designService.findById(Integer.valueOf((String) map.get("designid")));
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());			
		} finally {
			return commonJsonMap.autoMap(ds,mapBusi); 
		}
	}
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/qryonedesigndimg.do")
	public Object qryonedesignimg(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<VIDesign> sclist=null;
		Map<String, Object> pagination= new HashMap<String, Object>();
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
     		sclist=designImageSV.queryOneDesign(map);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());			
		} finally {
			return commonJsonMap.autoMap(sclist,mapBusi); 
		}
	}
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/qrydesignd3img.do")
	public Object qrydesignd3img(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage="";
		List<VDesign3img> sclist=null;
		Map<String, Object> pagination= new HashMap<String, Object>();
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			map.put("curpage", "0");
			map.put("pageSize", "4");
			
     		currentPage=(String) map.get("curpage");
			pagination.put("currentPage", currentPage);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
     		sclist=designSV.qrydesigns(map);
     		for(VDesign3img onesc:sclist){//设置商家头像
     			List<String> url=attachmentService.findForDownload(onesc.getId(), AttachmentConstant.DESIGN_TYPE);
     			if(onesc.getBigimg()!=null&onesc.getBigimg()!="")
     				onesc.setBigimg(attachmentService.getBasePath()+onesc.getBigimg());//封面
     			else {
     				onesc.setBigimg("");
				}
     			onesc.setImg1("");
     			onesc.setImg2("");
     			if(url.size()>1)
     				onesc.setImg1(url.get(0));
     			if(url.size()>2)
     				onesc.setImg1(url.get(1));
     		}
			map= PaginationInterceptor.nextPagination(map);
			if(designSV.qrydesigns(map).size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());			
		} finally {
			return commonJsonMap.autoMap(sclist,mapBusi); 
			
		}
	}
	

}
