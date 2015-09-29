package com.jkkp.modules.system.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.ComplainDetails;
import com.jkkp.modules.system.view.VComplainDetails;

public interface IComplainDetailsService extends IService<ComplainDetails, VComplainDetails, Integer> {
	public List<ComplainDetails> complaintDetailList(int cid);
	public void saveOrUpdate(ComplainDetails com);
}
