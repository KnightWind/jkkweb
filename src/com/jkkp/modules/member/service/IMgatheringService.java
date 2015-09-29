package com.jkkp.modules.member.service;

import com.jkkp.common.IService;
import com.jkkp.modules.member.model.Mgathering;
import com.jkkp.modules.member.view.VMgathering;
import com.jkkp.modules.order.model.PaymentRecord;

public interface IMgatheringService extends IService<Mgathering, VMgathering, Integer> {

	void saveRecord(PaymentRecord record);

}
