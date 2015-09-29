package com.jkkp.pc.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.pc.business.service.IBusinessService;
import com.jkkp.pc.business.view.VBusiness;
import com.jkkp.utils.CheckedUtil;

@Component("businessServicePC")
public class BusinessServiceImpl extends ServiceSupport<Supplier, VBusiness, Integer> implements IBusinessService {

	@Autowired
	private SupplierMapper supplierMapper;
	@Value("#{application['system.application.sitepath']}")
	private String sitePath;
	@Value("#{application['system.attachment.uploadpath']}")
	private String uploadPath;
	
	@Autowired
	private IAttachmentService attachmentService;
	
	public String getBasePath() {
		return sitePath + uploadPath;
		// return ProjectContext.getRealPath();
	}
	
	protected Mapper<Supplier> getMapper() {
		return supplierMapper;
	}

	@Transactional(readOnly=true)
	public List<VBusiness> selectBusinessList(Map<String, Object> map) {
		List<VBusiness> list = supplierMapper.selectBusinessList(map);
		for (VBusiness vb : list) {
			vb.setFilepath(this.getBasePath() + vb.getFilepath());
		}
		return list;
	}

	@Transactional(readOnly=true)
	public VBusiness selectBusiness(Integer id) {
		VBusiness vb = supplierMapper.selectBusiness(id);
		List<VAttachment> attachments = attachmentService.findAttachment(vb.getId(), AttachmentConstant.SUPPLIER_COMPANY_TYPE);
		for (VAttachment va : attachments) {
			if(CheckedUtil.isNotEmpty(va.getDownloadPath())){
				vb.setFilepath(va.getDownloadPath());
				break;
			}
		}
		return vb;
	}

	

}
