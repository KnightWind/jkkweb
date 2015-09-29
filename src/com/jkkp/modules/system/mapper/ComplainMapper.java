package com.jkkp.modules.system.mapper;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.system.model.Complain;
import com.jkkp.modules.system.view.VComplain;
import com.jkkp.utils.Pager;

public interface ComplainMapper extends Mapper<Complain> {
	public List<VComplain> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);
	
	public VComplain complaintDetail(@Param("id")int id);
	
	public void closeOneComplaint(@Param("id")int id);
	
	//ysc=========================================
	public List<VComplain> getList(String and);
	public List<VComplain> getPageList(Pager pager);
	public long getCount(String and);
	public VComplain getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
}