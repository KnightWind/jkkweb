package com.jkkp.modules.supplier.model;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "start_work")
public class StartWork {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer drawing;
	private Integer quote;
	@Column(name = "other_ask")
	private String otherAsk;
	private String conditions;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDrawing() {
		return drawing;
	}
	public void setDrawing(Integer drawing) {
		this.drawing = drawing;
	}
	public Integer getQuote() {
		return quote;
	}
	public void setQuote(Integer quote) {
		this.quote = quote;
	}
	public String getOtherAsk() {
		return otherAsk;
	}
	public void setOtherAsk(String otherAsk) {
		this.otherAsk = otherAsk;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
