package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jkkp.appapi.modules.mapper.VIEngineerings;
import com.jkkp.appapi.modules.mapper.VIEngineeringsV1;
import com.jkkp.appapi.modules.mapper.VIEngineeringsV2;
import com.jkkp.appapi.modules.mapper.VIEngneerings;
import com.jkkp.appapi.modules.mapper.VIEngneeringsV1;
import com.jkkp.appapi.modules.mapper.VIPingJiaSV;
import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.view.VEngineerings;

public interface IEngineeringsService extends IService<Engineerings,VEngineerings,Integer> {
	List<VIEngneerings> qryEngineerDetialByid(Map<String, Object> map);

	List<VIEngneeringsV1> qrySuppEngBySpId(Map<String, Object> map);


	List<VIEngineerings> qryEngStartWorkByEngId(Map<String, Object> map);

	List<VIEngineeringsV1> qryEngOpinionByEngId(Map<String, Object> map);

	List<VIEngineeringsV2> qryEngCheckByEngId(Map<String, Object> map);
	public VIPingJiaSV getPingJia(Integer gcdid);
	public VIPingJiaSV getPingJiaJl(Integer gcdid);
	public VIPingJiaSV getPingJiaSjs(Integer gcdid);
	Engineerings addEngineerings(Integer AppPushId);//预约单id，预约推送id，装修款
	List<VEngineerings> qryEngByUid(Map<String, Object> map);

}
