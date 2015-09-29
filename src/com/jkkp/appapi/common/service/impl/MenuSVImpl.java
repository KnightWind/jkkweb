package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IMenuSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.AdminMenuMapper;
import com.jkkp.modules.system.model.AdminMenu;
import com.jkkp.modules.system.view.VAdminMenu;

@Service("menuSV")

public class MenuSVImpl extends ServiceSupport<AdminMenu, VAdminMenu, Integer> implements IMenuSV{



	@Autowired
	AdminMenuMapper adminMenuMapper;
	
	protected Mapper<AdminMenu> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VAdminMenu> qryMenuByUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return adminMenuMapper.qryMenuByUser(map);
	}

}
