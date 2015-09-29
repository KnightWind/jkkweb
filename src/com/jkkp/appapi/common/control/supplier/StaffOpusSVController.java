package com.jkkp.appapi.common.control.supplier;
import java.util.*;

import javax.servlet.http.*;

import net.sf.json.*;
import org.apache.commons.lang.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.appapi.common.jsonmap.*;
import com.jkkp.appapi.common.service.interfaces.IJtopicSV;
import com.jkkp.appapi.common.service.interfaces.ITopicCommentSV;
import com.jkkp.appapi.common.service.interfaces.ITopicLabelSV;
import com.jkkp.common.*;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.TopicComment;
import com.jkkp.modules.member.model.TopicLabel;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.supplier.mapper.*;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.model.JtopicReply;
import com.jkkp.modules.supplier.model.StaffOpus;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.service.IJtopicReplyService;
import com.jkkp.modules.supplier.service.IStaffOpusService;
import com.jkkp.modules.supplier.view.*;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.utils.*;
@Controller
public class StaffOpusSVController extends BaseController{
	@Autowired public CommonJsonMap commonJsonMap;
    @Autowired public IStaffOpusService iStaffOpusService ;
    @Autowired
	private IAttachmentService attachmentService;
	@ResponseBody
	@RequestMapping("/staff_opus_add.do")
	public Object addCasesTopicList(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer spid=CommonUtil.stringToInteger((String) map.get("spid"));
			Integer staffid=CommonUtil.stringToInteger((String) map.get("staffid"));
			String content=null;
			if(map.containsKey("title"))
			content=(String)map.get("title");
			StaffOpus staffOpus=new StaffOpus();
			staffOpus.setTitle(content);
			staffOpus.setSpId(spid);
			staffOpus.setStaffId(staffid);
			List<Attachment> attachment = attachmentService.uploadMulti((MultipartRequest) request);		 
			if(attachment==null){
				mapBusi.put("doneCode", "9999");
				mapBusi.put("mess","保存文件失败");
				return -1;
			}
			iStaffOpusService.save(staffOpus);
			Admin admin = new Admin();
			attachmentService.saveAttachment(attachment, admin, staffOpus.getId(), AttachmentConstant.STAFF_OPUS_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(null,mapBusi);
		}
	}
}
