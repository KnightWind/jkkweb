package com.jkkp.modules.design.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VJlGcd;
import com.jkkp.modules.design.model.AdBanner;

public interface AdBannerMapper extends Mapper<AdBanner> {
	List<AdBanner> index(@Param("name") String name);
	List<AdBanner> news(@Param("name") String name);
	List<AdBanner> quan(@Param("name") String name);
	List<AdBanner> design(@Param("name") String name);
	VJlGcd querycount(@Param("gcdId") Integer gcdId,@Param("stageId") Integer stageId );
}