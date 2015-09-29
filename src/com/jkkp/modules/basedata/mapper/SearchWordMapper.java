package com.jkkp.modules.basedata.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.SearchWord;

public interface SearchWordMapper extends Mapper<SearchWord> {
	List<SearchWord> top();
	List<SearchWord> hu();
	List<SearchWord> feng();
}