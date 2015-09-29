package com.jkkp.modules.supplier.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.JlComment;
import com.jkkp.modules.supplier.view.VJlComment;

public interface IJlCommentService extends
		IService<JlComment, VJlComment, Integer> {

	List<JlComment> findByMxInstId(Integer mxInstId);

}
