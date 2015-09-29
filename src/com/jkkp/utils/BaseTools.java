package com.jkkp.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.*;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.SystemOutLogger;

import com.jkkp.pc.main.controller.mainController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 基础工具类
 * 
 * @author ysc
 *
 */
@SuppressWarnings("unchecked")
public class BaseTools {
	public static final String preFix = "--";
	public static final String lineEnd = "\r\n";
	public static String enter = " \n", tab = "\t";
	public static SimpleDateFormat pkt = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat fmt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dmt = new SimpleDateFormat("yyyy-MM-dd");
	public static NumberFormat df4 = new DecimalFormat("0000");
	public static final int timeOut = 1000 * 60 * 60 * 24;

	public static String doPostByUrl(String urlPath, String encode,
			JSONObject mp, JSONObject fs) {
		return doPostByUrl(urlPath, encode, mp, fs, null);
	}

	/**
	 * @param urlPath请求地址
	 * @param encode编码格式
	 * @param mp字段参数
	 * @param fs附件参数
	 * @param pm代理参数
	 *            --proxySet true：使用代理 --proxyHost 代理服务器的地址 --proxyPort 代理服务器的端口
	 *            --proxyUser 代理服务器的帐号 --proxyPassword 代理服务器的密码
	 * @return 返回请求内容
	 */
	public static String doPostByUrl(String urlPath, String encode,
			JSONObject mp, JSONObject fs, JSONObject pm) {
		StringBuffer rs = new StringBuffer("");
		String uuid = UUID.randomUUID().toString();// 边界标识 随机生成
		uuid = uuid.toUpperCase().replaceAll("-", "");
		String CONTENT_TYPE = "multipart/form-data"; // 内容类型
		BufferedReader br = null;
		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		try {
			URL url = new URL(urlPath);
			conn = (HttpURLConnection) url.openConnection();
			if (pm != null && pm.get("proxySet") != null
					&& pm.get("proxySet").equals("true")) {// 通过代理上网
				Properties pt = System.getProperties();
				pt.put("proxySet", "true");
				pt.put("proxyHost", pm.get("proxyHost"));
				pt.put("proxyPort", pm.get("proxyPort"));
				if (pm.get("proxyUser") != null)
					pt.put("proxyUser", pm.get("proxyUser"));
				if (pm.get("proxyPassword") != null)
					pt.put("proxyPassword", pm.get("proxyPassword"));
			}
			conn.setReadTimeout(timeOut);
			conn.setConnectTimeout(timeOut);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", encode); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
					+ uuid);
			conn.connect();
			dos = new DataOutputStream(conn.getOutputStream());
			if (mp != null)
				addFormField(uuid, mp, dos, encode);
			if (fs != null)
				addFormFile(uuid, fs, dos, encode);
			byte[] end_data = (preFix + uuid + preFix + lineEnd).getBytes();
			dos.write(end_data);
			dos.flush();
			/**
			 * 获取响应码 200=成功 当响应成功，获取响应的流
			 */
			int code = conn.getResponseCode();
			if (code >= 200 && code < 500) {
				if (encode == null || encode.equals(""))
					br = new BufferedReader(new InputStreamReader(
							conn.getInputStream()));
				else
					br = new BufferedReader(new InputStreamReader(
							conn.getInputStream(), encode));
				String line = null;
				while ((line = br.readLine()) != null) {
					rs.append(line + "\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
				if (br != null) {
					br.close();
					br = null;
				}
				if (dos != null) {
					dos.close();
					dos = null;
				}
			} catch (IOException e2) {
				pln("getHtmlByUrl关闭异常：" + e2.getMessage());
			}
		}
		return rs.toString().trim();
	}

	private static void addFormField(String uuid, JSONObject mp,
			DataOutputStream dos, String encode) {
		try {
			StringBuilder sb = new StringBuilder("");
			for (Object key : mp.keySet()) {
				Object value = mp.get(key);
				sb.append(preFix + uuid + lineEnd); // 前缀+分隔线+结尾
				sb.append("Content-Disposition:form-data;name=\"" + key + "\""
						+ lineEnd); // 属性名+结尾
				sb.append(lineEnd + value + lineEnd); // 结尾+属性值+结尾
			}
			dos.write(sb.toString().getBytes(encode)); // 写入表单字段数据
		} catch (Exception e) {
			pln(".addFormField.异常:" + e.getMessage());
		}
	}

	/**
	 * @param uuid
	 *            分隔符
	 * @param fs
	 *            HashMap:key=文件名称 value=文件类型
	 * @param dos
	 *            输出流
	 * @param encode
	 *            编码格式
	 * @return
	 */
	private static String addFormFile(String uuid, JSONObject fs,
			DataOutputStream dos, String encode) {
		if (fs == null)
			return "文件参数为空,不能上传附件";
		int ii = 0;
		try {
			for (Object key : fs.keySet()) {
				Object value = fs.get(key);
				if (value == null)
					continue;
				byte[] bb = null;
				if (value instanceof String) {
					String path = value.toString();
					if (path.startsWith("http")) { // 1.当value是URL地址
						bb = getByteByUrlPath(path);
					} else { // 2.当value是文件地址
						File ff = new File(path);
						if (ff.exists() == false)
							continue;
						bb = getByteByFilePath(path);
					}
				} else if (value instanceof File) { // 3.当value是文件
					File ff = (File) value;
					if (ff.exists() == false)
						continue;
					bb = getByteByFile(ff);
					// }else if(value instanceof Bitmap){ //4.当value是拍照图片
					// bb=getByteByBitmap((Bitmap)value);
				} else if (value instanceof byte[]) { // 5.当value二进制流
					bb = (byte[]) value;
				} else
					continue;
				String ss = preFix + uuid + lineEnd;
				ss += "Content-Type:application/octet-stream;charset=" + encode
						+ lineEnd;
				ss += "Content-Disposition:form-data;name=\"myfile" + (ii++)
						+ "\";filename=\"" + key + "\"" + lineEnd;
				dos.write(ss.getBytes(encode));
				dos.write(lineEnd.getBytes(encode));
				dos.write(bb);
				dos.write(lineEnd.getBytes(encode));
			}
		} catch (Exception e) {
			pln("异常：" + e.getMessage());
		}
		return "上传成功";
	}

	public static byte[] getByteByUrlPath(String path) {
		byte[] bb = null;
		try {
			URL url = new URL(path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(timeOut);
			con.setRequestMethod("POST");
			if (con.getResponseCode() == 200) {
				InputStream is = con.getInputStream();
				bb = getByteByInputStream(is);
				is.close();
			}
		} catch (Exception e) {
			pln("getByteByUrlPath异常：" + e.getMessage());
		}
		return bb;
	}

	public static byte[] getByteByInputStream(InputStream is) {
		byte[] bb = new byte[1024];
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			while ((len = is.read(bb)) != -1) {
				bos.write(bb, 0, len);
			}
			bb = bos.toByteArray();
			bos.flush();
			bos.close();
		} catch (Exception e) {
			pln("getByteByInputStream异常：" + e.getMessage());
		}
		return bb;
	}

	public static byte[] getByteByFilePath(String path) {
		File ff = new File(path);
		return getByteByFile(ff);
	}

	public static byte[] getByteByFile(File ff) {
		byte[] bb = null;
		try {
			InputStream is = new FileInputStream(ff);
			bb = getByteByInputStream(is);
			is.close();
		} catch (Exception e) {
			pln("getByteByFile异常：" + e.getMessage());
		}
		return bb;
	}

	/**
	 * 拼装SQL查询条件 ysc
	 * 
	 * @param request
	 * @param pname
	 *            页面参数名称
	 * @param fname
	 *            数据库字段名称
	 * @param ctype
	 *            查询类型【1=不用URLDecode解密参数值，2=需要URLDecode解密参数值】
	 * @return
	 */
	public static String getAndByPname(HttpServletRequest request,
			String pname, String fname, String ctype) {
		return getAndByPname(request, pname, fname, ctype, null);
	}

	/**
	 * 拼装SQL查询条件 ysc
	 * 
	 * @param request
	 * @param pname
	 *            页面参数名称
	 * @param fname
	 *            数据库字段名称
	 * @param ctype
	 *            查询类型【1=不用URLDecode解密参数值，2=需要URLDecode解密参数值】
	 * @param iseq
	 *            是否精确查询【1=需要精确查询,其他值模糊查询】
	 * @return
	 */
	public static String getAndByPname(HttpServletRequest request,
			String pname, String fname, String ctype, String iseq) {
		String and = "", enter = " \n";
		if (pname == null || pname.equals(""))
			return and;
		try {
			String pvalue = request.getParameter(pname);
			if (pvalue == null || pvalue.equals(""))
				return and;
			if ("2".equals(ctype))
				pvalue = URLDecoder.decode(pvalue, "UTF-8").trim();
			if (fname == null || fname.equals(""))
				fname = pname;
			if (pname.startsWith("is")) {
				if ("1".equals(pvalue))
					and += " and " + fname + "='1' " + enter;
				else
					and += " and (" + fname + " is null or " + fname + "='0') "
							+ enter;
			} else {
				if (iseq == null || iseq.equals(""))
					iseq = request.getParameter(pname + "_iseq");
				if ("on".equals(iseq))
					iseq = "1";
				String[] st = pvalue.split(";");
				and += " and (";
				int ii = 0;
				for (int i = 0; i < st.length; i++) {
					String tt = st[i];
					if (tt == null || tt.equals(""))
						continue;
					if (ii > 0)
						and += " " + ("1".equals(iseq) ? "and" : "or") + " ";
					ii++;
					if ("null".equals(tt)) {
						and += fname + " is null ";
					} else {
						and += "lower(" + fname + ") ";
						if ("1".equals(iseq))
							and += " =lower('" + tt + "') ";
						else
							and += " like lower('%" + tt + "%') ";
					}
					if (i < st.length - 1)
						and += enter;
				}
				and += ")" + enter;
			}
		} catch (Exception e) {
			and = "";
		}
		return and;
	}

	/**
	 * 拼装SQL查询条件 ysc
	 * 
	 * @param request
	 * @param pname
	 *            页面参数名称
	 * @param fname
	 *            数据库字段名称
	 * @param ctype
	 *            查询类型【1=不用URLDecode解密参数值，2=需要URLDecode解密参数值】
	 * @return
	 */
	public static String getAndByJson(JSONObject jobj, String pname,
			String fname, String ctype) {
		return getAndByJson(jobj, pname, fname, ctype, null);
	}

	/**
	 * 拼装SQL查询条件 ysc
	 * 
	 * @param request
	 * @param pname
	 *            页面参数名称
	 * @param fname
	 *            数据库字段名称
	 * @param ctype
	 *            查询类型【1=不用URLDecode解密参数值，2=需要URLDecode解密参数值】
	 * @param iseq
	 *            是否精确查询【1=需要精确查询,其他值模糊查询】
	 * @return
	 */
	public static String getAndByJson(JSONObject jobj, String pname,
			String fname, String ctype, String iseq) {
		String and = "", enter = " \n";
		if (jobj == null || pname == null || pname.equals(""))
			return and;
		Object obj = jobj.get(pname);
		if (obj == null || obj.toString().trim().equals(""))
			return and;
		try {
			String pvalue = obj.toString().trim();
			if (pvalue == null || pvalue.equals(""))
				return and;
			if ("2".equals(ctype))
				pvalue = URLDecoder.decode(pvalue, "UTF-8").trim();
			if (fname == null || fname.equals(""))
				fname = pname;
			if (pname.startsWith("is")) {
				if ("1".equals(pvalue))
					and += " and " + fname + "='1' " + enter;
				else
					and += " and (" + fname + " is null or " + fname + "='0') "
							+ enter;
			} else {
				if (StringUtils.isBlank(iseq)) {
					obj = jobj.get(pname + "_iseq");
					if (obj != null)
						iseq = obj.toString().trim();
				}
				if (fname == null || fname.equals(""))
					fname = pname;
				if ("on".equals(iseq))
					iseq = "1";
				String[] st = pvalue.split(";");
				and += " and (";
				int ii = 0;
				for (int i = 0; i < st.length; i++) {
					String tt = st[i];
					if (tt == null || tt.equals(""))
						continue;
					if (ii > 0)
						and += " " + ("1".equals(iseq) ? "and" : "or") + " ";
					ii++;
					if ("null".equals(tt)) {
						and += fname + " is null ";
					} else if ("not null".equals(tt)) {
						and += fname + " is not null ";
					} else {
						and += "lower(" + fname + ") ";
						if ("1".equals(iseq))
							and += " =lower('" + tt + "') ";
						else
							and += " like lower('%" + tt + "%') ";
					}
					if (i < st.length - 1)
						and += enter;
				}
				and += ")" + enter;
			}
		} catch (Exception e) {
			and = "";
		}
		return and;
	}

	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();
		return ip;
	}

	public static void showMessageByMap(Map<String, Object> mp, String head) {
		showMessageByJSON(JSONObject.fromObject(mp), head);
	}

	public static void showMessageByJSON(JSONObject json, String head) {
		Iterator<String> it = json.keySet().iterator();
		int ii = 1;
		while (it.hasNext()) {
			String kk = it.next();
			Object vv = json.get(kk);
			System.out.println(head + (ii++) + ":" + "key【" + kk + "】" + vv
					+ "");
			if (vv == null)
				continue;
			if (vv instanceof JSONArray) {
				// JSONArray jlist=(JSONArray)vv;
				// for (int i = 0; i < jlist.size(); i++) {
				// JSONObject jobj=jlist.getJSONObject(i);
				// showMessageByJSON(jobj,head+"\t");
				// }
			} else if (vv instanceof JSONObject) {
				// showMessageByJSON((JSONObject)vv,head+"\t");
			}
		}
	}

	public static boolean isNumber(Object ss) {
		if (ss == null || ss.equals(""))
			return false;
		ss = ss.toString().trim().replaceAll("-", "");
		return ss.toString().trim().matches("\\d+(\\.\\d+)?");
	}

	public static String getFileType(String fname) {
		if (fname == null || fname.equals(""))
			return "";
		int mm = fname.lastIndexOf(".");
		if (mm == -1)
			return "";
		return fname.substring(mm + 1).toLowerCase();
	}

	// 生成UUID，可做主键。
	public static String createUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").toUpperCase();
	}

	// 生成时间主键：年月日时分秒+3位毫秒数+3位纳秒数+4位随机数
	public static String createTimePK() {
		String ss = createTime();
		BigInteger aa = new BigInteger(ss);
		return getPrintNoByBigInt(aa);
	}

	// 生成时间主键：年月日时分秒+3位毫秒数+3位纳秒数+4位随机数
	public static String createTime() {
		String time = pkt.format(new Date());
		String nano = (System.nanoTime() + "");
		nano = nano.substring(nano.length() - 6);
		int ran = new Random().nextInt(10000);
		String sran = df4.format(ran);
		return time + "" + nano + "" + sran + "";
	}

	public static String getPrintNoByBigInt(BigInteger no) {
		BigInteger ii = BigInteger.valueOf(32);
		String ss = "";
		int flag = no.compareTo(ii);
		if (flag > 0)
			ss = getPrintNoByBigInt(no.divide(ii));
		ss += getWordByBigNo(no.remainder(ii));// 取模【%】
		return ss;
	}

	public static String getWordByBigNo(BigInteger no) {
		Map<Object, String> map = new HashMap<Object, String>();
		for (int i = 0; i <= 9; i++)
			map.put(BigInteger.valueOf(i), i + "");
		int j = 10;
		for (int i = 10; i < 36; i++) {
			String cc = (char) (65 + i - 10) + "";
			if (cc.equals("O"))
				continue; // 36-1剩35(字母O和数字0相似，除O留0)
			if (cc.equals("L"))
				continue; // 35-1剩34(字母L和数字1相似，除L留1)
			if (cc.equals("I"))
				continue; // 34-1剩33(字母I和数字1相似，除I留1)
			if (cc.equals("Z"))
				continue; // 33-1剩32(字母Z和数字2相似，除Z留2)
			map.put(BigInteger.valueOf(j++), cc + "");// 5AB404UGNYUE7FDQ
		}
		return map.get(no);
	}

	public static String getNowTime() {
		return fmt.format(new Date());
	}

	public static String getNowDate() {
		return dmt.format(new Date());
	}

	public static String getValueByKey(JSONObject jobj, String key) {
		String ss = "";
		if (jobj == null || StringUtils.isBlank(key))
			return ss;
		Object obj = jobj.get(key);
		if (obj == null)
			return ss;
		return obj.toString();
	}

	public static Integer getIntByKey(JSONObject jobj, String key) {
		Integer ss = null;
		if (jobj == null || StringUtils.isBlank(key))
			return ss;
		Object obj = jobj.get(key);
		if (obj == null)
			return ss;
		if (obj instanceof Integer)
			ss = ((Integer) obj).intValue();
		return ss;
	}

	// 正常输出
	public static void p(Object obj) {
		System.out.print(obj);
	}

	// 正常输出
	public static void pln(Object obj) {
		System.out.println(obj);
	}

	// 异常输出
	public static void epln(Object obj) {
		System.err.println(obj);
	}

	/**
	 * 实体复制工具类 相同属性复制
	 * 
	 * @param Object
	 *            源实体
	 * @param Object
	 *            被修改实体
	 */
	public static void CopyBean(Object source, Object dest) throws Exception {
		// 获取属性
		BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),
				java.lang.Object.class);
		PropertyDescriptor[] sourceProperty = sourceBean
				.getPropertyDescriptors();

		BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(),
				java.lang.Object.class);
		PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

		try {
			for (int i = 0; i < sourceProperty.length; i++) {

				for (int j = 0; j < destProperty.length; j++) {

					if (sourceProperty[i].getName().equals(
							destProperty[j].getName())) {
						// 调用source的getter方法和dest的setter方法
						destProperty[j].getWriteMethod().invoke(
								dest,
								sourceProperty[i].getReadMethod()
										.invoke(source));
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("属性复制失败:" + e.getMessage());
		}
	}

	/**
	 * 非空属性复制至目标
	 * 
	 * @param Object
	 *            源实体
	 * @param Object
	 *            被修改实体
	 */
	public static void CopyBeanNotNull(Object source, Object dest)
			throws Exception {
		// 获取属性
		BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),
				java.lang.Object.class);
		PropertyDescriptor[] sourceProperty = sourceBean
				.getPropertyDescriptors();

		BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(),
				java.lang.Object.class);
		PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

		try {
			for (int i = 0; i < sourceProperty.length; i++) {

				for (int j = 0; j < destProperty.length; j++) {

					if (sourceProperty[i].getName().equals(
							destProperty[j].getName())) {
						// 调用source的getter方法和dest的setter方法
						if (sourceProperty[i].getReadMethod().invoke(source) != null) {
							destProperty[j].getWriteMethod().invoke(
									dest,
									sourceProperty[i].getReadMethod().invoke(
											source));
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("属性复制失败:" + e.getMessage());
		}
	}

	/**
	 * 实体复制工具类 相同属性复制
	 * 
	 * @param List
	 *            (Object) 源实体list
	 * @param Class
	 *            (T) 目标转换的类
	 * @return List(T) 复制后的结果
	 */
	public static <T> List<T> CopyBean(List<Object> list, Class<T> clz)
			throws Exception {
		List<T> rst = new ArrayList<>();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				T obj = null;
				obj = clz.newInstance();
				CopyBean(list.get(i), obj);
				rst.add(obj);
			}
		}
		return rst;
	}

	/**
	 * Map转Bean工具类 相同属性复制
	 * 
	 * @param Map
	 *            (String, Object) 源map
	 * @param Class
	 *            (T) Bean类型
	 * @return T 转换后的Bean
	 */
	public static <T> T converter(Map<String, Object> map, Class<T> clz)
			throws Exception {
		T obj = null;
		try {
			obj = clz.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(clz);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					// setter.invoke(obj, value);
					Class<?> clazz = property.getPropertyType();
					String type = clazz.getName();
					if (type.equals("java.lang.String")) {
						setter.invoke(obj, value);
					} else if (type.equals("java.util.Date")) {
						// setter.invoke(obj,(Date) value);
						setter.invoke(obj,
								new Date(Long.valueOf(value.toString())));
					} else if (type.equals("java.lang.Integer")) {
						setter.invoke(obj, Integer.valueOf(value.toString()));
					} else {
						// ........
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("map转bean异常:" + e.getMessage());
		}
		return obj;
	}

	/**
	 * List(Map)转Bean工具类 相同属性复制
	 * 
	 * @param List
	 *            (Map(String, Object)) 源map
	 * @param Class
	 *            (T) Bean类型
	 * @return List(T) 转换后的Bean
	 */
	public static <T> List<T> converter(List<Map<String, Object>> list,
			Class<T> clz) throws Exception {
		List<T> rst = new ArrayList<>();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				rst.add(converter(list.get(i), clz));
			}
		}
		return rst;
	}
}
