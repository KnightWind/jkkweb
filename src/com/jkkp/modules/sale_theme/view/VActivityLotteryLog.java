package com.jkkp.modules.sale_theme.view;

import com.jkkp.modules.sale_theme.model.ActivityLotteryLog;

public class VActivityLotteryLog extends ActivityLotteryLog {
	private String name;
	private Integer rank;

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
