package com.jkkp.modules.basedata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.SearchWordMapper;
import com.jkkp.modules.basedata.model.SearchWord;
import com.jkkp.modules.basedata.service.ISearchWordService;
import com.jkkp.modules.basedata.view.VSearchWord;

@Service("searchWordService")
public class SearchWordServiceImpl extends ServiceSupport<SearchWord,VSearchWord,Integer> implements ISearchWordService{
	@Autowired
	private SearchWordMapper searchWordMapper;
	@Override
	protected Mapper<SearchWord> getMapper() {
		return searchWordMapper;
	}
	@Override
	public List<SearchWord> top() {
		return searchWordMapper.top();
	}
	@Override
	public List<SearchWord> hu() {
		return searchWordMapper.hu();
	}
	@Override
	public List<SearchWord> feng() {
		return searchWordMapper.feng();
	}
	

}
