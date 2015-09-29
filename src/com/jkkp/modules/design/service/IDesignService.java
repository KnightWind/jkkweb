package com.jkkp.modules.design.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.view.VDesign;
import com.jkkp.modules.supplier.view.VSupplierUser;

public interface IDesignService extends IService<Design, VDesign, Integer> {
	public void saveOrUpdate(Design design);

	public void remove(Integer id);

	Design operate(Integer id, boolean isOpen);

	List<Design> fin(Integer id);
	
	public VDesign engineeringDesignDetail(int id);

	public void saveOrUpdate(VSupplierUser su, Design design,
			Integer[] imgId,Integer aid, String hremark, HttpServletRequest request);
	
	public Map doTranscAddDesign(Map<String, Object> resultMap,HttpServletRequest request,Map<String, Object> paramMap) throws Exception;
}
