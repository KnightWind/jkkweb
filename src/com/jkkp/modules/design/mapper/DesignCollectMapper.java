package com.jkkp.modules.design.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.design.model.DesignCollect;
import com.jkkp.modules.design.model.DesignerCollect;
import com.jkkp.modules.supplier.model.SupplierCollect;

public interface DesignCollectMapper extends Mapper<DesignCollect> {
	List<DesignCollect> queryDesignByUidSpid(Map<String, Object> map);
}