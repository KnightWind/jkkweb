package com.jkkp.modules.member.service;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.VMember;
import com.jkkp.modules.member.view.VMembers;

public interface IMemberService extends IService<Member, VMembers, Integer> {
   public List<String> getAllMemberPhone();
   
   
   //微信引流活动报名用户转换
   public void memeberChange(String Phone);
	List<String> findAllPhone();

   public void deleteOne(Integer id);
   
   /**
    * 更新会员对应字段
    * @param memberId 会员id
    * @param member 需要更新的字段
 * @return 
 * @throws Exception 
    */
   public Member updateMember(int memberId,Member member) throws Exception;
   /**
    * 接口更新会员对应字段
    * @param memberId 会员id
    * @param map 需要更新的字段
 * @return 
 * @throws Exception 
    */
   public VMember updateMember(int memberId,Map<String, Object> map) throws Exception;
}
