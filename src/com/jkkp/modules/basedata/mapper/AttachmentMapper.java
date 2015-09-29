package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.Attachment;
import com.jkkp.modules.basedata.view.VAttachment;
import com.jkkp.utils.Pager;

public interface AttachmentMapper extends Mapper<Attachment> {

	List<Attachment> selectAttachmentByParamsOnProperty(@Param("params") List<Integer> params, @Param("type") Integer type);

	List<Attachment> selectAttachmentByParamsOnId(@Param("params") List<Integer> params,@Param("type")Integer designType);
	
	int deleteByDesignId(@Param("designId")Integer designId);
	
	public Attachment selectAttachmentByMap(Map map);
	//ysc=========================================
	public List<VAttachment> getList(String and);
	public List<VAttachment> getPageList(Pager pager);
	public long getCount(String and);
	public VAttachment getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
	
	//web
    public void	deleteOneAttachment(@Param("mainId") Long mainId,@Param("fileType") int fileType);
}