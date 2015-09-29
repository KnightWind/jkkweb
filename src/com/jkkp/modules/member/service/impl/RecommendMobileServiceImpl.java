package com.jkkp.modules.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.service.IRecommendMobileService;


@Service("recommendMobileService")
public class RecommendMobileServiceImpl implements IRecommendMobileService {
	@Autowired
	private IMemberService memberService;
	@Autowired
	private ISupplierCompanyStaffService supplierCompanyStaffService;
	/**
	 * 判断这个号码是否存在（是否推荐）
	 * 参数： name 手机号码
	 * 
	 */
	@Override
	public boolean isvalidate(String name) {
		 Member member1=new Member();
		 member1.setMobile(name);
		 List<Member> list=memberService.select(member1);
		 SupplierCompanyStaff staff=new SupplierCompanyStaff();
		 staff.setMobile(name);
		 List<SupplierCompanyStaff> liStaff=supplierCompanyStaffService.select(staff);
		 if(list.size()>0||liStaff.size()>0){
			 return true;
		 }
		return false;
	}

}
