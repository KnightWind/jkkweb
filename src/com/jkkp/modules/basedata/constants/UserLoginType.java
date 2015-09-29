package com.jkkp.modules.basedata.constants;


/**
 * 用户登录类型    以后会添加建材商，等其他类型用户，方便修改
 * @author zhangfg
 *
 */
public class UserLoginType {
	
	//private final static Map<Integer,String> map = new HashMap<Integer, String>();
	
	/**
	 * 装修公司
	 */
	public final static int RenovationCompany_TYPE 			 = 1;
	/**
	 * 建材商
	 */
	public final static int BuildingMaterialsFactory_TYPE = 2;
	/**
	 * 监理
	 */
	public final static int Supervisor_TYPE  		     	 = 5;
	
//	static{
//		map.put(1, RenovationCompany_TYPE);
//		map.put(5, Supervisor_TYPE);
//	}
//	
//	public static String getLoginUrl(int type){
//		return map.get(type);
//	}
//	
	
	/**
	public static String returnPageUrl(HttpServletRequest request) {
		Map<String,String[]> params = request.getParameterMap();
		Set<String> keys = params.keySet();
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			sb.append("&"+key+"=");
			String []pars = params.get(key);
			for (String val : pars) {
				sb.append(val);
			}
		}
		String path = request.getRequestURI();
		String paramsStr = sb.toString().replaceFirst("&", "?");
		String url = Escape.escape(path+paramsStr);
		return url;
	}
	*/
}
