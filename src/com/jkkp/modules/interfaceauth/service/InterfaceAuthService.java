/**
 * 
 */
package com.jkkp.modules.interfaceauth.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkkp.modules.interfaceauth.mapper.InterfaceRecordMapper;
import com.jkkp.modules.interfaceauth.model.InterfaceRegister;
import com.jkkp.modules.sale_theme.mapper.ActivityJoinSignMapper;

/**
 * @author Administrator
 *
 */
public interface InterfaceAuthService {
	
	

	public  HashMap<String, InterfaceRegister> getInterfaceRegisterMap() ;

	public  void setInterfaceRegisterMap(HashMap<String, InterfaceRegister> interfaceRegisterMap);
	
	
	public  InterfaceRegister getInterfaceRegister(String interfaceCode) ;
	
	
}
