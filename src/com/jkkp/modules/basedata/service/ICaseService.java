package com.jkkp.modules.basedata.service;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Case;
import com.jkkp.modules.basedata.view.VCase;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.supplier.view.VSupplierUser;

public interface ICaseService extends IService<Case, VCase, Integer> {
	
	public void save(VSupplierUser su, Integer[] imgId, Design design,
			Case cases, HttpServletRequest request);

}
