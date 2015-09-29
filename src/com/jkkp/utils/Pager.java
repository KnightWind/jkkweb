package com.jkkp.utils;
import org.apache.commons.lang.StringUtils;
import net.sf.json.JSONObject;
/**
 * 分页组件
 * @author ysc
 */
public class Pager {
	public String value="";//查询条件
	public long count=0;
	public long pageNo=1,curPage=1,pageCount=1;
	public int pageSize=5,limit=5;
	public int rowStart=0,rowEnd=0,sidx=0,eidx=0;
	
	public JSONObject pagination=new JSONObject();
	public Pager() {
	}
	public Pager(String and) {
		this.value = and;
	}
	public Pager(String and, int pageSize, int pageNo) {
		this.value = and;
		this.pageNo = pageNo;
		this.pageSize = pageSize;this.limit = pageSize;
		this.sidx=(int)((this.pageNo-1)*(this.pageSize));
		this.eidx=(int)(this.pageNo*this.pageSize);
		this.rowStart=this.sidx;this.rowEnd=this.eidx;
	}
	public Pager(String and, int pageSize, int pageNo,long count) {
		this(and, pageSize, pageNo);
		this.count=count;
		this.pageCount=getPageCount(this.pageSize,this.count);
		if(pageNo>pageCount) 	this.pageNo = this.pageCount;
		else if(pageNo < 1) 	this.pageNo = 1;
		this.sidx=(int)((this.pageNo-1)*(this.pageSize));
		this.eidx=(int)(this.pageNo*this.pageSize);
		this.rowStart=this.sidx;
		this.rowEnd=this.eidx;
		pagination.put("hasnext", this.pageNo<this.pageCount);
		pagination.put("currentPage", this.pageNo);
	}
	public Pager(String and, int pageSize, int pageNo,long count,String noPage) {
		this(and,pageSize,pageNo,count);
		if("1".equals(noPage)){
			this.pageNo=1;this.pageSize=(int)count;this.pageCount=1;
		}
		pagination.put("hasnext", this.pageNo<this.pageCount);
		pagination.put("currentPage", this.pageNo);
	}
	public Pager(String and,JSONObject jobj,long count){
		Object noPage=jobj.get("noPage");
		if(noPage!=null){//不分页
			boolean flag="1".equals(noPage);
			flag=flag||(BaseTools.isNumber(noPage.toString())&&Integer.parseInt(noPage.toString())==1);
			if(flag){
				jobj.put("pageSize",count);
				jobj.put("pageNo",1);jobj.put("curPage",1);
			}
		}
		Object obj=jobj.get("pageSize");
		if(obj!=null&&StringUtils.isNotBlank(obj.toString().trim())){
			this.pageSize=Integer.parseInt(obj.toString().trim());
			this.limit = this.pageSize;
		}
		Object pageNo_obj=jobj.get("pageNo");
		Object curPage_obj=jobj.get("curPage");
		if(curPage_obj!=null&&StringUtils.isNotBlank(curPage_obj.toString().trim())){
			this.pageNo=Integer.parseInt(curPage_obj.toString().trim());
		}else if(pageNo_obj!=null&&StringUtils.isNotBlank(pageNo_obj.toString().trim())){
			this.pageNo=Integer.parseInt(pageNo_obj.toString().trim());
		}
		this.value=and;this.count=count;
		this.pageCount=getPageCount(this.pageSize,this.count);
		if(pageNo>pageCount) 	this.pageNo = this.pageCount;
		else if(pageNo < 1) 	this.pageNo = 1;
		this.sidx=(int)((this.pageNo-1)*(this.pageSize));
		this.eidx=(int)(this.pageNo*this.pageSize);
		this.rowStart=this.sidx;
		this.rowEnd=this.eidx;
		this.curPage=this.pageNo;
		pagination.put("hasnext", this.pageNo<this.pageCount);
		pagination.put("currentPage",this.pageNo);
	}
	//分页页数。
	public long getPageCount(int pageSize,long count) {	
		long pageCount=1;if (pageSize > 0 && count > 0){
			if(count%pageSize>0) pageCount=count/pageSize+1;
			else pageCount=count/pageSize;
		}if(pageCount==0) pageCount=1;
		return pageCount;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getPageNo() {
		return pageNo;
	}
	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowStart() {
		return rowStart;
	}
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	public int getRowEnd() {
		return rowEnd;
	}
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}
	public int getSidx() {
		return sidx;
	}
	public void setSidx(int sidx) {
		this.sidx = sidx;
	}
	public int getEidx() {
		return eidx;
	}
	public void setEidx(int eidx) {
		this.eidx = eidx;
	}
	
	public String toString() {
		return "Pager [value=" + value + ", count=" + count + ", pageNo="
				+ pageNo + ", pageCount=" + pageCount + ", pageSize="
				+ pageSize + ", limit=" + limit + ", rowStart=" + rowStart
				+ ", rowEnd=" + rowEnd + ", sidx=" + sidx + ", eidx=" + eidx
				+ "]";
	}
	
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	public JSONObject getPagination() {
		return pagination;
	}
	public void setPagination(JSONObject pagination) {
		this.pagination = pagination;
	}
	
}
