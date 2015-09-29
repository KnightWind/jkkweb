package com.jkkp.modules.member.mapper;

import java.util.*;

import org.apache.ibatis.annotations.*;
import com.github.abel533.mapper.*;
import com.jkkp.appapi.modules.mapper.*;
import com.jkkp.modules.member.model.*;
import com.jkkp.modules.member.view.*;
import com.jkkp.utils.*;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VISMember;
import com.jkkp.appapi.modules.mapper.VITopicList;
import com.jkkp.appapi.modules.mapper.VITopicSV;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;
import com.jkkp.modules.member.view.VMemberTopic;

public interface TopicMapper extends Mapper<Topic> {
	List<VTopic> findList(Map<String, Object> params);

	public List<VMemberTopic> selectAllMemberTopic(Map<String, Object> params);

	public long selectAllMemberTopicCount(Map<String, Object> params);

	public VMemberTopic detail(@Param("id") int id);

	public void noPass(@Param("id") int id);
    public List<VITopicSV> queryTopic(Map<String, Object> params);
    public List<VITopicSV> queryTopicList(Map<String, Object> params);
	public void pass(@Param("id") int id);
	
	public VITopicSV queryAd();
	public VISMember getById(@Param("uid") int uid);
	public List<VIAllTopicSV> getAllTopic(Map<String, Object> params);
	public List<VIAllTopicSV> getAllJtopic(Map<String, Object> map);
	public VCaseShare getCommonnJtopic(@Param("spid") Integer spid);
	//ysc=========================================
	public List<VTopic> getList(String and);
	public List<VTopic> getPageList(Pager pager);
	public long getCount(String and);
	public VTopic getBeanById(Integer id);
	public int deleteByAnd(String and);
	public VITopicMjkSV getAllTopicDetails(@Param("uid") Integer uid,@Param("aid") Integer aid);
	//ysc=========================================

	List<VITopicList> queryCasesTopicList(Map<String, Object> map);
}