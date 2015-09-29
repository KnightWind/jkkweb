package com.jkkp.modules.member.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.MgatheringMapper;
import com.jkkp.modules.member.model.Mgathering;
import com.jkkp.modules.member.service.IMgatheringService;
import com.jkkp.modules.member.view.VMgathering;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;

@Service("mgatheringService")
public class MgatheringServiceImpl extends ServiceSupport<Mgathering, VMgathering, Integer> implements
		IMgatheringService {

	@Autowired
	private MgatheringMapper mgatheringMapper;
	@Autowired
	private IPaymentRecordService paymentRecordService;

	@Override
	protected Mapper<Mgathering> getMapper() {
		return mgatheringMapper;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveRecord(PaymentRecord record) {
		if (record == null) {
			return;
		}
		Mgathering entity = new Mgathering();
		entity.setAmount(record.getAmount());
		entity.setGname(record.getTitle());
		entity.setMethod(Mgathering.METHOD_PAY);
		entity.setSkTime(new Date());
		// entity.setOperator(operator); TODO
		// entity.setSkUser(skUser);
		// entity.setSkUName(skUName);
		entity.setType(record.getType() == PaymentRecord.TYPE_DEPOSIT ? Mgathering.TYPE_DEPOSIT
				: (record.getType() == PaymentRecord.TYPE_WALLET ? Mgathering.TYPE_WALLET : Mgathering.TYPE_PROJECT));
	}

}
