package com.jkkp.modules.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.member.mapper.MemberMapper;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.VMember;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.member.view.VMembers;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.BaseTools;
import com.jkkp.utils.CommonUtil;

@Service("memberService")
public class MemberServiceImpl extends
		ServiceSupport<Member, VMembers, Integer> implements IMemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired private IBaseinf IBaseinfService;
	@Override
	protected Mapper<Member> getMapper() {
		return memberMapper;
	}

	@Override
	public List<String> getAllMemberPhone() {
		List<Member> memberList = this.select(null);
		List<String> phoneList = new ArrayList<String>();
		for (Member bean : memberList) {
			phoneList.add(bean.getMobile());
		}
		return phoneList;
	}

	@Override
	public void memeberChange(String Phone) {
		Member bean = new Member();
		bean.setNickname("家可可用户");
		bean.setMobile(Phone);
		try {
			String pwd = Sysconfig.CONFIG_PARAM
					.get(ConfigConstant.INIT_PASSWORD);
			pwd = pwd.isEmpty() ? "a1234567" : pwd;
			bean.setPassword(CommonUtil.md5(pwd));
		} catch (Exception e) {
			e.printStackTrace();
		}
		bean.setCreateTime(new Date());
		this.save(bean);
	}

	@Transactional(readOnly = true)
	public List<String> findAllPhone() {
		return memberMapper.findAllPhone();
	}

	@Override
	public void deleteOne(Integer id) {
		this.deleteById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Member updateMember(int memberId, Member member) throws Exception {
		Member memberdest = this.findById(memberId);
		BaseTools.CopyBeanNotNull(member, memberdest);
		this.update(memberdest);
		return memberdest;
	}

	@Override
	public VMember updateMember(int memberId, Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		VMember vmember = new VMember();
		
		Member member = new Member();
		member=BaseTools.converter(map,Member.class);
		//兼容之前接口定义
		if(map.containsKey("name")){
			String name = (String) map.get("name");
			member.setNickname(name);
		}
		
		member=updateMember(memberId, member);
		BaseTools.CopyBean(member, vmember);
		vmember.setHeadImg(IBaseinfService.getHeadimg(memberId,
				AttachmentConstant.MEMBER_TYPE));
		return vmember;
	}

}
