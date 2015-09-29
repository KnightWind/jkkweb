package com.jkkp.modules.product.model;

import java.util.Date;
import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "porder_type")
public class PorderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer status;
    public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "type_name")
    private String typeName;
    @Column(name = "remark")
	private String remark;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_user")
    private String createUser;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCreateTimeHandle(){
		if(createTime!=null){
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
	
	public String getStatusVal(){
		if(status==null||status!=1){
			return "隐藏";
		}else{
			return "显示";
		}
	}
	
}