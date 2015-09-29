package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.AdminMenu;
import com.jkkp.modules.system.view.VAdminMenu;

public interface IMenuSV extends IService<AdminMenu, VAdminMenu, Integer>{

	List<VAdminMenu> qryMenuByUser(Map<String, Object> map);

}
