package com.jkkp.modules.basedata.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.AdSearchMapper;
import com.jkkp.modules.basedata.mapper.ApplicationVersionMapper;
import com.jkkp.modules.basedata.model.AdSearch;
import com.jkkp.modules.basedata.model.AdSearchTag;
import com.jkkp.modules.basedata.model.ApplicationVersion;
import com.jkkp.modules.basedata.service.IAdSearchService;
import com.jkkp.modules.basedata.service.IApplicationVersionService;
import com.jkkp.modules.basedata.view.VAdSearch;
import com.jkkp.modules.basedata.view.VApplicationVersion;
@Service("applicationVersionService")
public class ApplicationVersionService  extends ServiceSupport<ApplicationVersion,VApplicationVersion,Integer> implements IApplicationVersionService {
	
	@Autowired
	private ApplicationVersionMapper applicationVersionMapper;
	@Override
	protected Mapper<ApplicationVersion> getMapper() {		
		return applicationVersionMapper;
	}
	@Override
	public ApplicationVersion checkApplicationVersion(Map<String, Object> map) throws Exception{
		int version;int osType;int appType;String deviceId;
		try {	
			//version=(int) map.get("version");
			osType=(int) map.get("osType");
			appType=(int) map.get("appType");
			deviceId=(String) map.get("deviceId");
		} catch (Exception e) {
			throw new Exception("parameters error");
		}
		List<ApplicationVersion> appver=this.selectByProperty(new String[]{"osType","appType"}, new Object[]{osType,appType});
		if(appver.size()>0){
			//获取最新的版本
			int maxversion=-1;
			int i = 0;
			for (; i < appver.size(); i++) {
				if(appver.get(i).getVersion()>maxversion)
					maxversion=i;
			}
			if(maxversion!=-1)
				return appver.get(maxversion);
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
}
