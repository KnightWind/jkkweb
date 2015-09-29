package com.jkkp.appapi.common.service.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.jkkp.appapi.modules.mapper.VAcceptanceComment;
import com.jkkp.appapi.modules.mapper.VAcceptanceDetail;
import com.jkkp.appapi.modules.mapper.VAcceptanceItem;
import com.jkkp.appapi.modules.mapper.VAcceptionMainInfo;
import com.jkkp.appapi.modules.mapper.VJlGcd;
import com.jkkp.appapi.modules.mapper.VSupervisorReport;
import com.jkkp.modules.basedata.model.Attachment;

public interface IAcceptanceService {

	List<VAcceptanceDetail> findInitialList(Integer stageId);

	VAcceptionMainInfo findAcceptanceInfo(Integer engineerId, Integer instanceId);

	List<VAcceptanceDetail> findAcceptanceDetail(Integer instanceId);

	List<VSupervisorReport> queryList(Integer engineerId);

	void saveAcceptance(Integer projectId, Date accepanceDate, Integer supervisorId,
			Integer stageId, JSONArray data, Map<String, Attachment> attachment);

	void updateAcceptance(Integer instanceId, Date accepanceDate, List<VAcceptanceDetail> detailList,
			List<VAcceptanceItem> mxList, Map<String, Attachment> attachment);

	String findStageStandard(Integer mxId);

	List<VAcceptanceComment> findAcceptOpinion(Integer mxInstId);

	List<VAcceptanceComment> findOwnerOpinion(Integer instId);

	void saveOwnerOpinion(Integer instanceId, Integer memberId, String content, List<Attachment> attachmentList);

	VAcceptionMainInfo findAcceptanceInfo(Integer engineerId);

	VAcceptionMainInfo findAcceptance(Integer engineerId, Integer stageId);

	void updateReportPass(Integer instanceId, boolean isPass, boolean updateProject);

	VJlGcd querycount(Integer gcdId, Integer stageId);
	
	public Map<String, Object> updateReportBeforecheck(Integer instanceId, boolean isPass, boolean updateProject);
}
