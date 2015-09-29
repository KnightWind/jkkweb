package com.jkkp.modules.basedata.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.system.model.Admin;

public interface IAttachmentService extends IService<Attachment, VAttachment, Integer> {

	/**
	 * 上传单个文件
	 * 
	 * @param request
	 * @param admin
	 * @return
	 */
	public Attachment uploadOne(MultipartRequest request);

	/**
	 * 上传多个文件
	 * 
	 * @param request
	 * @param admin
	 * @return
	 */
	public List<Attachment> uploadMulti(MultipartRequest request);

	/**
	 * 上传多个文件
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Attachment> uploadMapMulti(MultipartRequest request);
	
	/**
	 * 保存到附件表
	 * 
	 * @param attachments
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(List<Attachment> attachments, Admin admin, Integer mainid, Integer filetype);
	
	/**
	 * 保存到附件表
	 * 
	 * @param attachments
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(Attachment attachments, Integer mainid, Integer filetype);

	/**
	 * 保存到附件表
	 * 
	 * @param attachment
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(Attachment attachment, Admin admin, Integer mainid, Integer filetype);
	
	/**
	 * 保存到附件表
	 * 
	 * @param attachment
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(Attachment attachment, Admin admin, Integer mainid, Integer filetype,String remark);
	
	/**
	 * 保存到附件表
	 * 
	 * @param attachment
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(Attachment attachment, Member member, Integer mainid, Integer filetype);
	
	/**
	 * 保存到附件表
	 * 
	 * @param attachments
	 * @param admin
	 * @param mainid
	 * @param filetype
	 */
	public void saveAttachment(List<Attachment> attachments, Member member, Integer mainid, Integer filetype);

	/**
	 * 查询附件
	 * 
	 * @param mainid
	 * @param filetype
	 * @return
	 */
	public List<Attachment> findByMainId(Integer mainid, Integer filetype);

	/**
	 * 查询附件的下载地址
	 * 
	 * @param mainid
	 * @param filetype
	 * @return
	 */
	public List<String> findForDownload(Integer mainid, Integer filetype);
	
	/**
	 * 根据主键id查询附件的下载地址
	 * 
	 * @param mainid
	 * @param filetype
	 * @return
	 */
	public String findForDownloadById(Integer id);
	
	/**
	 * 查询附件的下载地址
	 * 
	 * @param mainid
	 * @param filetype
	 * @return
	 */
	public List<VAttachment> findAttachment(Integer mainid, Integer filetype);
	
	/**
	 * 根据对象一个属性匹配多个值
	 * @param params mainid集合
	 * @param type 表类型
	 * @return
	 */
	public List<Attachment> selectAttachmentByParamsOnProperty(List<Integer> params, Integer type);

	/**
	 * 根据对象一个属性匹配多个值
	 * @param params mainid集合
	 * @param type 表类型
	 * @return
	 */
	public List<Attachment> selectAttachmentByParamsOnId(List<Integer> params,
			int designType);
	
	
	
	/**
	 * 设计id
	 * @param designId
	 * @return
	 */
	public int deleteByDesignId(int designId);
	//获取文件路径
	public String getBasePath();
	
	
	
	public Attachment selectAttachmentByMap(Map map);
	
	
	public Map doTransacDelImge(ArrayList<String> urls, Map pmap) throws Exception;
	
	
	public void	deleteOneAttachment(Long mainId,int fileType);
}
