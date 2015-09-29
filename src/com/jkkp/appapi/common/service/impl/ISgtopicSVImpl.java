package com.jkkp.appapi.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ISgtopicSV;
import com.jkkp.appapi.modules.mapper.VISgtopic;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SgtopicMapper;
import com.jkkp.modules.supplier.model.Sgtopic;
import com.jkkp.modules.supplier.view.VSgtopic;
@Service("iSgtopicSV")
public class ISgtopicSVImpl extends ServiceSupport<Sgtopic,VSgtopic,Integer> implements ISgtopicSV{
	@Autowired 
	SgtopicMapper sgtopicMapper;
	@Override
	protected Mapper<Sgtopic> getMapper() {
		return sgtopicMapper;
	}

	@Override
	public VISgtopic all(Integer spid, Integer sid, String name) {
		
		return sgtopicMapper.all(spid, sid, name);
	}

	@Override
	public VISgtopic query(Integer sid,Integer uid) {		
		return sgtopicMapper.query(sid, uid);
	}
}
