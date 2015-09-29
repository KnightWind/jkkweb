package com.jkkp.modules.crowdfunding.service;

import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.Zcjs;
import com.jkkp.modules.crowdfunding.view.VZcJs;

public interface IZcJsService extends IService<Zcjs, VZcJs, Integer> {
	public Double selectJSTotal(int spId);
}
