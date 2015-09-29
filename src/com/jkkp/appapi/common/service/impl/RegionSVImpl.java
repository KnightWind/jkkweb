package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IRegionSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.RegionMapper;
import com.jkkp.modules.basedata.model.Region;


@Service("iRegionSV")
public class RegionSVImpl extends ServiceSupport<Region, Region, Integer> implements IRegionSV {

	@Autowired
	RegionMapper mapper;
	@Override
	protected Mapper<Region> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	@Override
	public Region findRegionByName(Map<String, Object> map) {
		return mapper.findRegionByName(map);
	}
	
	@Override
	public List<Region> findByParentid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.findByParentid(map);
	}
	@Override
	public String fullRegionName(int region) {
		String fullnameString=new String();
		Region r=null;
		int t=0;
		for(int i=0;i<10;i++){
			if(t==0){
				if(this.findById(region)!=null){
					r=this.findById(region);
					fullnameString=r.getRegionname();
					t=1;
				}
			}
			if(this.findById(r.getParentid())!=null){
				r=this.findById(r.getParentid());
				fullnameString=r.getRegionname()+fullnameString;
			}else {
				break;
			}
		}
		return fullnameString;
		/*try {
			if(this.findById(region)!=null)
				return this.findById(region).getRegionname();
			else {
				return "";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return "";
		}*/
	}
	@Override
	public String RegionName(int region) {
		// TODO Auto-generated method stub
		String fullnameString="";
		Region r=null;
		if(this.findById(region)!=null){
			r=this.findById(region);
			fullnameString=r.getRegionname();
		}
		return fullnameString;
	}
	@Override
	public List<Region> findOpenCity() {
		return this.selectByProperty(new String[]{"status","level"}, new Object[]{1,2});
	}
}
