package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IJiaKeBaoApiSV;
import com.jkkp.appapi.common.service.interfaces.ITopicSV;
import com.jkkp.appapi.modules.mapper.VJiakebao;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.member.mapper.TopicMapper;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;
import com.jkkp.modules.product.mapper.ItemMapper;
import com.jkkp.modules.supplier.mapper.JiakebaoMapper;
import com.jkkp.modules.supplier.model.Jiakebao;
import com.jkkp.utils.Pagination;
/**
 * 
 * @author 朱国忠
 *
 */


@Service("JiakebaoService")
public class JiaKeBaoSVImpl extends ServiceSupport<Jiakebao, VJiakebao, Integer>
		implements IJiaKeBaoApiSV {

	@Autowired JiakebaoMapper Mapper;

	@Override
	protected Mapper<Jiakebao> getMapper() {
		// TODO Auto-generated method stub
		return Mapper;
	}


}
