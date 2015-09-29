package com.jkkp.appapi.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.ISuppComStaffSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;

@Service("baseinfapi")
public class IBaseinfImpl implements IBaseinf{
/*
 * (non-Javadoc)
 * @see com.jkkp.appapi.common.service.interfaces.IBaseinf#getHeadimg(int, int)
 * type 类型         表
 参考 AttachmentConstant
 */
	@Autowired ISupplierSV supp;
	@Autowired IMemberService mem;
	@Autowired ISuppComStaffSV suppstaff;
	@Autowired private IAttachmentService attachmentService;
	@Override
	public String getHeadimg(int id, int type) {
		// TODO Auto-generated method stub
		List<String> img=attachmentService.findForDownload(id, type);//商家名字
		if(img.size()>0){
			return img.get(0);
		}
		else {
			return "";			
		}
	}

	@Override
	public String getName(int id, int type) {
		// TODO Auto-generated method stub
		if(type==AttachmentConstant.SUPPLIER_COMPANY_TYPE){
			Supplier s=supp.findById(id);
			if(s!=null)
			return s.getSpName();
		}
		if(type==AttachmentConstant.MEMBER_TYPE){
			Member s=mem.findById(id);
			if(s!=null)
			return s.getReallName();
		}
		if(type==AttachmentConstant.SUPPLIER_STAFF_TYPE){
			SupplierCompanyStaff s=suppstaff.findById(id);
			if(s!=null)
			return s.getName();
		}
		return "";
	}

}
