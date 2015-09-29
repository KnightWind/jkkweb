package com.jkkp.pc.cases.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.Case;
import com.jkkp.pc.cases.view.VCases;

public interface ICasesService extends IService<Case, VCases, Integer> {

	List<VCases> queryXCases(Integer id,Integer type,Integer count);

}
