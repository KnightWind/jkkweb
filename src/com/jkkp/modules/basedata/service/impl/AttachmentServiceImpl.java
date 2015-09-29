package com.jkkp.modules.basedata.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.utils.HttpFileTools;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.basedata.mapper.AttachmentMapper;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.FileUtil;
import com.jkkp.utils.ProjectContext;

@Service("attachmentService")
public class AttachmentServiceImpl extends ServiceSupport<Attachment, VAttachment, Integer> implements
		IAttachmentService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Value("#{application['system.application.sitepath']}")
	private String sitePath;
	
	@Value("#{application['system.attachment.uploadpath']}")
	private String uploadPath;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Override
	protected Mapper<Attachment> getMapper() {
		return attachmentMapper;
	}

	public String getBasePath() {
		return sitePath + uploadPath;
		// return ProjectContext.getRealPath();
	}
	
	public String getAccessPath() {
		return ProjectContext.PROJECT_SITE_PATH + uploadPath;
	}

	/**
	 * 上传单个文件
	 * 
	 * @param request
	 * @param admin
	 * @return
	 */
	public Attachment uploadOne(MultipartRequest request) {
		try {
			MultipartFile file = getSingleFile(request);
			if (file == null || !StringUtils.isNotEmpty(file.getOriginalFilename())) {
				return null;
			}
			return this.upload(file.getOriginalFilename(), file.getInputStream());
		} catch (Exception e) {
			logger.error("上传文件出现异常", e);
			return null;
		}
	}

	private MultipartFile getSingleFile(MultipartRequest request) {
		Map<String, MultipartFile> map = request.getFileMap();
		if (map.isEmpty()) {
			return null;
		}
		String fileName = map.keySet().iterator().next();
		MultipartFile file = map.get(fileName);
		return file;
	}

	private Attachment upload(String fileName, InputStream stream) {
		try {
			String filePath = AttachmentConstant.FILE_PATH + FileUtil.createPath(null);
			String realPath = this.getBasePath() + filePath;
			FileUtil.createFolder(realPath);
			String newFileName = FileUtil.newFileName(fileName);
			FileUtil.copyFile(stream, realPath + "/" + newFileName);

			Attachment attachment = new Attachment();
			attachment.setFilename(fileName);
			attachment.setFilepath(filePath + "/" + newFileName);
			return attachment;
		} catch (Exception e) {
			logger.error("上传文件出现异常", e);
			return null;
		}
	}

	/**
	 * 上传多个文件
	 * 
	 * @param request
	 * @return
	 */
	public List<Attachment> uploadMulti(MultipartRequest request) {
		try {
			Map<String, MultipartFile> map = request.getFileMap();
			List<Attachment> attachments = new ArrayList<Attachment>();
			for (String fileName : map.keySet()) {
				MultipartFile file = map.get(fileName);
				logger.debug("上传文件名filename："+file.getName()+"|oriname:"+file.getOriginalFilename());
				if (!StringUtils.isNotEmpty(file.getOriginalFilename())) {
					continue;
				}
				//add 区分图片类别
				Attachment atchment = this.upload(file.getOriginalFilename(), file.getInputStream());
				//现场图
				if(file.getName()!=null&&file.getName().startsWith("LOCALE_TYPE")){
					atchment.setFiletype(AttachmentConstant.LOCALE_TYPE);
			    //设计图
				}else if(file.getName()!=null&&file.getName().startsWith("DESIGN_TYPE")){
					atchment.setFiletype(AttachmentConstant.DESIGN_TYPE);
				}
				//end 
				attachments.add(atchment);
			}
			return attachments;
		} catch (Exception e) {
			logger.error("上传文件出现异常", e);
			return null;
		}
	}
	
	/**
	 * 上传多个文件
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Attachment> uploadMapMulti(MultipartRequest request) {
		Map<String, Attachment> data = new HashMap<String, Attachment>();
		try {
			Map<String, MultipartFile> map = request.getFileMap();
			for (String fileName : map.keySet()) {
				MultipartFile file = map.get(fileName);
				logger.debug("上传文件名filename：" + file.getName() + "|oriname:"
						+ file.getOriginalFilename());
				if (!StringUtils.isNotEmpty(file.getOriginalFilename())) {
					continue;
				}
				data.put(
						fileName,
						this.upload(file.getOriginalFilename(),
								file.getInputStream()));
			}
		} catch (Exception e) {
			logger.error("上传文件出现异常", e);
		}
		return data;
	}

	/**
	 * 保存到附件表
	 * 
	 * @param attachments
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(List<Attachment> attachments, Admin admin, Integer mainid, Integer filetype) {
		for (Attachment attachment : attachments) {
			this.saveAttachment(attachment, admin, mainid, filetype);
		}
	}
	
	/**
	 * 保存到附件表
	 * 
	 * @param attachments
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(Attachment attachment, Integer mainid, Integer filetype) {
		if (attachment != null) {
			attachment.setMainid(mainid);
			attachment.setFiletype(filetype);
			attachment.setCreateTime(new Date());
			this.save(attachment);
		}
	}

	/**
	 * 保存到附件表
	 * 
	 * @param attachment
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(Attachment attachment, Admin admin, Integer mainid, Integer filetype) {
		if (attachment != null) {
			attachment.setMainid(mainid);
			attachment.setFiletype(filetype);
			attachment.setCreateTime(new Date());
			attachment.setMemberId(admin.getId());
			this.save(attachment);
		}
	}
	
	/**
	 * 保存到附件表
	 * 
	 * @param attachment
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(Attachment attachment, Admin admin, Integer mainid, Integer filetype,String remark) {
		if (attachment != null) {
			attachment.setMainid(mainid);
			attachment.setFiletype(filetype);
			attachment.setCreateTime(new Date());
			attachment.setMemberId(admin.getId());
			attachment.setRemark(remark);
			this.save(attachment);
		}
	}

	/**
	 * 保存到附件表
	 * 
	 * @param attachments
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(List<Attachment> attachments, Member member, Integer mainid, Integer filetype) {
		for (Attachment attachment : attachments) {
			this.saveAttachment(attachment, member, mainid, filetype);
		}
	}

	/**
	 * 保存到附件表
	 * 
	 * @param attachment
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(Attachment attachment, Member member, Integer mainid, Integer filetype) {
		if (attachment != null) {
			attachment.setMainid(mainid);
			attachment.setFiletype(filetype);
			attachment.setCreateTime(new Date());
			attachment.setMemberId(member.getId());
			this.save(attachment);
		}
	}

	/**
	 * 查询附件
	 * 
	 * @param mainid
	 * @param filetype
	 * @return
	 */
	public List<Attachment> findByMainId(Integer mainid, Integer filetype) {
		Attachment entity = new Attachment();
		entity.setMainid(mainid);
		entity.setFiletype(filetype);
		return this.select(entity);
	}

	/**
	 * 查询附件的下载地址
	 * 
	 * @param mainid
	 * @param filetype
	 * @return
	 */
	public List<String> findForDownload(Integer mainid, Integer filetype) {
		List<String> downloadPath = new ArrayList<String>();
		List<Attachment> attachments = this.findByMainId(mainid, filetype);
		for (Attachment attachment : attachments) {
			downloadPath.add(this.getAccessPath() + attachment.getFilepath());
		}
		return downloadPath;
	}
	
	/**
	 * 查询附件的下载地址
	 * 
	 * @param mainid
	 * @param filetype
	 * @return
	 */
	public List<VAttachment> findAttachment(Integer mainid, Integer filetype) {
		List<VAttachment> viewList = new ArrayList<VAttachment>();
		List<Attachment> attachments = this.findByMainId(mainid, filetype);
		for (Attachment attachment : attachments) {
			VAttachment view = CommonUtil.copyBean(attachment, VAttachment.class);
			view.setDownloadPath(this.getAccessPath() + attachment.getFilepath());
			viewList.add(view);
		}
		return viewList;
	}

	
	/**
	 * 根据对象一个属性匹配多个值
	 * @param params
	 * @return
	 */
	public List<Attachment> selectAttachmentByParamsOnProperty(
			List<Integer> params, Integer type) {
		return attachmentMapper.selectAttachmentByParamsOnProperty(params,type);
	}

	@Override
	public List<Attachment> selectAttachmentByParamsOnId(List<Integer> params,
			int designType) {
		return attachmentMapper.selectAttachmentByParamsOnId(params,designType);
	}
	
	
	public int deleteByDesignId(int designId){
		return attachmentMapper.deleteByDesignId(designId);
	}

	@Override
	public Attachment selectAttachmentByMap(Map map) {
		return attachmentMapper.selectAttachmentByMap(map);
	}
	@Override
	public Map doTransacDelImge(ArrayList<String> urls, Map pmap) throws Exception {
		Map map  = new HashMap();
		String url;
		for (int i = 0; i < urls.size(); i++) {
			 url = urls.get(i);
		     String bn=Sysconfig.CONFIG_PARAM.get(ConfigConstant.PHOTO_PREFIX_URL);
		     String imgString= url.substring(bn.length());
		     pmap.put("url", imgString);
			// 删除attachment数据
			Attachment attachment = attachmentMapper.selectAttachmentByMap(pmap);
			// 删除服务器文件
			if (attachment != null) {
				attachmentMapper.delete(attachment);
				HttpFileTools.deleteFile(attachment.getFilepath());
				map.put("doneCode", "0000");
				map.put("mess", "删除成功");
			}else {
				map.put("doneCode", "0003");
				map.put("mess", "刪除失敗");
			}
		}
		return map;
	}

	/**
	 * 根据主键id查询附件的下载地址
	 * 
	 * @param mainid
	 * @param filetype
	 * @return
	 */
	public String findForDownloadById(Integer id) {
		Attachment attachment = this.findById(id);
		if(attachment != null){
			attachment.setFilepath(this.getAccessPath() + attachment.getFilepath());
		}
		return attachment.getFilepath();
	}

	@Override
	public void deleteOneAttachment(Long mainId, int fileType) {
		attachmentMapper.deleteOneAttachment(mainId, fileType);
	}
}
