package com.jkkp.modules.supplier.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.modules.supplier.view.SupplierCompanyStaffInfo;
import com.jkkp.modules.supplier.view.VSupplierCompanyStaff;

@Service("supplierCompanyStaffService")
public class SupplierCompanyStaffServiceImpl extends ServiceSupport<SupplierCompanyStaff,VSupplierCompanyStaff,Integer> implements ISupplierCompanyStaffService {
	
	@Autowired
	private SupplierCompanyStaffMapper supplierCompanyStaffMapper;
	@Autowired
	private IAttachmentService 		   attachmentService;
	
	@Override
	protected Mapper<SupplierCompanyStaff> getMapper() {
		return supplierCompanyStaffMapper;
	}
	public List<SupplierCompanyStaffInfo> findSaffListBySpId(Map<String,Object> params) {
		return supplierCompanyStaffMapper.findSaffListBySpId(params);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUpdate(Integer id, String[] tra, String[] name,String [] job) {
		for (int i = 0; i < job.length; i++) {
			SupplierCompanyStaff supplierCompanyStaff=new SupplierCompanyStaff();
			supplierCompanyStaff.setCreateTime(new Date());
			supplierCompanyStaff.setSpId(id);
			supplierCompanyStaff.setAvatar(tra[i]);
			supplierCompanyStaff.setName(name[i]);
			supplierCompanyStaff.setJob(job[i]);
			this.save(supplierCompanyStaff);
		}
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void del(Integer id) {
		supplierCompanyStaffMapper.del(id);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteStaffById(Integer id, List<Attachment> attList) {
		
		this.deleteById(id);
		if(attList != null){
			for (Attachment attachment : attList) {
				attachmentService.delete(attachment);
				HttpFileTools.deleteFile(attachment.getFilepath());
			}
		}
		
	}
	@Override
	public List<SupplierCompanyStaff> getAllSupplierStaff(Integer spId) {
		return this.selectByProperty("spId", spId);
	}
	
	
	@Transactional
	public void saveOrUpdate(SupplierCompanyStaff bean) {
		if(bean.getId()!=null){
		    this.updateByPrimaryKeySelective(bean);
		}else{
			bean.setCreateTime(new Date());
			bean.setTotalSttleMoney(Float.valueOf("0"));
			this.save(bean);
		}
	}
	@Override
	public List<VSupplierCompanyStaff> selectJCSupplierStaff(Integer spId,Integer status) {
		return supplierCompanyStaffMapper.selectJCSupplierStaff(spId,status);
	}
	@Override
	public void updateSupplierStaffStatus(int staffId,Float gainRate,Integer status) {
		supplierCompanyStaffMapper.updateSupplierStaffStatus(staffId,gainRate,status);		
	}
	
}
