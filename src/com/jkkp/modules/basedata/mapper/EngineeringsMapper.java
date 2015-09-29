package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.*;
import com.jkkp.modules.basedata.model.*;
import com.jkkp.utils.Pager;
import com.jkkp.modules.basedata.view.*;

public interface EngineeringsMapper extends Mapper<Engineerings> {
	public List<VIEngneerings> qryEngineerDetialByid(Map<String, Object> map);

	public List<VIEngneeringsV1> qrySuppEngBySpId(Map<String, Object> map);

	public List<VIEngineerings> qryEngStartWorkByEngId(Map<String, Object> map);

	public List<VIEngineeringsV1> qryEngOpinionByEngId(Map<String, Object> map);

	// web后台
	public List<VEngineerings> selectAllEngineerings(Map<String, Object> param);

	public VEngineerings engineeringDetail(@Param("id") int id);

	public void updatePoint(@Param("pointx") String pointx,
			@Param("pointy") String pointy, @Param("id") int id);

	// -web后台

	public long selectEngineeringsCount(Map<String, Object> param);

	// ysc=========================================
	public List<VEngineerings> getList(String and);

	public List<VEngineerings> getPageList(Pager pager);

	public long getCount(String and);

	public VEngineerings getBeanById(Integer id);

	public int deleteByAnd(String and);
	public VIPingJiaSV getPingJiaJl(@Param("gcdid") Integer gcdid);
    public VIPingJiaSV getPingJia(@Param("gcdid") Integer gcdid);
    public VIPingJiaSV getPingJiaSjs(@Param("gcdid") Integer gcdid);
	// ysc==========================================
	List<Engineerings> qryEngineerDetialByAid(@Param("aid") Integer aid);

	public List<VIEngineeringsV2> qryEngCheckByEngId(Map<String, Object> map);

	public List<VEngineerings> qryEngByUid(Map<String, Object> map);
	
	
	public List queryEngineerings(Map<String, Object> map);
	
	public void updateEngineeringsStatus(Map<String, Object> map);
}