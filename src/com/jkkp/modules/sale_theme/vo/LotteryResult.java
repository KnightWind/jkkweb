package com.jkkp.modules.sale_theme.vo;

import com.jkkp.modules.activities.model.Awards;

/***
 * 
 * @author bruce 抽奖结果
 */
public class LotteryResult {
	
	private boolean status;
	
	private String error = "未中奖";

	private Awards awards;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
		if(error != null) {
			this.status = false;
		}
	}

	public Awards getAwards() {
		return awards;
	}

	public void setAwards(Awards awards) {
		this.awards = awards;
		if(awards != null) {
			this.error = null;
			this.status = true;
		}
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
