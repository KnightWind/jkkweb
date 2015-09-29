package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.supplier.model.JlComplain;
import com.jkkp.modules.supplier.view.VJlComplain;
import com.jkkp.utils.Pager;

public interface JlComplainMapper extends Mapper<JlComplain> {
	public List<VJlComplain> selectAllAboutJLComplain(Map<String, Object> params);

	public long selectAllAboutJLCCount(Map<String, Object> params);

	public void close(@Param("id") int id);

	public VJlComplain complainDetail(@Param("id") int id);
	

	//ysc=========================================
	public List<VJlComplain> getList(String and);
	public List<VJlComplain> getPageList(Pager pager);
	public long getCount(String and);
	public VJlComplain getBeanById(Integer id);
	public int deleteByAnd(String and);
	public List<VJlComplain> getListByGcdId(Integer gcdId);
	//ysc=========================================
}
