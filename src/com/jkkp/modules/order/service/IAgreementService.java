package com.jkkp.modules.order.service;

import com.jkkp.appapi.modules.mapper.VAgreement;
import com.jkkp.common.IService;
import com.jkkp.modules.order.model.Agreement;

public interface IAgreementService extends IService<Agreement, VAgreement, Integer> {

	Agreement selectByPushId(Integer id);

}
