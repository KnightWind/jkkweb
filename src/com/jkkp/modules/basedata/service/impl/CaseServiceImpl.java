package com.jkkp.modules.basedata.service.impl;

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
import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.mapper.CaseMapper;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.model.Case;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.service.ICaseService;
import com.jkkp.modules.basedata.view.VCase;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.model.DesignImage;
import com.jkkp.modules.design.service.IDesignImageService;
import com.jkkp.modules.design.service.IDesignService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.FileUtil;
import com.jkkp.utils.ProjectContext;

@Service("caseService")
public class CaseServiceImpl extends ServiceSupport<Case, VCase, Integer> implements ICaseService {

	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IDesignService designService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private ISupplierCompanyStaffService supplierCompanyStaffService;
	@Autowired
	private IDesignImageService designImageService;
	@Value("#{application['system.application.sitepath']}")
	private String sitePath;
	@Value("#{application['system.attachment.uploadpath']}")
	private String uploadPath;
	
	@Override
	protected Mapper<Case> getMapper() {
		return caseMapper;
	}

	public String getBasePath() {
		return sitePath + uploadPath;
		// return ProjectContext.getRealPath();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(VSupplierUser su ,Integer []imgId,Design design, Case cases,HttpServletRequest request) {
		
		Supplier supplier = supplierService.findById(su.getSpId());
		SupplierCompanyStaff staff = supplierCompanyStaffService.findById(design.getStaffid());
		Integer designId;
		if(design.getId() != null && design.getId() > 0){
			Design odlDesign=designService.findById(design.getId());
			designId = odlDesign.getId();
			odlDesign.setDesignName(design.getDesignName());
			if(staff != null)
				odlDesign.setDesigner(staff.getName());
			odlDesign.setForman(design.getForman());
			odlDesign.setCompany(design.getCompany());
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
			designService.update(odlDesign);
			
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
				//根据pid删除关联表记录
				if(ids.size() > 0)
					designImageService.delete(ids);
			}
			
		}else{
			if(staff != null)
				design.setDesigner(staff.getName());
			design.setSpId(su.getSpId());
			design.setStatus(new Byte("0"));
			design.setIsLock(new Byte("0"));
			design.setUid(su.getId());
			design.setBidding(0);
			design.setCityDomain(supplier.getCityDomain());
			design.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
			designService.save(design);
			designId = design.getId();
			
			cases.setSpId(supplier.getId());
			cases.setDesignId(designId);
			cases.setSpace(design.getSpace());
			cases.setMethod(design.getMethod());
			cases.setCaseSource(0);
			cases.setBudget(design.getBudget());
			cases.setHouseType(design.getHuxing());
			cases.setStyle(design.getFengge());
			cases.setCommentCount(0);
			cases.setBrowseCount(0);
			cases.setSpace(design.getSpace());
			cases.setRemark(design.getRemark());
			cases.setSjsId(design.getStaffid());
			cases.setCreateTime(new Date());
			
			this.save(cases);
				
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
					if(fileName.contains("des"))
						fileType = AttachmentConstant.DESIGN_TYPE;
					else
						fileType = AttachmentConstant.LOCALE_TYPE;
					attachmentService.saveAttachment(attachment, admin, designId, fileType);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("文件上传出错！");
		}
		
		
	}

	
	

}
