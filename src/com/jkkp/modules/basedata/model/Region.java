package com.jkkp.modules.basedata.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "region")
public class Region {
    /**
     * 区域id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regionid;

    /**
     * 上一级id
     */
    private Integer parentid;

    /**
     * 区域名称
     */
    private String regionname;

    /**
     * 区域等级
     */
    private Integer level;

    /**
     * 排序顺序
     */
    private Integer sortby;
    /**
     * 是否开通
     */
    private Integer status;
    /**
     * 经度
     **/
    private String pointx;
    /**
     * 纬度
     **/
    private String pointy;

    /**
     * 获取区域id
     *
     * @return regionid - 区域id
     **/
    public Integer getRegionid() {
        return regionid;
    }

    /**
     * 设置区域id
     *
     * @param regionid 区域id
     */
    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    /**
     * 获取上一级id
     *
     * @return parentid - 上一级id
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * 设置上一级id
     *
     * @param parentid 上一级id
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取区域名称
     *
     * @return regionname - 区域名称
     */
    public String getRegionname() {
        return regionname;
    }

    /**
     * 设置区域名称
     *
     * @param regionname 区域名称
     */
    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    /**
     * 获取区域等级
     *
     * @return level - 区域等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置区域等级
     *
     * @param level 区域等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取排序顺序
     *
     * @return sortby - 排序顺序
     */
    public Integer getSortby() {
        return sortby;
    }

    /**
     * 设置排序顺序
     *
     * @param sortby 排序顺序
     */
    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPointx() {
		return pointx;
	}

	public void setPointx(String pointx) {
		this.pointx = pointx;
	}

	public String getPointy() {
		return pointy;
	}

	public void setPointy(String pointy) {
		this.pointy = pointy;
	}
    
	public String getOperateName() {
		if (this.status == null) 
			return "开通";
		 if(this.status == 1)
			return "下线";
		return "开通";
	}
	
	public String getStatusName() {
		if(this.status != null){
			if(this.status == 1)
				return "已开通";
			if(this.status == 0)
				return "未开通";
		}
		return "未开通";
	}
}