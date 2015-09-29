package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.JlComplainDetails;
import com.jkkp.modules.supplier.view.VJlComplaintsDetails;

public interface IJlComplainDetailsService extends
		IService<JlComplainDetails, VJlComplaintsDetails, Integer> {
    public void saveOne(JlComplainDetails j);
    public void saveOneJL(JlComplainDetails j);
}
