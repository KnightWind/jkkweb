package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jkkp.appapi.modules.mapper.VAgreement;
import com.jkkp.appapi.modules.mapper.VAgreementDetail;
import com.jkkp.appapi.modules.mapper.VHeTong;
import com.jkkp.common.IService;
import com.jkkp.modules.order.model.Agreement;

public interface IAgreementSV extends IService<Agreement,VAgreement,Integer> {
	public VHeTong queryhetong(Integer id);
	
	
	public Map doTranscAddAgreement(HttpServletRequest request,
			Map<String, Object> map, Map<String, Object> mapBusi,
			List<Object> ret) throws Exception ;
	
	public VAgreementDetail detail(int id);
}
