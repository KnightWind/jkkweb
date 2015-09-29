package com.jkkp.modules.product.view;

import com.jkkp.modules.product.model.PorderList;

public class VPorderList extends PorderList {
	private String typeName;
	private String gmdname;
	private Integer type;

	public String getGmdname() {
		return gmdname;
	}

	public void setGmdname(String gmdname) {
		this.gmdname = gmdname;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeVal() {
		if (this.getType() != null) {
			if (type == 0) {
				return "线上";
			}
			if (type == 1) {
				return "线下";
			}
			return "";
		}
		return "";
	}
}
