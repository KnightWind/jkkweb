package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.Staff;
import com.jkkp.appapi.modules.mapper.VDesignCaseSV;
import com.jkkp.appapi.modules.mapper.VDesing;
import com.jkkp.common.IService;
import com.jkkp.modules.basedata.view.VDesignerCollect;
import com.jkkp.modules.design.model.DesignerCollect;

public interface IDesigner  extends IService<DesignerCollect,VDesignerCollect,Integer>{
	public List<Staff> all(Integer spid);
	public long get(Integer spid);
	public long count(String name);
	public VDesing every(Integer spid, Integer sid, String name);
	public List<VDesignCaseSV> getCaseDesign(Map<String, Object> map);
}
