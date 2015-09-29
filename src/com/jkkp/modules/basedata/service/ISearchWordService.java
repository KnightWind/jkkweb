package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.SearchWord;
import com.jkkp.modules.basedata.view.VSearchWord;

public interface ISearchWordService extends IService<SearchWord,VSearchWord,Integer>{
	List<SearchWord> top();
	List<SearchWord> hu();
	List<SearchWord> feng();
}
