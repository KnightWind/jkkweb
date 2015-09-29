package com.jkkp.modules.member.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.member.model.Member;

public interface MemberMapper extends Mapper<Member> {

	Integer findByBillId(Map<String, Object> map);

	Member findByBill(Map<String, Object> map);

	public List<Member> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	List<String> findAllPhone();

}