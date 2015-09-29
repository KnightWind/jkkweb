package com.jkkp.modules.sale_theme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.sale_theme.mapper.ActivityVoucherNumMapper;
import com.jkkp.modules.sale_theme.model.ActivityCompany;
import com.jkkp.modules.sale_theme.model.ActivityVoucherNum;
import com.jkkp.modules.sale_theme.service.IActivityCompanyService;
import com.jkkp.modules.sale_theme.service.IActivityVoucherNumService;
import com.jkkp.modules.sale_theme.view.VActivityCompany;
import com.jkkp.modules.sale_theme.mapper.ActivityCompanyMapper;

@Service("ActivityCompanyService")
public class ActivityCompanyServiceImpl extends
		ServiceSupport<ActivityCompany, VActivityCompany, Integer>
		implements IActivityCompanyService {

	@Autowired
	private ActivityCompanyMapper ActivityCompanyMapper;

	@Override
	protected Mapper<ActivityCompany> getMapper() {
		return ActivityCompanyMapper;
	}

}
