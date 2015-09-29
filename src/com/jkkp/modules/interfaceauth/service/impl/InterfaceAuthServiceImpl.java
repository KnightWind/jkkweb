/**
 * 
 */
package com.jkkp.modules.interfaceauth.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkkp.modules.interfaceauth.mapper.InterfaceRecordMapper;
import com.jkkp.modules.interfaceauth.mapper.InterfaceRegisterMapper;
import com.jkkp.modules.interfaceauth.model.InterfaceRegister;
import com.jkkp.modules.interfaceauth.service.InterfaceAuthService;
import com.jkkp.modules.sale_theme.mapper.ActivityJoinSignMapper;

/**
 * @author Administrator
 *
 */
@Service("interfaceAuthService")
public class InterfaceAuthServiceImpl implements InterfaceAuthService {
	
	@Autowired
	InterfaceRecordMapper interfaceRecordMapper;
	@Autowired
	InterfaceRegisterMapper interfaceRegisterMapper;
	
	
	private static HashMap<String,InterfaceRegister> interfaceRegisterMap = null;
	private void init(){
		List<InterfaceRegister> list = interfaceRegisterMapper.select(null);
		if(list!=null){
			if(interfaceRegisterMap==null){
				interfaceRegisterMap = new HashMap();
			}
			for(InterfaceRegister intf : list){
				interfaceRegisterMap.put(intf.getInterfaceCode(), intf);
			}
		}
	}
	public void addInterfaceLog(){
		
	}

	public  HashMap<String, InterfaceRegister> getInterfaceRegisterMap() {
		if(interfaceRegisterMap==null){
			init();
		}
		return interfaceRegisterMap;
	}

	public  void setInterfaceRegisterMap(
			HashMap<String, InterfaceRegister> interfaceRegisterMap) {
		InterfaceAuthServiceImpl.interfaceRegisterMap = interfaceRegisterMap;
	}
	
	
	public  InterfaceRegister getInterfaceRegister(String interfaceCode) {
		if(interfaceRegisterMap==null){
			init();
		}
		InterfaceRegister intf=null;
		if(interfaceRegisterMap!=null){
			intf = interfaceRegisterMap.get(interfaceCode);
			return intf;
		}else{
			return null;
		}
		
	}
}
