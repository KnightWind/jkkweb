package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierUserSV;
import com.jkkp.appapi.modules.mapper.VISuppMessagePush;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SuppMessagePushMapper;
import com.jkkp.modules.supplier.model.SuppMessagePush;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.view.VSupplierUser;

@Service("suppMessagePushSV")
public class SuppMessagePushSVImpl extends
		ServiceSupport<SuppMessagePush, VISuppMessagePush, Integer> implements
		ISuppMessagePushSV {

	@Autowired
	ISupplierSV supplierSVImpl;

	@Autowired
	ISupplierUserSV supplierUserSVImpl;

	@Autowired
	SuppMessagePushMapper suppMessagePushMapper;

	@Override
	protected Mapper<SuppMessagePush> getMapper() {
		// TODO Auto-generated method stub
		return suppMessagePushMapper;
	}

	// 填入商家信息到推送表
	@Override
	public void dealPushLoad(Map<String, Object> map) {
		System.out.println("map=" + map);
		VSupplierUser vsupplierUser = (VSupplierUser) map.get("supplierUser");
		List<SuppMessagePush> suppMessagePushs = null;
		SuppMessagePush suppMessagePush = new SuppMessagePush();
		SupplierUser supplierUser = null;
		try {
			String spuname = (String) map.get("userName");
			String cid = (String) map.get("cid");
			Integer supliuerId = 0;
			suppMessagePushs = suppMessagePushMapper.qryByCid(map);
			// 如不存在则新增一条数据
			if (suppMessagePushs.size() <= 0) {
				supplierUser = supplierUserSVImpl.findByUserName(map);
				if (supplierUser != null) {
					supliuerId = supplierUser.getSpId();
					Integer userType = supplierUser.getIsMerchandiser();
					if (vsupplierUser.getType() == 1) {// 商家才判断是否接单员
						// 如不是接单员，不记录器对应clientid，后面则不推送预约记录
						if (userType != 0) {
							return;
						}
					}
				} else {
					return;
				}
				suppMessagePush.setSpId(supliuerId);
				suppMessagePush.setSpName(spuname);
				// 用户类型
				suppMessagePush.setUserType(vsupplierUser.getType());
				if (cid != null) {
					suppMessagePush.setCid(cid);
					this.save(suppMessagePush);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 填入用户信息到推送表，用户使用手机号后9位作为spid
	@Override
	public void dealPushLoadMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("map=" + map);
		SuppMessagePush suppMessagePush = new SuppMessagePush();
		if (suppMessagePushMapper.qryByCid(map).size() > 0) {
			return;
		}
		try {
			String spuname = (String) map.get("userName");
			String cid = (String) map.get("cid");
			/*
			 * String shortphone= (String) map.get("spId");//int 长度问题，截取9位数字
			 * if(shortphone.length()>9)
			 * shortphone=shortphone.substring(shortphone.length()-9); Integer
			 * spId=Integer.valueOf(shortphone);
			 */
			suppMessagePush.setSpId((Integer) map.get("spId"));
			suppMessagePush.setSpName(spuname);
			suppMessagePush.setUserType(20);
			if (cid != null && !cid.equals("")) {
				suppMessagePush.setCid(cid);
				this.save(suppMessagePush);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据手机号查找
	@Override
	public List<SuppMessagePush> selectByMobile(String mobile) {
		String shortphone = null;
		if (mobile.length() > 9)
			shortphone = mobile.substring(mobile.length() - 9);
		return this.selectByProperty("spId", Integer.valueOf(shortphone));
	}

	// 根据用户类型和用户id查找
	@Override
	public List<SuppMessagePush> selectByUserIdAndType(int userId, int type) {
		return this.selectByProperty(new String[] { "spId", "userType" },
				new Object[] { userId, type });
	}

	@Override
	public void deleteByCid(String cid) {
		suppMessagePushMapper.deleteByCid(cid);
	}

}
