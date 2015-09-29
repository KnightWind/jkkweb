package com.jkkp.appapi.common.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ISendSmsRecordSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.SendSmsRecordMapper;
import com.jkkp.modules.system.model.SendSmsRecord;
import com.jkkp.modules.system.view.VSendSmsRecord;

@Service("sendSmsRecordSV")
public class SendSmsRecordSVImpl extends ServiceSupport<SendSmsRecord, VSendSmsRecord, Integer> implements ISendSmsRecordSV{

	@Autowired 
	SendSmsRecordMapper sendSmsRecordmapper;
	@Override
	protected Mapper<SendSmsRecord> getMapper() {
		// TODO Auto-generated method stub
		return sendSmsRecordmapper;
	}
	@Override
	public SendSmsRecord queryByBillId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sendSmsRecordmapper.queryByBillId(map);
	}

}
