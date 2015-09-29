package com.jkkp.appapi.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IAgreementImgService;
import com.jkkp.appapi.common.service.interfaces.IEngineeringsService;
import com.jkkp.appapi.modules.mapper.VIEngneerings;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.AdSearchMapper;
import com.jkkp.modules.basedata.mapper.EngineeringsMapper;
import com.jkkp.modules.basedata.model.AdSearch;
import com.jkkp.modules.basedata.model.AdSearchTag;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.service.IAdSearchService;
import com.jkkp.modules.basedata.view.VAdSearch;
import com.jkkp.modules.basedata.view.VAgreementImg;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.order.mapper.AgreementImgMapper;
import com.jkkp.modules.order.model.AgreementImg;
@Service("agreemenimgService")
public class AgreementImgServiceImpl  extends ServiceSupport<AgreementImg,VAgreementImg,Integer> implements IAgreementImgService {
	
	@Autowired
	private AgreementImgMapper OneMapper;
	@Override
	protected Mapper<AgreementImg> getMapper() {		
		return OneMapper;
	}
	public List<VAgreementImg> qryAgreeImgByAgreeid(Map<String, Object> map)
	{
		return OneMapper.qryAgreeImgByAgreeid(map);
	}
}
