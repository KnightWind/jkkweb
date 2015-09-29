package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VIAppointmentMember;
import com.jkkp.appapi.modules.mapper.VIEngneerings;
import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.AdSearch;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.view.VAdSearch;
import com.jkkp.modules.basedata.view.VAgreementImg;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.modules.order.model.AgreementImg;

public interface IAgreementImgService extends IService<AgreementImg,VAgreementImg,Integer> {
	List<VAgreementImg> qryAgreeImgByAgreeid(Map<String, Object> map);//通过合同单号查询图片
}
