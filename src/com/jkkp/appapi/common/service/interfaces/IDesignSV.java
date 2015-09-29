package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VDesign3img;
import com.jkkp.common.IService;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.view.VDesign;

public interface IDesignSV extends IService<Design, VDesign, Integer>{
  List<VDesign> pagin(Map<String, Object> params);
  List<VDesign3img> qrydesigns(Map<String, Object> map);
}
