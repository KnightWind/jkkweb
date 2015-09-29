package com.jkkp.modules.crowdfunding.service;

import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.QrCode;
import com.jkkp.modules.crowdfunding.view.VQrCode;


public interface IQrCodeService extends IService<QrCode, VQrCode, Integer> {
   public String saveOneAndReturnCode(Integer spId,Double fee,Integer type);
   
   public void updateProductInfo(String productInfo,Integer id);
   
   public void updateAddress(String address,Integer id);
   
   public void updateRemark(String remark,Integer id);
}
