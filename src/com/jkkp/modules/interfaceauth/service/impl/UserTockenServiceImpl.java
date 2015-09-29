/**
 * 
 */
package com.jkkp.modules.interfaceauth.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.control.common.IdCreator;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.interfaceauth.mapper.InterfaceRecordMapper;
import com.jkkp.modules.interfaceauth.mapper.UserTockenMapper;
import com.jkkp.modules.interfaceauth.model.UserTocken;
import com.jkkp.modules.interfaceauth.service.UserTockenService;
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
@Service("userTockenService")
public class UserTockenServiceImpl  extends ServiceSupport<UserTocken, VUserTocken, Integer> implements UserTockenService{
	private static ConcurrentHashMap<String,UserTocken> userTockenMap = new ConcurrentHashMap <String,UserTocken>();
	private static ConcurrentHashMap<String,String> transactionMap = new ConcurrentHashMap <String,String>();

	
	
	private static int inactionDay = 7;//不活跃天数
	
	private UserTockenService service ;
	@Autowired
	UserTockenMapper userTockenMapper;
	
	public  ConcurrentHashMap<String,UserTocken> getUserTockenMap() {
		return userTockenMap;
	}

	public  void setUserTockenMap(ConcurrentHashMap<String,UserTocken> userTockenMap) {
		UserTockenServiceImpl.userTockenMap = userTockenMap;
	}
	
	
	public  UserTocken getSessionUserTocken(String tockenId) {
		if(tockenId==null){
			return null;
		}
		
		UserTocken userTockenInfo=null;
		userTockenInfo = userTockenMap.get(tockenId);
		//session中没有，则查看库表中是否有登录在线记录
		if(userTockenInfo==null){
			//查在线表
			UserTocken tocken = new UserTocken();
			tocken.setTockenId(tockenId);
			userTockenInfo = userTockenMapper.selectOne(tocken);
			if(userTockenInfo!=null){
				//将信息放入缓存
				userTockenMap.put(tockenId, userTockenInfo);
			}
		}
		return userTockenInfo;
	}


	/* 
	 * 生成登录在线记录
	 * 用户保持登录状态
	 */
	@Override
	public UserTocken createNewTocken(VSupplierUser user) throws Exception {
		//删除原来的登录记录
		deleteTockenByUserId(user.getSpId());
		
		//生成tocken
		String tockenId = IdCreator.generateTockenId();
		UserTocken tocken = new UserTocken();
		tocken.setTockenId(tockenId);
		tocken.setUserId(user.getSpId());
		tocken.setUserName(user.getUsername());
		tocken.setCreateDate(new Date());
		tocken.setUserType(user.getType());
//		//插入登录tocken表
		userTockenMapper.insert(tocken);
		//将用户信息放入session缓存
		userTockenMap.put(tockenId, tocken);
		return tocken;
	}
	/**
	 * 删除用户tocken数据，用户将变成登出状态
	 */
	@Override
	public void deleteTocken(UserTocken tocken) {
		if(tocken==null){
			return ;
		}
		// TODO Auto-generated method stub
		userTockenMap.remove(tocken.getTockenId());
		userTockenMapper.delete(tocken);
	}
	/**
	 * 删除用户tocken数据，用户将变成登出状态
	 */
	@Override
	public void deleteTocken(String tockenId) {
		// TODO Auto-generated method stub
		if(tockenId==null){
			return ;
		}
		userTockenMap.remove(tockenId);
		UserTocken tocken = new UserTocken();
		tocken.setTockenId(tockenId);
		userTockenMapper.delete(tocken);
	}
	
	public void deleteTockenByUserId(int userId) throws Exception {
		try {
			UserTocken deltk=new UserTocken();
			for (UserTocken tocken : userTockenMap.values()) {
				  if(tocken.getUserId()==userId){
					  userTockenMap.remove(tocken.getTockenId());
					  deltk.setUserId(userId);
					  userTockenMapper.delete(deltk);
				  }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteTockenByUserName(String userName) throws Exception {
		if(userName==null){
			return ;
		}
		UserTocken deltk=new UserTocken();
		for (UserTocken tocken : userTockenMap.values()) {
			  if(userName.equals(tocken.getUserName())){
				  userTockenMap.remove(tocken.getTockenId());
				  deltk.setUserName(userName);
				  userTockenMapper.delete(deltk);
			  }
		}
	}
	/**
	 * 在内存中移除不活跃的用户
	 * 
	 */
	public void removeInactionUserInRam(){
		for (UserTocken tocken : userTockenMap.values()) {
			if(tocken.getRecentVistiTime()!=null){
				long days = ((new Date().getTime())-tocken.getRecentVistiTime().getTime())/(1000 * 60 * 60 * 24);
				//7天没有访问应用 则将其从缓存中移除
				if(days>inactionDay){
					  userTockenMap.remove(tocken.getTockenId());
				}
			}
		}
	}

	public  ConcurrentHashMap<String, String> getTransactionMap() {
		return transactionMap;
	}

	public static void setTransactionMap(
			ConcurrentHashMap<String, String> transactionMap) {
		UserTockenServiceImpl.transactionMap = transactionMap;
	}

	@Override
	protected Mapper<UserTocken> getMapper() {
		return userTockenMapper;
	}

	@Override
	public String getPreTransactionId(String tockenId) {
		return transactionMap.get(tockenId);
	}

	@Override
	public UserTocken createNewTocken(Member user) throws Exception {
		//删除原来的登录记录
		deleteTockenByUserId(user.getId());
		
		//生成tocken
		String tockenId = IdCreator.generateTockenId();
		UserTocken tocken = new UserTocken();
		tocken.setTockenId(tockenId);
		tocken.setUserId(user.getId());
		tocken.setUserName(user.getNickname());
		tocken.setCreateDate(new Date());
		tocken.setUserType(20);
//		//插入登录tocken表
		userTockenMapper.insert(tocken);
		//将用户信息放入session缓存
		userTockenMap.put(tockenId, tocken);
		return tocken;
	}
		
}
