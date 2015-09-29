package com.jkkp.appapi.modules.mapper;

public class VJLAppointmentPush1{
    public Integer id;
    
    /*** 预约id*/
    public Integer aid;
    
    public Integer spId;
    
    /** 状态：0未查看1已查看 */
    public Byte status;

	private float levelFlag;
	
	/**订单号*/
	private int orderNo;
	
	/**供应商*/
	private String spname;
	
	private String headimg;
	
	private Integer random;
    
	public Integer getRandom() {
		return random;
	}
	public void setRandom(Integer random) {
		this.random = random;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public float getLevelFlag() {
		return levelFlag;
	}
	public void setLevelFlag(float levelFlag) {
		this.levelFlag = levelFlag;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
}
