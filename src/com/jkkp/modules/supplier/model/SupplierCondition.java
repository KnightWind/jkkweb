package com.jkkp.modules.supplier.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "supplier_condition")
public class SupplierCondition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "sp_id")
	private Integer spId;
	public Integer getId() {
		return id;
	}
	public Integer getXzZxfs() {
		return xzZxfs;
	}
	public void setXzZxfs(Integer xzZxfs) {
		this.xzZxfs = xzZxfs;
	}
	public String getXzCity() {
		return xzCity;
	}
	public void setXzCity(String xzCity) {
		this.xzCity = xzCity;
	}
	public Float getXzYsMj() {
		return xzYsMj;
	}
	public void setXzYsMj(Float xzYsMj) {
		this.xzYsMj = xzYsMj;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public Float getXzYs() {
		return xzYs;
	}
	public void setXzYs(Float xzYs) {
		this.xzYs = xzYs;
	}
	public Float getXzMj() {
		return xzMj;
	}
	public void setXzMj(Float xzMj) {
		this.xzMj = xzMj;
	}	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "xz_ys")
	private Float xzYs;
	@Column(name = "xz_mj")
	private Float xzMj;
	@Column(name = "xz_zxfs")
	private Integer xzZxfs;
	@Column(name = "xz_city")
	private String xzCity;
	@Column(name = "xz_ys_mj")
	private Float xzYsMj;
	@Column(name = "create_time")
	private Date createTime;
}
