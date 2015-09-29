package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.appapi.modules.mapper.VPorderList;
import com.jkkp.appapi.modules.mapper.VPorderType;
import com.jkkp.common.IService;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;
import com.jkkp.modules.product.model.Item;
import com.jkkp.modules.product.model.PorderList;
import com.jkkp.modules.product.model.PorderType;
import com.jkkp.modules.product.view.VItem;

public interface IPorderListSV extends IService<PorderList,VPorderList,Integer>{
}
