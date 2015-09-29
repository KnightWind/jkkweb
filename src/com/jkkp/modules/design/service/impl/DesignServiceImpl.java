package com.jkkp.modules.design.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IDesignSV;
import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentMemberService;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.model.Case;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.ICaseService;
import com.jkkp.modules.basedata.service.IEngineeringsService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.design.mapper.DesignMapper;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.service.IDesignImageService;
import com.jkkp.modules.design.service.IDesignService;
import com.jkkp.modules.design.view.VDesign;
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.modules.order.service.IAgreementService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.FileUtil;

@Service("designService")
public class DesignServiceImpl extends ServiceSupport<Design, VDesign, Integer> implements IDesignService {
	@Autowired
	private DesignMapper designMapper;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private IDesignImageService designImageService;
	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private IAppointmentMemberService appointmentMemberService;
	@Autowired
	private IEngineeringsService engineeringsService;
	@Autowired
	private ICaseService caseService;
	@Autowired
	private IAppointmentPushService appointmentPushService;
	@Autowired
	private ISupplierCompanyStaffService supplierCompanyStaffService;
	
	@Value("#{application['system.application.sitepath']}")
	private String sitePath;
	@Value("#{application['system.attachment.uploadpath']}")
	private String uploadPath;

	
	@Autowired
	private IAppointmentPushSV appointmentSV;
	@Autowired
	private IDesignSV designSV;
	@Autowired
	private IAppointmentService appAppointmentService;
	@Autowired
	private IAgreementService agreementService;
	
	
	
	
	
	public String getBasePath() {
		return sitePath + uploadPath;
		// return ProjectContext.getRealPath();
	}
	
	@Override
	protected Mapper<Design> getMapper() {
		return designMapper;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Design design) {
		this.save(design);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void remove(Integer id) {
		this.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Design operate(Integer id, boolean isOpen) {
		Design design = this.findById(id);
		if (isOpen) {
			design.setStatus(new Byte("-1"));
			design.setCreateTime(new Date());
		} else {
			design.setStatus(new Byte("1"));
			design.setCreateTime(new Date());
		}
		this.update(design);
		return design;
	}

	@Override
	public VDesign convertBeanToView(Design entity) {
		VDesign vDesign = super.convertBeanToView(entity);

		vDesign.setDname("xx");
		vDesign.setEname("xx");
		vDesign.setFname("xx");
		return vDesign;
	}

	@Override
	public List<Design> fin(Integer id) {
		return designMapper.fin(id);
	}

	@Override
	public VDesign engineeringDesignDetail(int id) {
		return designMapper.engineeringDesignDetail(id);
	}

	@Transactional
	public void deleteDesignById(Integer id){
		List<VAttachment> xianChangList = attachmentService.findAttachment(id, AttachmentConstant.LOCALE_TYPE);
		List<VAttachment> xiaoGuoList = attachmentService.findAttachment(id, AttachmentConstant.DESIGN_TYPE);
		for (VAttachment vAttachment : xianChangList) {
			attachmentService.delete(vAttachment);
		}
		for (VAttachment vAttachment : xiaoGuoList) {
			attachmentService.delete(vAttachment);
		}
		this.deleteById(id);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(VSupplierUser su, Design design,Integer[] imgId,Integer pid, String hremark,HttpServletRequest request) {
		
		Supplier supplier = supplierService.findById(su.getSpId());
		SupplierCompanyStaff staff = supplierCompanyStaffService.findById(design.getStaffid());
		Agreement agr = new Agreement();
		Integer designId;
		if(design.getId() != null && design.getId() > 0){
			Design odlDesign=this.findById(design.getId());
			designId = odlDesign.getId();
			//odlDesign.setDesignName(design.getDesignName());
			if(staff != null)
				odlDesign.setDesigner(staff.getName());
			//odlDesign.setForman(design.getForman());
			//odlDesign.setCompany(design.getCompany());
			odlDesign.setHuxing(design.getHuxing());
			odlDesign.setFengge(design.getFengge());
			odlDesign.setKongjian(design.getKongjian());
			odlDesign.setSuType(design.getSuType());
			odlDesign.setSpace(design.getSpace());
			odlDesign.setDuration(design.getDuration());
			odlDesign.setRemark(design.getRemark());
			odlDesign.setBudget(design.getBudget());
			odlDesign.setWorkTime(design.getWorkTime());
			odlDesign.setStartWork(design.getStartWork());
			odlDesign.setWhiteFuel(design.getWhiteFuel());
			odlDesign.setCompletion(design.getCompletion());
			odlDesign.setTileWood(design.getTileWood());
			odlDesign.setStaffid(design.getStaffid());
			//odlDesign.setCommunity(design.getCommunity());
			odlDesign.setMethod(design.getMethod());
			
			//根据装修公司id跟预约单id确定一个推送表app_push记录，更新其报价
			if(odlDesign.getAid() != null){
				AppointmentPush apush = appointmentPushService.findPushBySpIdAndAid(odlDesign.getSpId(),odlDesign.getAid());
				apush.setQuote(Double.valueOf(design.getBudget()));
				appointmentPushService.update(apush);
				Agreement agrs = agreementService.selectByPushId(apush.getId());
				if(agrs != null){
					agrs.setRemark(hremark);
					agreementService.update(agrs);
					agr = agrs;
				}else{
					agr.setCreateTime(new Date());
					agr.setAppointmentpushId(apush.getId());
					agr.setRemark(hremark);
					agreementService.save(agr);        
				}
			}else{
				this.InsertInvalidData(hremark, agr,odlDesign,su);        
			}
			//删除更换后的图片记录跟   imgId[] 更换图片后对应附件表的id集合
			List<Integer> ids = new ArrayList<Integer>();
			if(imgId != null){
				for (Integer attId : imgId) {
					if(attId != null){
						Attachment at = attachmentService.findById(attId);
						if(at != null){
							HttpFileTools.deleteFile(at.getFilepath());
							attachmentService.delete(at);
							ids.add(attId);
						}
					}
				}
			}
			odlDesign.setStatus(new Byte("0"));
			this.update(odlDesign);
		}else{
			Case ca = new Case();
			List<AppointmentMember> ams = null;
			//更新
			AppointmentPush ap = appointmentPushService.findById(pid);
			if(ap != null){
				agr.setAppointmentpushId(ap.getId());
				ap.setQuote(Double.valueOf(design.getBudget()));
				appointmentPushService.update(ap);
				Appointment app = appointmentService.findById(ap == null ? null : ap.getAid());
				if(app != null){
					ca.setAid(app.getId());
					design.setAid(app.getId());
				}
				if(app.getMethod() != null)
					design.setMethod(app.getMethod());
				if(app.getGcdId() != null){
					agr.setGcdId(app.getGcdId());
					ca.setGcdId(app.getGcdId());
				}
				if(app.getCommunity() != null)
					ca.setCommunity(app.getCommunity());
				if(app.getSpace() != null)
					ca.setSpace(app.getSpace());
				if(app.getMethod() != null)
					ca.setMethod(app.getMethod());
				ams = appointmentMemberService.selectByProperty("aid", app.getId());
				
			}else{
				this.InsertInvalidData(hremark, agr,design, su);
			}
			
			if(staff != null)
				design.setDesigner(staff.getName());
			design.setSpId(su.getSpId());
			design.setStatus(new Byte("0"));
			design.setIsLock(new Byte("0"));
			design.setUid(su.getId());
			design.setBidding(0);
			design.setCityDomain(supplier.getCityDomain());
			design.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
			this.save(design);
			designId = design.getId();
			
			//创建案例
			//Engineerings eng = engineeringsService.findById(app.getGcdId());
			ca.setSpId(supplier.getId());
			ca.setDesignId(designId);
			ca.setCreateTime(new Date());
			ca.setCreateUser(su.getUsername());
			ca.setCaseSource(1);
			ca.setBudget(design.getBudget());
			ca.setHouseType(design.getHuxing());
			ca.setStyle(design.getFengge());
			ca.setCommentCount(0);
			ca.setBrowseCount(0);
			ca.setSpace(design.getSpace());
			ca.setRemark(design.getRemark());
			ca.setSjsId(design.getStaffid());
			if(ams != null && ams.size() > 0){
				ca.setUid(ams.get(0).getMid().toString());
			}
			caseService.save(ca);
			
		}
		
		
		try {
			MultipartRequest req = (MultipartRequest) request;
			Map<String, MultipartFile> map = req.getFileMap();
			for (String fileName : map.keySet()) {
				MultipartFile file = map.get(fileName);
				if (!StringUtils.isNotEmpty(file.getOriginalFilename())) {
					continue;
				}
				if(file.getSize() > 0){
					//上传文件到服务器
					String filePath = AttachmentConstant.FILE_PATH + FileUtil.createPath(null);
					String realPath = this.getBasePath() + filePath;
					FileUtil.createFolder(realPath);
					String newFileName = FileUtil.newFileName(file.getOriginalFilename());
					FileUtil.copyFile(file.getInputStream(), realPath + "/" + newFileName);
					
					//在附件表添加图片记录
					Attachment attachment = new Attachment();
					attachment.setCreateTime(new Date());
					attachment.setFilename(file.getOriginalFilename());
					attachment.setFilepath(filePath + "/" + newFileName);
					Admin admin = new Admin();
					Integer fileType = 0;
					if(fileName.contains("desImg")){
						fileType = AttachmentConstant.DESIGN_TYPE;
						attachmentService.saveAttachment(attachment, admin, designId, fileType);
					}else if (fileName.contains("sceneImg")){
						fileType = AttachmentConstant.LOCALE_TYPE;
						attachmentService.saveAttachment(attachment, admin, designId, fileType);
					}else if (fileName.contains("hetongImg")){
						fileType = AttachmentConstant.AGREEMENT_TYPE;
						attachmentService.saveAttachment(attachment, admin, agr.getId(), fileType);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("文件上传出错！");
		}
		
	}

	private void InsertInvalidData(String hremark, Agreement agr, Design odlDesign,VSupplierUser su) {
		Appointment appointment = new Appointment();
		appointment.setStatus(-1);
		appointmentService.save(appointment);
		odlDesign.setAid(appointment.getId());
		AppointmentPush apush = new AppointmentPush();
		apush.setSupplierCount(0);apush.setSendCollectState(0);
		apush.setRandom(2);apush.setSpId(su.getSpId());
		apush.setAid(appointment.getId());
		apush.setStatus(new Byte("70"));
		appointmentPushService.save(apush);
		agr.setCreateTime(new Date());
		agr.setAppointmentpushId(apush.getId());
		agr.setRemark(hremark);
		agreementService.save(agr);
	}
	/**
	 * 添加设计方案 事务控制
	 * 查询预约单推送信息表Appointment_Push
	 * 查询案例 design表
	 * 更新Appointment_Push 状态 +报价
	 * 保存数据到装修设计design表
	 * 保存图片信息到attachment表
	 * 保存数据到case历史案例表
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Map doTranscAddDesign(Map<String, Object> resultMap,HttpServletRequest request,Map<String, Object> parammap) throws Exception{
		Integer appointmentPushId=Integer.valueOf((String)parammap.get("appointmentPushId"));
		String  remark = (String) parammap.get("remark");		
		Float  space = Float.parseFloat((String)parammap.get("space"));//面积	
		String suType =(String)parammap.get("fangxing");	
		Integer huxing = Integer.parseInt((String)parammap.get("huxing"));	
		String designer =(String)parammap.get("designer");
		Integer fengge = Integer.parseInt((String)parammap.get("fengge"));
		Integer method = Integer.parseInt((String)parammap.get("method"));
		Integer work_time = Integer.parseInt(((String)parammap.get("workTime")));
		Integer start_work =Integer.parseInt((String)parammap.get("startWork"));
		Integer whiteFuel =Integer.parseInt((String)parammap.get("whiteFuel"));
		Integer tileWood =Integer.parseInt((String)parammap.get("tileWood"));//瓦木工期
		Integer completion = Integer.parseInt((String)parammap.get("completion"));
		Float duration = Float.parseFloat((String)parammap.get("duration"));//工期
		Double  quote = Double.valueOf((String)parammap.get("quote"));//报价
		Float budget = Float.valueOf((String)parammap.get("quote"));
		Integer staffid = Integer.parseInt((String)parammap.get("staffid"));//设计师id
		Integer agreementId=null;
		boolean isAdd=true;//update:false

		//合同编号可以为空
		if((String)parammap.get("agreementId")!=null){
			agreementId= Integer.parseInt((String)parammap.get("agreementId"));//合同id
		}
		//获取商家推送表数据
		AppointmentPush appPush=appointmentSV.queryAppPushlByid(parammap);
		if(appPush==null){
			resultMap.put("doneCode", "9999");
			resultMap.put("mess","根据推送id查找数据为空");
			return resultMap;
		}
		if(appPush.getRandom()!=null){
			if(appPush.getRandom()==1||appPush.getRandom()==2){
				resultMap.put("doneCode", "9998");
				resultMap.put("mess","订单状态改变了，请刷新");
				return resultMap;
			}
		}
		//------获取接口参数
		
		 Design design=new Design();
		 design.setAid(appPush.getAid());
		 design.setSpId(appPush.getSpId());
		 List<Design> designs=this.select(design);
		 //判断时新增还是更新
		 if(designs!=null&&designs.size()>0){
			 //已存在  更新
			 isAdd=false;
		 }
		
		//预约推送单id
		//------结束
		//获取图片文件列表
		 List<Attachment> attachmentList=null;
		 if(request instanceof MultipartRequest){
			 attachmentList = attachmentService.uploadMulti((MultipartRequest) request);
		 }
		//如果是新增 必须上传图片
		if((attachmentList==null||attachmentList.size()==0)&&isAdd){
			resultMap.put("doneCode", "9999");
			resultMap.put("mess","保存文件失败");
			return resultMap;
		}
		//根据预约单id查询设计
		//更新推送信息
		updateAppPushInfo(appPush, quote);	
		
		//商家id
		Integer designId=0;
		Integer spId=appPush.getSpId();
		designId = saveDesignInfo(appPush.getAid(), remark, space, suType,
				huxing, designer, fengge, method, work_time, start_work,
				whiteFuel, tileWood, completion, duration, budget, designs,
				spId,staffid,agreementId);
		//保存图片信息
		saveFileInfo(attachmentList, designId);
		//-------------保存case表数据
		saveCaseInfo(appPush, space, huxing, fengge, method, budget,
				staffid, designId, spId);
		//---------保存case数据结束
		
		resultMap.put("designId", designId);
		return resultMap;
	}
	
	private void updateAppPushInfo(AppointmentPush appPush, Double quote) throws Exception  {
		//保存装修设计图报价 更新push表
		appPush.setQuote(quote);
		//更新状态为已量房
		appPush.setStatus(ConstantAppStatus.PUSH_YI_QIAN_YUE);
		appPush.setCreateTime(new Date());
		appointmentSV.update(appPush);
	}


	private Integer saveDesignInfo(Integer appointmentId, String remark,
			Float space, String suType, Integer huxing, String designer,
			Integer fengge, Integer method, Integer work_time,
			Integer start_work, Integer whiteFuel, Integer tileWood,
			Integer completion, Float duration, Float budget,
			List<Design> designs, Integer spId,Integer staffid,Integer agreementId)  throws Exception {
		Integer designId;
		Design design=new Design();
		if(designs.size()>0){
			design = designs.get(0);
		}
		design.setAid(appointmentId);
		design.setSpId(spId);
		design.setSpace(space);
		design.setHuxing(huxing);
		design.setSuType(suType);
		design.setBudget(budget);
		design.setDesigner(designer);
		design.setFengge(fengge);
		design.setMethod(method);
		design.setWorkTime(work_time);
		design.setStartWork(start_work);
		design.setWhiteFuel(whiteFuel);
		design.setTileWood(tileWood);
		design.setCompletion(completion);
		design.setDuration(duration);
		design.setCreateTime(StringAndDate.getTime());
		design.setRemark(remark);
		design.setStaffid(staffid);
		design.setAgreementId(agreementId);
		design.setStatus(new Byte("0"));
		//保存设计信息
		if(designs.size()>0){
			this.update(design);
		}else{
			this.save(design);
		}
		
		designId=design.getId();
		return designId;
	}


	private void saveFileInfo(List<Attachment> attachmentList, Integer designId)  throws Exception {
		Admin admin = new Admin();
		admin.setId(0);
		//先删除之前的记录 --修改为不删除以前的图片
		//attachmentService.deleteByDesignId(designId);
		if(attachmentList!=null){
			for(Attachment ac:attachmentList){
				//设计图类型
				attachmentService.saveAttachment(ac, admin, designId, ac.getFiletype());
			}
		}
		
	}


	private void saveCaseInfo(AppointmentPush appPush, Float space,
			Integer huxing, Integer fengge, Integer method, Float budget,
			Integer staffid, Integer designId, Integer spId)  throws Exception {
		Case cs = new Case();
		List<Case> cases=caseService.selectByProperty("designId", designId);
		if(cases!=null&&cases.size()>0){
			cs = cases.get(0);
		}
		cs.setSpId(spId);
		cs.setDesignId(designId);
		cs.setSjsId(staffid);
		cs.setCreateTime(StringAndDate.getTime());
		cs.setSpace(space);
		cs.setMethod(method);
		//cs.setZxFund(zxFund);
		//cs.setCaseSource(0);//手工录入
		cs.setAid(appPush.getAid());
		cs.setHouseType(huxing);
		cs.setStyle(fengge);
		cs.setBudget(budget);
		
		if(cases!=null&&cases.size()>0){
			caseService.update(cs);
		}else{
			caseService.save(cs);
		}
	}
}
