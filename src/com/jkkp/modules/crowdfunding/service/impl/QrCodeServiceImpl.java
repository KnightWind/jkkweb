package com.jkkp.modules.crowdfunding.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.QrCodeMapper;
import com.jkkp.modules.crowdfunding.model.QrCode;
import com.jkkp.modules.crowdfunding.service.IQrCodeService;
import com.jkkp.modules.crowdfunding.view.VQrCode;
import com.jkkp.utils.CommonUtil;

@Service("qrCodeService")
public class QrCodeServiceImpl extends ServiceSupport<QrCode, VQrCode, Integer> implements
		IQrCodeService {
	
	@Autowired
	private QrCodeMapper qrCodeMapper;
	
	@Override
	public String saveOneAndReturnCode(Integer spId, Double fee, Integer type) {
		//二维码处理字符
	    String codeChar=spId.toString()+"e"+fee.toString()+type.toString();
	    
	    try {
			String md5Char=CommonUtil.md5(codeChar);
			List<QrCode> beanRel = this.selectByProperty("qrCode", md5Char);
			
			//存在则返回二维码串
			if(beanRel!=null&&beanRel.size()>0){
				return beanRel.get(0).getQrCode();
			}
			//否则新建一二维码串并返回
			QrCode bean=new QrCode();
			bean.setCreateDate(new Date());
			bean.setFee(fee);
			bean.setType(type);
			bean.setSpId(spId);
			bean.setActivityName("众凑活动");
			bean.setQrCode(md5Char);
			this.save(bean);
			return md5Char;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	protected Mapper<QrCode> getMapper() {
		return qrCodeMapper;
	}

	@Override
	public void updateProductInfo(String productInfo, Integer id) {
		qrCodeMapper.updateProductInfo(productInfo, id);		
	}

	@Override
	public void updateAddress(String address, Integer id) {
		qrCodeMapper.updateAddress(address, id);		
	}

	@Override
	public void updateRemark(String remark, Integer id) {
		qrCodeMapper.updateRemark(remark, id);		
	}


}
