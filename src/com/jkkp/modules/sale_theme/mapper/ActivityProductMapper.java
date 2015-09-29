package com.jkkp.modules.sale_theme.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.sale_theme.model.ActivityProduct;
import com.jkkp.modules.sale_theme.view.VActivityProduct;

public interface ActivityProductMapper extends Mapper<ActivityProduct> {
	 public  VActivityProduct selectOneWXActivityProductDetail(@Param("id") int id);
	 
	 public List<VActivityProduct> productList(Map<String, Object> map);
	 public long productListCount(Map<String, Object> map);
 	 public int selectMaxOrder();
 	 public VActivityProduct findProductInfoById(@Param("id") Integer id);
	 
}
