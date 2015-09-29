/**
 * 
 */
package com.jkkp.modules.interfaceauth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.jkkp.common.IService;
import com.jkkp.modules.interfaceauth.model.UserTocken;
import com.jkkp.modules.interfaceauth.view.VUserTocken;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.refund.view.VRefundApplyAudit;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.view.VSupplierUser;

/**
 * @author Administrator
 *
 */
public interface UserTockenService extends	IService<UserTocken, VUserTocken, Integer>{

	
	
	public  ConcurrentHashMap<String,UserTocken> getUserTockenMap();

	public  void setUserTockenMap(ConcurrentHashMap<String,UserTocken> userTockenMap) ;
	
	public  UserTocken getSessionUserTocken(String tockenId) ;
	
	public UserTocken createNewTocken(VSupplierUser user) throws Exception;
	
	public UserTocken createNewTocken(Member user) throws Exception;
	
	public void deleteTocken(UserTocken tocken);
	
	public void deleteTocken(String tockenId);
	
	public void deleteTockenByUserName(String userName) throws Exception;
	
	public void deleteTockenByUserId(int userId) throws Exception;
	
	public  ConcurrentHashMap<String, String> getTransactionMap() ;
	
	public  String getPreTransactionId(String tockenId);
}
