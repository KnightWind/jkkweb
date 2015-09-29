package com.jkkp.modules.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.JlService;

public interface JlServiceMapper extends Mapper<JlService> {
      public void updateInfo(@Param("id")Integer id);
}
