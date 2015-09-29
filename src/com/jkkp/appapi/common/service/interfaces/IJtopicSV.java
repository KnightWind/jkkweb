package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.view.VJtopic;

public interface IJtopicSV extends IService<Jtopic, Jtopic, Integer>{

	List<VJtopic> queryJtopicById(Map<String, Object> map);

}
