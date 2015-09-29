package com.jkkp.modules.member.mapper;

import java.util.List;
import java.util.Map;

import com.bean.MemberRedPackageVO;
import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.member.model.MemberRedPackage;

public interface MemberRedPackageMapper  extends Mapper<MemberRedPackage>{
	/**
	 * 获取我的钱包红包列表
	 * @param params
	 * @return
	 */
	public List<MemberRedPackageVO> findMemberPurseRedPackgeList(Map<String, Object> params);
	/**
	 * 提交订单红包列表
	 * @param params
	 * @return
	 */
	public List<MemberRedPackageVO> findOrderRedPackageList(Map<String, Object> params);
	/**
	 * 使用红包
	 * @param params
	 * @return
	 */
	public void usedMyRedPackge(Map<String, Object> params);
	/**
	 * 使用可拆分红包
	 * @param params
	 * @return
	 */
	public void usedSplitRedPackge(Map<String, Object> params);
	/**
	 * 获取单个红包记录
	 * @param id
	 * @return
	 */
	public MemberRedPackage findById(Integer id);
	/**
	 * 发送红包
	 * @param id
	 * @return
	 */
	public void batchInsertRedPackage(List<MemberRedPackage> list);
	/**
	 * 查询用户已领红包个数
	 * @param params:memberId,redPackageId
	 * @return Integer
	 */
	Integer findUserGetNum(Map<String,Object> params);
}