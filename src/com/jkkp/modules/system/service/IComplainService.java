package com.jkkp.modules.system.service;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.Complain;
import com.jkkp.modules.system.view.VComplain;

public interface IComplainService extends IService<Complain, VComplain, Integer> {
	public VComplain complaintDetail(int id);
	public void updateTime(int cid);
	/**
	 * 关闭投诉     把状态status改为1
	 * @param id  投诉id
	 */
	public void closeOneComplaint(int id);
}
