package com.jkkp.common.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jkkp.common.response.ResponsePagination;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.JsonUtil;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.RequestUtils;

@SuppressWarnings(value = { "unchecked" })
public class PaginationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		Method method = ((HandlerMethod) handler).getMethod();
		AccessPagination annotation = method.getAnnotation(AccessPagination.class);
		if (annotation != null && annotation.custom()) {
			Map<String, Object> params = RequestUtils.genParamMap(request);
			params.put("rowStart", 0);
			params.put("limit", Pagination.DEFAULT_LIMIT);
			Pagination.setSearchParams(params);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return;
		}
		Method method = ((HandlerMethod) handler).getMethod();
		AccessPagination annotation = method.getAnnotation(AccessPagination.class);
		if (annotation != null) {
			int pageNum = CommonUtil.isNull(CommonUtil.stringToInteger(request.getParameter("pageNum")), 1);
			int limit = CommonUtil.isNull(CommonUtil.stringToInteger(request.getParameter("pageSize")),
					Pagination.DEFAULT_LIMIT);
			if (annotation.async() == AccessPagination.ASYNC.ASYNC_YES) {
				ResponsePagination pagination;
				if (!annotation.custom()) {
					try {
						pagination = new ResponsePagination(Pagination.getProcessService().findPagination(
								Pagination.getSearchEntity(), pageNum, limit));
					} catch (Exception e) {
						e.printStackTrace();
						pagination = new ResponsePagination(false, "查询分页出现异常");
					}
				} else {
					Map<String, Object> params = RequestUtils.genParamMap(request, "search");
					params.put("rowStart", (pageNum - 1) * limit);
					params.put("limit", limit);
					Pagination.setSearchParams(params);
					pagination = new ResponsePagination(Pagination.getProcessService().pagination(pageNum, limit));
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/json;charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter out = response.getWriter();
				out.print(JsonUtil.objectToJson(pagination));
				out.flush();
				out.close();
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception excptn)
			throws Exception {
		Pagination.clear();
		Pagination.clearSearchParams();
	}
}
