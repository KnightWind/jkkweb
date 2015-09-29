package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IDesignCollectSV;
import com.jkkp.appapi.common.service.interfaces.IDesigner;
import com.jkkp.appapi.modules.mapper.Staff;
import com.jkkp.appapi.modules.mapper.VDesignCaseSV;
import com.jkkp.appapi.modules.mapper.VDesignCollect;
import com.jkkp.appapi.modules.mapper.VDesing;
import com.jkkp.appapi.modules.mapper.VIDesign;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.view.VDesignerCollect;
import com.jkkp.modules.design.mapper.DesignCollectMapper;
import com.jkkp.modules.design.mapper.DesignImageMapper;
import com.jkkp.modules.design.mapper.DesignMapper;
import com.jkkp.modules.design.mapper.DesignerCollectMapper;
import com.jkkp.modules.design.model.DesignCollect;
import com.jkkp.modules.design.model.DesignerCollect;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
@Service("designcollectapi")
public class DesignCollectSVImpl extends ServiceSupport<DesignCollect,VDesignCollect,Integer> implements IDesignCollectSV{

	@Autowired
	DesignImageMapper designImageMapper;
	@Autowired 
	DesignCollectMapper dcMapper;
	@Override
	protected Mapper<DesignCollect> getMapper() {
		// TODO Auto-generated method stub
		return dcMapper;
	}
	public List<VIDesign>designcollectlist(Map<String, Object> map){
		return designImageMapper.queryDesignDetailByDesignerId(map);
	}
	@Override
	public List<DesignCollect> queryDesignByUidSpid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dcMapper.queryDesignByUidSpid(map);
	}
}
