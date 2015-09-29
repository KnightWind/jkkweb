package com.jkkp.appapi.common.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.constants.ConstantAppStatus;
import com.jkkp.appapi.common.service.interfaces.IJlappointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IJlappointmentSV;
import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.appapi.modules.mapper.VJLAppointmentPush1;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.mapper.JlappointmentPushMapper;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.model.Jlappointment;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.view.VJlappointmentPush;
import com.jkkp.modules.member.mapper.TopicMapper;
import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.mapper.SysconfigMapper;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CommonUtil;

/**
 * 
 * @author 朱国忠
 *
 */

@Service("jlappointmentPushSV")
public class JlappointmentPushSVImpl extends
		ServiceSupport<JlappointmentPush, VJlappointmentPush, Integer>
		implements IJlappointmentPushSV {
	@Autowired
	SysconfigMapper sysconfigMapper;
	@Autowired
	JlappointmentPushMapper jlappointmentPushMapper;
	@Autowired
	IJlappointmentSV iJlappointmentSV;

	@Override
	protected Mapper<JlappointmentPush> getMapper() {
		return jlappointmentPushMapper;
	}

	public List<VJlappointmentPush> queryJAppPushDetail(Map<String, Object> map) {
		return jlappointmentPushMapper.queryJAppPushDetail(map);
	}

	public List<VJLAppointmentPush1> queryJAppPushDetail1(
			Map<String, Object> map) {
		return jlappointmentPushMapper.queryJAppPushDetail1(map);
	}

	@Override
	public Object doTrasacChooseJLSp(Map<String, Object> map,
			Map<String, Object> mapBusi) throws Exception {
		Jlappointment jlappointment = null;
		Integer jlappointment_id = CommonUtil.stringToInteger((String) map
				.get("jlappointment_id"));
		String spid_list = (String) map.get("spid_list");
		String[] id_list = null;
		id_list = spid_list.split(",");
		// 转换成list
		List<String> idList = Arrays.asList(id_list);
		// //查询抢单数量是否已经超过最大数量
		Map paramMap = new HashMap();
		paramMap.put("paraName", "APPOINTMENT_MAX_SUPPLIER");
		Sysconfig config = sysconfigMapper.selectByMap(paramMap);
		int max = Integer.parseInt(Sysconfig.CONFIG_PARAM
				.get(ConfigConstant.JL_MAX_SELECT_NUM));

		if (id_list.length > max) {
			mapBusi.put("mess", "选择监理数量过多，超过最大允许值，最多" + max + "个");
			mapBusi.put("doneCode", "0011");
			return -1;
		}
		// 查询预约单是否已经存在
		jlappointment = iJlappointmentSV.findById(jlappointment_id);
		// 已存在监理预约单，本次是新增监理
		if (jlappointment != null) {
			// 查询监理预约单已经选了多少个
			List<JlappointmentPush> beforeChoosedList = super.selectByProperty(
					"aid", jlappointment_id);
			// 之前已选监理
			if (beforeChoosedList != null && beforeChoosedList.size() > 0) {
				// 之前已选+本次选择的数量>最多可选监理数
				if (beforeChoosedList.size() + id_list.length > max) {
					mapBusi.put("mess", "选择监理数量过多，超过最大允许值，最多" + max + "个，之前已选"
							+ beforeChoosedList.size() + "个");
					mapBusi.put("doneCode", "0011");
					return -1;

					// 之前有选，本次新增监理
				} else {
					// 排除掉重复选择的
					for (JlappointmentPush push : beforeChoosedList) {
						if (idList.contains(push.getSpId())) {
							// 如果当前选择的监理曾经已经推送 则移除
							idList.remove(push.getSpId());
						}
					}
					// 开始插入push表
					if (idList.size() > 0) {
						saveOrUpdateJL(jlappointment_id, idList, jlappointment);
						// 全部是重复的商家id
					} else {
						mapBusi.put("mess", "全部是重复的监理");
						mapBusi.put("doneCode", "0012");
						return -1;
					}
				}
				// 该单首次选代理
			} else {
				// 开始插入表
				if (idList.size() > 0) {
					saveOrUpdateJL(jlappointment_id, idList, jlappointment);
				}
			}
			return 0;
		} else {

			mapBusi.put("mess", "没有监理预约单信息");
			mapBusi.put("doneCode", "0013");
			return -1;

		}

	}

	// 监理信息更新
	@Transactional
	private void saveOrUpdateJL(Integer jlappointment_id, List<String> idList,
			Jlappointment jlappointment) throws Exception {
		for (String jlSpId : idList) {
			// 对应的监理预约单没有对应的监理时候，就增加推送监理
			JlappointmentPush jlappointmentPush = new JlappointmentPush();
			jlappointmentPush.setAid(jlappointment_id);
			jlappointmentPush.setStatus(ConstantAppStatus.J_PUSH_DAI_YING_DAN);
			jlappointmentPush.setCreateTime(StringAndDate.getTime());
			jlappointmentPush.setSpId(Integer.valueOf(jlSpId));
			jlappointmentPush.setRandom(0);
			super.save(jlappointmentPush);
			jlappointment.setStatus(Integer
					.valueOf(ConstantAppStatus.J_DAI_QIANG_DAN));
			iJlappointmentSV.update(jlappointment);
		}
	}

	@Override
	/**
	 * jlappointment_id 监理预约单id
	 */
	public Map<String, Object> queryJlSpPushNum(Map<String, Object> map,
			Map<String, Object> mapBusi) {
		Map dataMap = new HashMap();
		Integer jlappointment_id = null;
		int selectedNum = 0;
		if (map.containsKey("jlappointment_id")) {
			String idString = (String) map.get("jlappointment_id");
			if (idString != null && !idString.equals("")
					&& !idString.equals("null")) {
				jlappointment_id = CommonUtil.stringToInteger((String) map
						.get("jlappointment_id"));
				// 查询监理预约单已经选了多少个
				List<JlappointmentPush> beforeChoosedList = super
						.selectByProperty("aid", jlappointment_id);

				if (beforeChoosedList != null && beforeChoosedList.size() > 0) {
					selectedNum = beforeChoosedList.size();
				}
			}
		}
		// 查询配置阀值
		Map paramMap = new HashMap();
		paramMap.put("paraName", "APPOINTMENT_MAX_SUPPLIER");
		Sysconfig config = sysconfigMapper.selectByMap(paramMap);
		int max = Integer.parseInt(Sysconfig.CONFIG_PARAM
				.get(ConfigConstant.JL_MAX_SELECT_NUM));

		dataMap.put("max", max);
		dataMap.put("selectdNum", selectedNum);
		return dataMap;
	}

	public void overOtherJLPush(int successJlpushId) {
		// TODO Auto-generated method stub
		JlappointmentPush push = this.findById(successJlpushId);
		if (push != null) {
			List<JlappointmentPush> other = this.selectByProperty("aid",
					push.getAid(), "aid", true);
			if (other == null)
				return;
			for (JlappointmentPush o : other) {
				if (o.getId() != successJlpushId) {
					if (o.getStatus() == ConstantAppStatus.J_PUSH_DAI_YING_DAN)// 待抢单的变为结束
					{
						o.setStatus(ConstantAppStatus.J_PUSH_JIE_SU_QIANGDAN);
					}
					o.setRandom(1);//都修改为关闭
					this.update(o);
				}
			}
		}

	}

}
