package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.SupplierBranch;
import com.jkkp.modules.basedata.view.VSupplierBranch;

public interface ISupplierBranchService extends IService<SupplierBranch, VSupplierBranch, Integer> {
	  /**
	   * 获取商户分店列表
	   * @param spId
	   * @return
	   */
      public List<SupplierBranch> getSupplierSupplierBranch(Integer spId);
      
      /**
       * 更新或添加一商家分店
       * @param bean
       */
      public void saveOrUpdate(SupplierBranch bean);
      
      /**
       * 删除一商家分店
       * @param id
       */
      public void deleteOne(Integer id);
}
