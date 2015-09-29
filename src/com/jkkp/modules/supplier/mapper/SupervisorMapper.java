package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.Supervisor;
import com.jkkp.modules.supplier.view.VSupervisor;
import com.jkkp.modules.supplier.view.VSupervisorWeb;
import com.jkkp.utils.Pager;

public interface SupervisorMapper extends Mapper<Supervisor> {

	// ysc=========================================
	public List<VSupervisor> getList(String and);

	public List<VSupervisor> getPageList(Pager pager);

	public long getCount(String and);

	public VSupervisor getBeanById(Integer id);

	public int deleteByAnd(String and);

	// ysc=========================================

	// -----------web------------------
	public List<VSupervisorWeb> selectAllSupervisors(Map<String, Object> params);

	public long selectAllSupervisorsCount(Map<String, Object> params);

	public VSupervisorWeb selectSupervisorDetail(@Param("id") int id);
	// ----------web----------------
}
