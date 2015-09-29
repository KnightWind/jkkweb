package com.jkkp.appapi.modules.mapper;

import java.util.List;

import com.jkkp.modules.order.model.Agreement;
import com.jkkp.pc.other.controller.agreementPcController;

public class VAgreementDetail extends Agreement{
	public List<String> imglist;

	public List<String> getImglist() {
		return imglist;
	}

	public void setImglist(List<String> imglist) {
		this.imglist = imglist;
	}
}
