package com.jkkp.modules.supplier.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.service.ISupplierService;
import com.jkkp.modules.supplier.view.VSupplier;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.BaseTools;

@Service("supplierService")
public class SupplierServiceImpl extends ServiceSupport<Supplier, VSupplier, Integer> implements ISupplierService {

	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private IAttachmentService attachmentService;

	@Override
	protected Mapper<Supplier> getMapper() {
		return supplierMapper;
	}

	// web后台
	// 新增一商家
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Supplier supplier) {
		if (supplier.getId() != null && supplier.getId() > 0) {
			Supplier bean = this.findById(supplier.getId());
			if (supplier.getRegionId() != null) {
				bean.setRegionId(supplier.getRegionId());
			}
			bean.setSpName(supplier.getSpName());
			bean.setContactUser(supplier.getContactUser());
			bean.setLegalPerson(supplier.getLegalPerson());
			bean.setContactMobile(supplier.getContactMobile());
			bean.setGainRate(supplier.getGainRate());
			bean.setAddress(supplier.getAddress());
			bean.setType(supplier.getType());
			bean.setProxyStatus(0);
			bean.setLevelId(supplier.getLevelId());
			bean.setBindMobile(supplier.getBindMobile());
			bean.setAbbreviation(supplier.getAbbreviation());
			this.update(bean);
		} else {
			supplier.setProxyStatus(0);
			supplier.setCreateTime(new Date());
			supplier.setStatus(0);
			supplier.setEstimateDesc(BigDecimal.valueOf(0.0));
			supplier.setEstimateService(BigDecimal.valueOf(0.0));
			supplier.setEstimateEfficiency(BigDecimal.valueOf(0.0));
			supplier.setEstimateAverage(BigDecimal.valueOf(0.0));
			this.save(supplier);
		}
	}

	@Override
	public Supplier supplierDetail(int id) {
		return this.findById(id);
	}

	@Override
	public VSupplier login(String name, String pass) {
		return supplierMapper.login(name, pass);
	}

	public String getAndByRequest(HttpServletRequest request) {
		String and = "";
		and += BaseTools.getAndByPname(request, "spName", "sp_name", "2");
		and += BaseTools.getAndByPname(request, "id", "id", "2", "1");
		and += BaseTools.getAndByPname(request, "status", "status", "2", "1");
		and += BaseTools.getAndByPname(request, "type", "type", "2", "1");
		System.out.println(getClass() + ".getAndByRequest.and=\n" + and);
		return and;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Supplier supervisorLogin(String name, String pwd) {
		return supplierMapper.supervisorLogin(name, pwd);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateSupervisor(Supplier supplier) {
		Supplier old = this.findById(supplier.getId());
		old.setProxyStatus(0);
		old.setAddress(supplier.getAddress());
		old.setContactUser(supplier.getContactUser());
		old.setContactMobile(supplier.getContactMobile());
		old.setSubPhone(supplier.getSubPhone());
		old.setDomain(supplier.getDomain());
		old.setLegalPerson(supplier.getLegalPerson());
		old.setBindMobile(supplier.getBindMobile());
		old.setBusinessCode(supplier.getBusinessCode());
		this.update(old);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(Supplier supplier, Integer cid,String remark, HttpServletRequest request) {
		if (supplier != null) {
			int type = 0;
			if (cid != null) {
				type = selectType(cid);
			}
			if (type > 0) {
				Attachment attachment = attachmentService.uploadOne((MultipartRequest) request);
				if (attachment != null) {
					int mainid = supplier.getId();
					Admin admin = new Admin();
					attachmentService.saveAttachment(attachment, admin, mainid, type,remark);
				}
			}
			this.update(supplier);
		}
	}

	@Override
	public String findSupplierName(Integer jlId) {
		Supplier supplier = this.findById(jlId);
		return supplier != null ? supplier.getSpName() : null;
	}

	@Transactional
	public void updatebusinessCodeAndIdCard(String businessCode, String idCard,Integer spId) {
		//更新商户信息
		supplierMapper.updatebusinessCodeAndIdCard(businessCode, idCard, spId);
	}

	@Transactional
	public void deleteSupplierInfosBySpid(Integer spId) {
		supplierMapper.deleteSupplierBySpid(spId);
		supplierMapper.deleteSupplierUserBySpid(spId);
		supplierMapper.deleteSupplierCompanyStaff(spId);
	}
	
	
	public int selectType(int cid){
		switch (cid) {
		case 1:
			return AttachmentConstant.DECORATION_BUSINESS_CERTIFICATE;
		case 2:
			return AttachmentConstant.BANK_OPEN_LICENCE;
		case 3:
			return AttachmentConstant.TRADEMARK_REGINTER_COPIES;
		case 4:
			return AttachmentConstant.AUTHORIZATION_CERTIFICATE;
		case 5:
			return AttachmentConstant.TAXATION_REGINTER_CERTIFICATE;
		case 6:
			return AttachmentConstant.BUSINESS_LICENSE_COPIES;
		case 7:
			return AttachmentConstant.ORGANIZATION_CODE_CERTIFICATE;
		case 8:
			return AttachmentConstant.SUPPLIER_LIVE_CERTIFICATE_TYPE;
		default:
			return 0;
		}
	}

	@Override
	public void proxyOneSupplier(Integer level, Integer proxyStatus,Integer spId,Float gainRate) {
		supplierMapper.proxyOneSupplier(level, proxyStatus, spId,gainRate);
	}
	

}
