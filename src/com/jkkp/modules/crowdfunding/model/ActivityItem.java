package com.jkkp.modules.crowdfunding.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/**
 * 众筹产品
 * 
 * @author Administrator
 *
 */
@Table(name = "zc_item")
public class ActivityItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "num_people")
	private int numPeople; // 众筹人数

	@Column(name = "create_date")
	private Date createDate; // 活动创建日期

	@Column(name = "flag")
	private String flag; // 是否有效

	@Column(name = "sp_id")
	private Integer spId; // 商户id

	@Column(name = "title")
	private String title; // 商品名称

	@Column(name = "model")
	private String model; // 商品型号

	@Column(name = "activity_id")
	private Integer activityId; // 活动id

	@Column(name = "category_id")
	private Integer categoryId; // 品类id

	@Column(name = "top_price")
	private Double topPrice; // 原始价

	@Column(name = "low_price")
	private Double lowPrice; // 最低价

	@Column(name = "now_price")
	private Double activityPrice; // 众筹价格

	@Column(name = "counts")
	private Integer counts; // 众筹人数

	@Column(name = "item_id")
	private Integer itemId; // 商品id

	@Column(name = "start_time")
	private Date startTime; // 活动开始时间

	@Column(name = "end_time")
	private Date endTime; // 活动结束时间
	
	@Column(name = "status")
	private Integer status;

	private List<String> imgslist; // 商品图片合集

	private String images; // 商品图片

	private String spImg; //商家Logo
	
	private String supplierName; // 商家名称

	private Double deposit; // 定金

	private Integer isRefund; // 是否可以退款

	private Double refundProportion;
	private Integer compare; //是否活动结束
	public Integer getCompare() {
		return compare;
	}

	public void setCompare(Integer compare) {
		this.compare = compare;
	}

	private String detail;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(Integer isRefund) {
		this.isRefund = isRefund;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Double getRefundProportion() {
		return refundProportion;
	}

	public void setRefundProportion(Double refundProportion) {
		this.refundProportion = refundProportion;
	}

	private List<Map<String, Object>> categoryList; // 品类集合

	public List<Map<String, Object>> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Map<String, Object>> categoryList) {
		this.categoryList = categoryList;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public List<String> getImgslist() {
		return imgslist;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public void setImgslist(List<String> imgslist) {
		this.imgslist = imgslist;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Double getTopPrice() {
		return topPrice;
	}

	public void setTopPrice(Double topPrice) {
		this.topPrice = topPrice;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(Double activityPrice) {
		this.activityPrice = activityPrice;
	}
	
	public String getCreateTimeHandle(){
		if(this.getCreateDate()!=null){
			return DateUtil.formatDateTime(createDate);
		}
		return "";
	}

	public String getSpImg() {
		return spImg;
	}

	public void setSpImg(String spImg) {
		this.spImg = spImg;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
