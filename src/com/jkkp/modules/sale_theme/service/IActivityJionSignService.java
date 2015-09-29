package com.jkkp.modules.sale_theme.service;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.sale_theme.model.ActivityJionSign;
import com.jkkp.modules.sale_theme.view.VActivityJionSign;
import com.jkkp.modules.sale_theme.view.VDaoChu;
import com.jkkp.modules.sale_theme.view.VPhone;
import com.jkkp.modules.sale_theme.view.VRecommend;

public interface IActivityJionSignService extends
		IService<ActivityJionSign, VActivityJionSign, Integer> {
	/**
	 * 判断是否已经报名
	 * @param activityId 活动ID
	 * @param phoneNo
	 *            用户电话
	 * @return
	 */
	public ActivityJionSign selectActivityJoinSignIsExits(Integer activityId, String phoneNo);

    /**
     * 引流活动报名
     * @param ajs  报名实体
     * @param from  上一页面的标识字符串
     * @param fromPhone  二维码推荐人手机号
     * @param voucherId  现金券id
     * @param activityId  活动id--针对微信扫码活动id传参     理由：并不是活动实体本身
     * @return
     */
	public Integer signIn(ActivityJionSign ajs,String from,String fromPhone,Integer voucherId,Integer activityId);
	
	
	/**
	 * 签到
	 * @param activityId 
	 * @param 用户手机号
	 */
	public Integer signActivityTheme(String phone, Integer activityId);
	
	/**
	 * 获取会员功能判断
	 * @phone 用户手机号码
	 */
	public Map<String, Object> tuijian(String phone);
	/**
	 * 获取会员功能判断
	 * @phone 用户手机号码
	 */
	public Boolean checkregister(String phone);

	/**
	 * web 建材后台录入报名信息
	 * @param ajs
	 * @param cardId
	 * @param desc 
	 * @param spId 
	 */
	public void saveData(ActivityJionSign ajs, Integer cardId, String desc, Integer spId);

	/**
	 * 检查用户是否为会员
	 * @param phone
	 * @return
	 */
	public List<ActivityJionSign> checkVip(String phone);

	/**
	 * 随机获取10个手机号码
	 * @param paramMap
	 * @return
	 */
	public List<VPhone> selectPhone(Map<String, Object> paramMap);

	public List<VDaoChu> querydaochu(Map<String, Object> map);
	public List<VRecommend> queryRecommend(Map<String, Object> map);
	
	public List<String> getAllJoinSinPhone();
	
	public void addPhoneToMember(String phone);

}
