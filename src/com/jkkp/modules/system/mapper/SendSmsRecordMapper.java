package com.jkkp.modules.system.mapper;

import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.system.model.SendSmsRecord;

public interface SendSmsRecordMapper extends Mapper<SendSmsRecord> {

	SendSmsRecord queryByBillId(Map<String, Object> map);

}
