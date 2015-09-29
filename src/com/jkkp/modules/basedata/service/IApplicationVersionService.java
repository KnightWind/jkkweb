package com.jkkp.modules.basedata.service;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.AdSearch;
import com.jkkp.modules.basedata.model.ApplicationVersion;
import com.jkkp.modules.basedata.view.VAdSearch;
import com.jkkp.modules.basedata.view.VApplicationVersion;

public interface IApplicationVersionService extends IService<ApplicationVersion,VApplicationVersion,Integer> {
	public ApplicationVersion checkApplicationVersion(Map<String, Object> map) throws Exception;
}
