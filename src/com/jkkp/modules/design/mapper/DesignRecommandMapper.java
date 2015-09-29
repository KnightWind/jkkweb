package com.jkkp.modules.design.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.design.model.DesignRecommand;
import com.jkkp.modules.design.view.VDesignRecommand;

public interface DesignRecommandMapper extends Mapper<DesignRecommand> {
	List<VDesignRecommand> fin(@Param("name") String name);
}