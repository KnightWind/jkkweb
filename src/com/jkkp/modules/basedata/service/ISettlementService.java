package com.jkkp.modules.basedata.service;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Settlement;
import com.jkkp.modules.basedata.view.VSettlement;

public interface ISettlementService extends IService<Settlement, VSettlement, Integer> {

	Double findUnCalePushMoney(String rphone);

}
