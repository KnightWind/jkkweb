package com.jkkp.modules.system.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.MenuMapper;
import com.jkkp.modules.system.model.Menu;
import com.jkkp.modules.system.service.IMenuService;
import com.jkkp.modules.system.view.VMenu;

@Service("menuService")
public class MenuServiceImpl extends
ServiceSupport<Menu,VMenu,Integer> implements IMenuService {
	@Autowired
	private MenuMapper menuMapper;
	@Override
	protected Mapper<Menu> getMapper() {	
		return menuMapper;
	}

}
