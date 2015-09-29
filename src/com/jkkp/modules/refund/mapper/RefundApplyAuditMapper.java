package com.jkkp.modules.refund.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.refund.model.RefundApplyAudit;
import com.jkkp.modules.refund.view.VRefundApplyAudit;


public interface RefundApplyAuditMapper extends Mapper<RefundApplyAudit>{
    int deleteByPrimaryKey(Integer id);

    int insert(RefundApplyAudit record);

    int insertSelective(RefundApplyAudit record);

    RefundApplyAudit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RefundApplyAudit record);

    int updateByPrimaryKey(RefundApplyAudit record);
    
    
    List<VRefundApplyAudit> selectRefundApplyList(Map<String, Object> map);
    
    long selectRefundApplyCount(Map<String, Object> map);
    
    VRefundApplyAudit detailInfo(@Param("id") Integer id);
    
    boolean insertRefundAudit(RefundApplyAudit refundApplyAudit);
    
}