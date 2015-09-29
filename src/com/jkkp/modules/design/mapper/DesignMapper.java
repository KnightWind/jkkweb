package com.jkkp.modules.design.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VDesign3img;
import com.jkkp.appapi.modules.mapper.VDesignCaseSV;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.view.VDesign;
import com.jkkp.pc.decorate.view.VDecorate;
import com.jkkp.utils.Pager;

public interface DesignMapper extends Mapper<Design> {
	List<VDesign> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	public List<Design> fin(@Param("id") Integer id);

	public long get(@Param("name") String name);

	List<VDesign> pagin(Map<String, Object> params);

	public List<Design> findDesignBySpId(Map<String, Object> params);

	public long findDesignBySpIdCount(Map<String, Object> params);
    public List<VDesignCaseSV> getCaseDesign(Map<String, Object> map);
	// web 工程单-装修方案
	public VDesign engineeringDesignDetail(@Param("id") int id);

	List<VDesign3img> qrydesigns(Map<String, Object> map);

	

	//ysc=========================================
	public List<VDesign> getList(String and);
	public List<VDesign> getPageList(Pager pager);
	public long getCount(String and);
	public VDesign getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
	
	//pc web say
	List<VDecorate> queryDecorateByPBL(Map<String, Object> paramMap);
	VDecorate queryDecorateById(Integer id);
	long queryDecorateByPBLCount(Map<String, Object> paramMap);
	
	
}