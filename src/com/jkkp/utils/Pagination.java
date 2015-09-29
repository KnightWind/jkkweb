package com.jkkp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.github.abel533.mapper.Mapper;
import com.jkkp.common.IService;

@SuppressWarnings("rawtypes")
public class Pagination<T> {

	private static ThreadLocal<IService> PROCESS_SERVICE = new ThreadLocal<IService>();
	private static ThreadLocal<Object> SEARCH_ENTITY = new ThreadLocal<Object>();
	private static ThreadLocal<Map<String, Object>> SEARCH_PARAMS = new ThreadLocal<Map<String, Object>>();

	private static ThreadLocal<Boolean> TRANSLATE_TO_VIEW = new ThreadLocal<Boolean>();

	private static ThreadLocal<Map<String,Object>> PAGE_PARAMS = new ThreadLocal<Map<String,Object>>();
	
	public static final int DEFAULT_PAGENUM = 1;
	public static final int DEFAULT_LIMIT = 10;

	private List<?> dataList; // 对象记录结果集
	private long total = 0; // 总记录数
	private int limit = 10; // 每页显示记录数
	private int pageNumber = 1; // 当前页

	private int navigatePages = 5; 	// 导航页码数
	private int[] navigatePageNumbers; // 所有导航页号
	private List<Map<String, String>> order;

	private T searchBean;

	public Pagination() {

	}

	public Pagination(T searchBean, int limit, int pageNumber) {
		this.searchBean = searchBean;
		this.limit = limit;

		int pages = this.getPages();
		if (pageNumber < 1) {
			this.pageNumber = 1;
		} else if (pageNumber > pages) {
			this.pageNumber = pages;
		} else {
			this.pageNumber = pageNumber;
		}
		this.pageNumber = pageNumber;
	}

	public int getPages() {
		return (int) ((this.total - 1) / this.limit + 1);
	}

	/**
	 * 计算导航页
	 */
	public void calcNavigatePageNumbers() {
		// 当总页数小于或等于导航页码数时
		int pages = this.getPages();
		if (pages <= navigatePages) {
			navigatePageNumbers = new int[pages];
			for (int i = 0; i < pages; i++) {
				navigatePageNumbers[i] = i + 1;
			}
		} else { // 当总页数大于导航页码数时
			navigatePageNumbers = new int[navigatePages];
			int startNum = pageNumber - navigatePages / 2;
			int endNum = pageNumber + navigatePages / 2;

			if (startNum < 1) {
				startNum = 1;
				// (最前navPageCount页
				for (int i = 0; i < navigatePages; i++) {
					navigatePageNumbers[i] = startNum++;
				}
			} else if (endNum > pages) {
				endNum = pages;
				// 最后navPageCount页
				for (int i = navigatePages - 1; i >= 0; i--) {
					navigatePageNumbers[i] = endNum--;
				}
			} else {
				// 所有中间页
				for (int i = 0; i < navigatePages; i++) {
					navigatePageNumbers[i] = startNum++;
				}
			}
		}
	}

	/**
	 * 是否为第一页
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return pageNumber == 1;
	}

	/**
	 * 是否为最后一页
	 * 
	 * @return
	 */
	public boolean isLastPage() {
		return pageNumber == this.getPages() && pageNumber != 1;
	}

	/**
	 * 是否有前一页
	 * 
	 * @return
	 */
	public boolean isHasPreviousPage() {
		return pageNumber != 1;
	}

	/**
	 * 是否有下一页
	 */
	public boolean isHasNextPage() {
		return pageNumber != this.getPages();
	}

	/**
	 * 得到记录总数
	 * 
	 * @return {long}
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 得到每页显示多少条记录
	 * 
	 * @return {int}
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * 得到当前页号
	 * 
	 * @return {int}
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	public int getFirstResult() {
		return (pageNumber - 1) * limit;
	}

	/**
	 * 得到所有导航页号
	 * 
	 * @return {int[]}
	 */
	public int[] getNavigatePageNumbers() {
		return navigatePageNumbers;
	}

	public List<Map<String, String>> getOrder() {
		return order;
	}

	public void addOrder(final String property, final String orderBy) {
		if (order == null) {
			order = new ArrayList<Map<String, String>>();
		}
		order.add(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(property, orderBy);
			}
		});
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public T getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(T searchBean) {
		this.searchBean = searchBean;
	}

	public static <T> Pagination<T> newInstance(int pageNumber, List<T> dataList, long total, int limit) {
		Pagination<T> pagination = new Pagination<T>();
		pagination.setPageNumber(pageNumber);
		pagination.setDataList(dataList);
		pagination.setTotal(total);
		pagination.setLimit(limit);
		pagination.calcNavigatePageNumbers();
		return pagination;
	}

	public static void setContext(IService service, Object searchBean) {
		PROCESS_SERVICE.set(service);
		SEARCH_ENTITY.set(searchBean);
	}

	public static void setContext(IService service) {
		PROCESS_SERVICE.set(service);
	}

	public static void clear() {
		PROCESS_SERVICE.remove();
		SEARCH_ENTITY.remove();
	}

	public static IService getProcessService() {
		return PROCESS_SERVICE.get();
	}

	public static Object getSearchEntity() {
		return SEARCH_ENTITY.get();
	}

	public static void setIsConvert() {
		TRANSLATE_TO_VIEW.set(true);
	}

	public static void clearConvert() {
		TRANSLATE_TO_VIEW.remove();
	}

	public static boolean isConvert() {
		Boolean value = TRANSLATE_TO_VIEW.get();
		return value != null && value;
	}

	public static void setSearchParams(HttpServletRequest request) {
		SEARCH_PARAMS.set(RequestUtils.genParamMap(request));
	}
	
	public static void setSearchParams(Map<String, Object> params) {
		Map<String, Object> data = SEARCH_PARAMS.get();
		if (data != null) {
			data.putAll(params);
		} else {
			SEARCH_PARAMS.set(params);
		}
	}
	public static Map<String, Object> getSearchParams() {
		return SEARCH_PARAMS.get();
	}
	public static void clearSearchParams() {
		SEARCH_PARAMS.remove();
	}
	public static void setPageParams(HttpServletRequest request, Mapper mapper, String dataStatement,
			String countStatement) {
		Map<String, Object> pageParams = new HashMap<String, Object>();
		pageParams.put("mapper", mapper);
		pageParams.put("dataStatement", dataStatement);
		pageParams.put("countStatement", countStatement);

		int pageNum = CommonUtil.isNull(CommonUtil.stringToInteger(request.getParameter("pageNum")), 1);
		int limit = CommonUtil.isNull(CommonUtil.stringToInteger(request.getParameter("pageSize")), DEFAULT_LIMIT);
		Map<String, Object> data = RequestUtils.genParamMap(request);
		data.putAll(RequestUtils.genParamMap(request, "search"));
		data.put("pageNum", pageNum);
		data.put("limit", limit);
		data.put("rowStart", (pageNum - 1) * limit);
		pageParams.put("params", data);

		PAGE_PARAMS.set(pageParams);
	}
	
	public static Map<String, Object> getPageParams() {
		return PAGE_PARAMS.get();
	}
	
	public static void clearPageParams() {
		PAGE_PARAMS.remove();
	}
}
