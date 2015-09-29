package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.view.VJtopic;
import com.jkkp.utils.Pager;

public interface JtopicMapper extends Mapper<Jtopic> {
	public List<VJtopic> findPageHandle(Map<String, Object> params);

	public long countPageHandle(Map<String, Object> params);

	public VJtopic findVJtopicById(@Param("id") Integer id);

	public List<VJtopic> selectAllBusinessTopic(Map<String, Object> params);

	public long selectBusinessTopicCount(Map<String, Object> params);

	public VJtopic businessDetail(@Param("id") int id);

	// ysc=========================================
	public List<VJtopic> getList(String and);
	public List<VJtopic> getPageList(Pager pager);
	public long getCount(String and);
	public VJtopic getBeanById(Integer id);
	public int deleteByAnd(String and);
	public List<VJtopic> getListBySpId(String spId);
	// ysc=========================================

	// ------------web------------
	public void noPass(@Param("id") int id);

	public void pass(@Param("id") int id);
	
	public void deleteOneTopic(@Param("id")int id);
	// ------------web------------

	public List<VJtopic> queryJtopicById(Map<String, Object> map);
	
}
