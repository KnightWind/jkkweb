package com.jkkp.modules.order.mapper;


import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;

import com.jkkp.modules.basedata.view.VAgreementImg;
import com.jkkp.modules.order.model.AgreementImg;

public interface AgreementImgMapper extends Mapper<AgreementImg> {

	List<VAgreementImg> qryAgreeImgByAgreeid(Map<String, Object> map);
}