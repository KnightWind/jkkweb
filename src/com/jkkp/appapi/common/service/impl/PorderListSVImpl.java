package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IPorderListSV;
import com.jkkp.appapi.common.service.interfaces.IPorderTypeSV;
import com.jkkp.appapi.common.service.interfaces.ITopicSV;
import com.jkkp.appapi.modules.mapper.VPorderList;
import com.jkkp.appapi.modules.mapper.VPorderType;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.TopicMapper;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;
import com.jkkp.modules.product.mapper.ItemMapper;
import com.jkkp.modules.product.mapper.PorderListMapper;
import com.jkkp.modules.product.mapper.PorderTypeMapper;
import com.jkkp.modules.product.model.PorderList;
import com.jkkp.modules.product.model.PorderType;
import com.jkkp.utils.Pagination;
/**
 * 
 * @author 朱国忠
 *
 */


@Service("PorderListApiService")
public class PorderListSVImpl extends ServiceSupport<PorderList,VPorderList,Integer>
		implements IPorderListSV {

	@Autowired PorderListMapper Mapper;
	

	@Override
	protected Mapper<PorderList> getMapper() {
		// TODO Auto-generated method stub
		return Mapper;
	}
	

}
