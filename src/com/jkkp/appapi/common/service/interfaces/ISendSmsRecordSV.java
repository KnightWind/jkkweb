package com.jkkp.appapi.common.service.interfaces;

import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.SendSmsRecord;
import com.jkkp.modules.system.view.VSendSmsRecord;

public interface ISendSmsRecordSV extends IService<SendSmsRecord, VSendSmsRecord, Integer>{

	SendSmsRecord queryByBillId(Map<String, Object> map);

}
