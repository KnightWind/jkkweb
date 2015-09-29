package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VISgtopic;
import com.jkkp.modules.supplier.model.Sgtopic;
import com.jkkp.modules.supplier.view.VSgtopic;

public interface SgtopicMapper extends Mapper<Sgtopic> {
	public VISgtopic all(@Param("spid") Integer spid,
			@Param("sid") Integer sid, @Param("name") String name);

	public VISgtopic query(@Param("sid") Integer sid, @Param("uid") Integer uid);

	// ------------web-------------
	public List<VSgtopic> selectAllSgtopic(Map<String, Object> params);

	public long selectAllSgtopicCount(Map<String, Object> params);

	public VSgtopic sgtopicDetail(@Param("id") int id);

	public void noPass(@Param("id") int id);

	public void pass(@Param("id") int id);
	// --------------web-----------
}
