package com.jkkp.common.response;

import java.util.List;

import com.jkkp.utils.Pagination;

@SuppressWarnings("unchecked")
public class ResponsePagination {

	private String code;
	private String msg;
	private PageResult<Object> result;

	public ResponsePagination(boolean success, String msg) {
		this.code = success ? "0" : "1";
		this.msg = msg;
	}

	public ResponsePagination() {
	}

	public <T> ResponsePagination(Pagination<T> page) {
		this.code = "0";
		this.msg = "";
		this.result = new PageResult<Object>();
		this.result.setCount(page.getTotal());
		this.result.setCurPage(page.getPageNumber());
		this.result.setElements((List<Object>) page.getDataList());
		this.result.setPageCount(page.getPages());
		this.result.setPageSize(page.getLimit());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PageResult<Object> getResult() {
		return result;
	}

	public void setResult(PageResult<Object> result) {
		this.result = result;
	}

	public class PageResult<T> {
		private Long count;
		private Integer curPage;
		private Integer pageCount;
		private Integer pageSize;
		private List<T> elements;

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}

		public Integer getCurPage() {
			return curPage;
		}

		public void setCurPage(Integer curPage) {
			this.curPage = curPage;
		}

		public Integer getPageCount() {
			return pageCount;
		}

		public void setPageCount(Integer pageCount) {
			this.pageCount = pageCount;
		}

		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

		public List<T> getElements() {
			return elements;
		}

		public void setElements(List<T> elements) {
			this.elements = elements;
		}

	}
}

