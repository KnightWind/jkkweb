package com.jkkp.modules.supplier.view;

import com.jkkp.modules.basedata.model.Engineerings;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.supplier.model.JlComplain;
import com.jkkp.utils.DateUtil;

public class VJlComplain extends JlComplain {
	public String mnickName;
	public String spName;
	public String anickName;
	public String smobile;
	public String mmobile;
	public String createTimeHandle;
	public String updateTimeHandle;
	public String typeVal;
	public String levelVal;
	public String sourceVal;
	public String statusVal;
	public VEngineerings engineerings;
	
	public void setTypeVal(String typeVal) {
		this.typeVal = typeVal;
	}

	public void setLevelVal(String levelVal) {
		this.levelVal = levelVal;
	}

	public void setSourceVal(String sourceVal) {
		this.sourceVal = sourceVal;
	}

	public void setStatusVal(String statusVal) {
		this.statusVal = statusVal;
	}

	// 级别值
	public String getLevelVal() {
		if (this.getLevel() != null) {
			if (level == 1) {
				return "低";
			}
			if (level == 2) {
				return "中";
			}
			if (level == 3) {
				return "高";
			}
			return "";
		}
		return "";
	}

	// 状态值
	public String getStatusVal() {
		if (this.getStatus() != null) {
			if (status == 1) {
				return "已解决";
			}
			if (status == 2) {
				return "待处理";
			}
			return "";
		}
		return "";
	}

	// 类型值
	public String getTypeVal() {
		if (this.getType() != null) {
			if (type == 1) {
				return "业主投诉监理";
			}
			if (type == 2) {
				return "监理投诉业主";
			}
			return "";
		}
		return "";
	}

	// 来源值
	public String getSourceVal() {
		if (this.getSource() != null) {
			if (source == 1) {
				return "预约时";
			}
			if (source == 2) {
				return "看工地";
			}
			if (source == 3) {
				return "施工工程";
			}
			return "";
		}
		return "";
	}

	public void setCreateTimeHandle(String createTimeHandle) {
		this.createTimeHandle = createTimeHandle;
	}

	public void setUpdateTimeHandle(String updateTimeHandle) {
		this.updateTimeHandle = updateTimeHandle;
	}

	// 创建时间
	public String getCreateTimeHandle() {
		if (this.getCreateTime() != null) {
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}

	// 更新时间
	public String getUpdateTimeHandle() {
		if (this.getUpdateTime() != null) {
			return DateUtil.formatDateTime(updateTime);
		}
		return "";
	}

	public VEngineerings getEngineerings() {
		return engineerings;
	}

	public void setEngineerings(VEngineerings engineerings) {
		this.engineerings = engineerings;
	}

	public String getSmobile() {
		return smobile;
	}

	public void setSmobile(String smobile) {
		this.smobile = smobile;
	}

	public String getMmobile() {
		return mmobile;
	}

	public void setMmobile(String mmobile) {
		this.mmobile = mmobile;
	}

	public String getMnickName() {
		return mnickName;
	}

	public void setMnickName(String mnickName) {
		this.mnickName = mnickName;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getAnickName() {
		return anickName;
	}

	public void setAnickName(String anickName) {
		this.anickName = anickName;
	}
}
