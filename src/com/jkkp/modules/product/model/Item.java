package com.jkkp.modules.product.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "item")
public class Item {
    /**
     * 商品id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 二级标题
     */
    @Column(name = "sub_title")
    private String subTitle;

    /**
     * 商品售价
     */
    private Double price;

    /**
     * 上架:1上架，0不上架
     */
    @Column(name = "start_sell")
    private Boolean startSell;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 品牌id
     */
    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * 分类id
     */
    private Integer cate;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 0正常-1删除
     */
    private Integer status;

    /**
     * 是否被冻结1冻结0正常
     */
    @Column(name = "is_lock")
    private Integer isLock;

    /**
     * 销售量（付款完成，数量+1）
     */
    @Column(name = "sales_num")
    private Integer salesNum;

    /**
     * 关注数
     */
    @Column(name = "follow_num")
    private Integer followNum;

    /**
     * 库存总量
     */
    private Integer stock;

    /**
     * 封面图片
     */
    private String pid;

    /**
     * 快照id（添加或编辑商品时产生）
     */
    @Column(name = "snapshot_id")
    private Integer snapshotId;

    /**
     * 所在城市域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 是否是零元购商品：0否1是
     */
    @Column(name = "is_free")
    private Integer isFree;

    @Column(name = "sku_data")
    private String skuData;

    @Column(name = "rel_id")
    private Integer relId;

    @Column(name = "sku_1")
    private Integer sku1;

    @Column(name = "sku_2")
    private Integer sku2;
    @Column(name = "zc_flag")
    private Boolean zcFlag;
    
    private String model;
    
    public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getZcFlag() {
		return zcFlag;
	}

	public void setZcFlag(Boolean zcFlag) {
		this.zcFlag = zcFlag;
	}
    /**
     * 商品描述
     */
    private String detail;

    /**
     * 获取商品id
     *
     * @return id - 商品id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置商品id
     *
     * @param id 商品id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     *
     * @return title - 商品名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商品名称
     *
     * @param title 商品名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取二级标题
     *
     * @return sub_title - 二级标题
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 设置二级标题
     *
     * @param subTitle 二级标题
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * 获取商品售价
     *
     * @return price - 商品售价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置商品售价
     *
     * @param price 商品售价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取上架:1上架，0不上架
     *
     * @return start_sell - 上架:1上架，0不上架
     */
    public Boolean getStartSell() {
        return startSell;
    }

    /**
     * 设置上架:1上架，0不上架
     *
     * @param startSell 上架:1上架，0不上架
     */
    public void setStartSell(Boolean startSell) {
        this.startSell = startSell;
    }
    public String getShang(){
    	if(this.startSell!=null){
    		if(this.startSell==false){
    			return "上架";
    		}else {
				return "不上架";
			}
    	}
    	return "";
    }
    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    public String getUp() {
 		return updateTime == null ? "" : DateUtil.formatDateTime(updateTime);
 	}
    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取品牌id
     *
     * @return brand_id - 品牌id
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * 设置品牌id
     *
     * @param brandId 品牌id
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取分类id
     *
     * @return cate - 分类id
     */
    public Integer getCate() {
        return cate;
    }

    /**
     * 设置分类id
     *
     * @param cate 分类id
     */
    public void setCate(Integer cate) {
        this.cate = cate;
    }

    /**
     * 获取商家id
     *
     * @return sp_id - 商家id
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * 设置商家id
     *
     * @param spId 商家id
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * 获取0正常-1删除
     *
     * @return status - 0正常-1删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0正常-1删除
     *
     * @param status 0正常-1删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否被冻结1冻结0正常
     *
     * @return is_lock - 是否被冻结1冻结0正常
     */
    public Integer getIsLock() {
        return isLock;
    }

    /**
     * 设置是否被冻结1冻结0正常
     *
     * @param isLock 是否被冻结1冻结0正常
     */
    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }
    public String getDong(){
    	if(this.isLock!=null){
    		if(this.isLock==1){
    			return "冻结";
    		}else {
				return "正常";
			}
    	}
    	return "";
    }
    /**
     * 获取销售量（付款完成，数量+1）
     *
     * @return sales_num - 销售量（付款完成，数量+1）
     */
    public Integer getSalesNum() {
        return salesNum;
    }

    /**
     * 设置销售量（付款完成，数量+1）
     *
     * @param salesNum 销售量（付款完成，数量+1）
     */
    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    /**
     * 获取关注数
     *
     * @return follow_num - 关注数
     */
    public Integer getFollowNum() {
        return followNum;
    }

    /**
     * 设置关注数
     *
     * @param followNum 关注数
     */
    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    /**
     * 获取库存总量
     *
     * @return stock - 库存总量
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置库存总量
     *
     * @param stock 库存总量
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取封面图片
     *
     * @return pid - 封面图片
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置封面图片
     *
     * @param pid 封面图片
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取快照id（添加或编辑商品时产生）
     *
     * @return snapshot_id - 快照id（添加或编辑商品时产生）
     */
    public Integer getSnapshotId() {
        return snapshotId;
    }

    /**
     * 设置快照id（添加或编辑商品时产生）
     *
     * @param snapshotId 快照id（添加或编辑商品时产生）
     */
    public void setSnapshotId(Integer snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * 获取所在城市域名
     *
     * @return city_domain - 所在城市域名
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置所在城市域名
     *
     * @param cityDomain 所在城市域名
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
    }

    /**
     * 获取是否是零元购商品：0否1是
     *
     * @return is_free - 是否是零元购商品：0否1是
     */
    public Integer getIsFree() {
        return isFree;
    }

    /**
     * 设置是否是零元购商品：0否1是
     *
     * @param isFree 是否是零元购商品：0否1是
     */
    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    /**
     * @return sku_data
     */
    public String getSkuData() {
        return skuData;
    }

    /**
     * @param skuData
     */
    public void setSkuData(String skuData) {
        this.skuData = skuData;
    }
    /**
     * @return rel_id
     */
    public Integer getRelId() {
        return relId;
    }

    /**
     * @param relId
     */
    public void setRelId(Integer relId) {
        this.relId = relId;
    }

    /**
     * @return sku_1
     */
    public Integer getSku1() {
        return sku1;
    }

    /**
     * @param sku1
     */
    public void setSku1(Integer sku1) {
        this.sku1 = sku1;
    }

    /**
     * @return sku_2
     */
    public Integer getSku2() {
        return sku2;
    }

    /**
     * @param sku2
     */
    public void setSku2(Integer sku2) {
        this.sku2 = sku2;
    }

    /**
     * 获取商品描述
     *
     * @return detail - 商品描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置商品描述
     *
     * @param detail 商品描述
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
   
	
}