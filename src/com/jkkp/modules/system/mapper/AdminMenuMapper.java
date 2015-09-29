package com.jkkp.modules.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.system.model.AdminMenu;
import com.jkkp.modules.system.view.VAdminMenu;

public interface AdminMenuMapper extends Mapper<AdminMenu> {

	public List<AdminMenu> findByPid(@Param("parentId") Integer parentId);

	public int findMaxOrderby(@Param("parentId") Integer parentId);

	public List<AdminMenu> findByAdmin(@Param("username") String username, @Param("parentId") int parentId);

	public List<AdminMenu> findBannerByAdmin(@Param("username") String username);

	public List<VAdminMenu> qryMenuByUser(Map<String, Object> map);

}
