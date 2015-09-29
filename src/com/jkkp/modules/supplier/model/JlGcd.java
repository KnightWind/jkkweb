package com.jkkp.modules.supplier.model;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "jl_gcd")
public class JlGcd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "sp_id")
	private Integer spId;
	@Column(name = "gcd_id")
	private Integer gcdId;
	private Integer activity;
	public Integer getActivity() {
		return activity;
	}
	public void setActivity(Integer activity) {
		this.activity = activity;
	}
	public Integer getId() {
		return id;
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
	public Integer getGcdId() {
		return gcdId;
	}
	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}
}
