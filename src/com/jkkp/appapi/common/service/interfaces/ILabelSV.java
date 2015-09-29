package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.Label;
import com.jkkp.modules.system.view.VLabel;


public interface ILabelSV  extends IService<Label,VLabel,Integer>{
	 public List<Label> getByName();

	public List<Label> queryLabelById(Map<String, Object> map);

	public List<Label> queryAllLabelById(Map<String, Object> map);
}
