package com.jkkp.appapi.common.service.interfaces;

import com.jkkp.appapi.modules.mapper.VISgtopic;
import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Sgtopic;
import com.jkkp.modules.supplier.view.VSgtopic;


public interface ISgtopicSV  extends IService<Sgtopic,VSgtopic,Integer>{
	VISgtopic all(Integer spid,Integer sid,String name);
	VISgtopic query(Integer sid,Integer uid);
}
