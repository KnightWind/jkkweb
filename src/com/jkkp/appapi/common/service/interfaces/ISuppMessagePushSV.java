package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VISuppMessagePush;
import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SuppMessagePush;

public interface ISuppMessagePushSV extends IService<SuppMessagePush, VISuppMessagePush, Integer>{

	void dealPushLoad(Map<String, Object> map);
	void dealPushLoadMember(Map<String, Object> map);
	List<SuppMessagePush> selectByMobile(String mobile);
	List<SuppMessagePush> selectByUserIdAndType(int userId,int type);
	public void deleteByCid(String cid);
}
