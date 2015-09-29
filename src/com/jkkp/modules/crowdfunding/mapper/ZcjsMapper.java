package com.jkkp.modules.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.crowdfunding.model.Zcjs;
import com.jkkp.modules.crowdfunding.view.VZcJs;

public interface ZcjsMapper extends Mapper<Zcjs> {
    public Double selectJSTotal(@Param("spId")int spId);
    
    public List<VZcJs> selectAllZcjs(Map<String, Object> map);
    
    public Long selectAllZcjsCount(Map<String, Object> map);
}
