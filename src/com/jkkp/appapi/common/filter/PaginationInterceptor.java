package com.jkkp.appapi.common.filter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;    

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;    
import org.apache.ibatis.mapping.BoundSql;    
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;    
import org.apache.ibatis.plugin.Intercepts;    
import org.apache.ibatis.plugin.Invocation;    
import org.apache.ibatis.plugin.Plugin;    
import org.apache.ibatis.plugin.Signature;    
import org.apache.ibatis.reflection.MetaObject;    
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jkkp.modules.member.model.Topic;
import com.jkkp.modules.member.model.VTopic;

/**
 * 分页拦截器
 * @since 10.20.2014
 */
   
public class PaginationInterceptor {
	//每页大小
	private static Integer pageSize=20;

	public static Map<String, Object> pagination(Map<String, Object> map){

		try{
			String curpage=(String) map.get("curpage");
			if(map.get("pageSize")!=null){
				Integer pageSizeParam=Integer.valueOf(map.get("pageSize").toString());
				if(pageSizeParam>0)
					pageSize=pageSizeParam;
			}
			if(curpage!=null&&!curpage.isEmpty()){
				Integer pageNum=Integer.valueOf(curpage);
				if (pageNum>=1)
					pageNum=(pageNum-1)*pageSize;
				map.put("pageSize", pageSize);
				map.put("curpage", pageNum.toString());
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			return map;
		}
		

	}
	
	public static Map<String, Object> nextPagination(Map<String, Object> map){

		try{
			Integer curpage = 0;
			if(map.containsKey("curpage"))
				curpage = Integer.valueOf((String) map.get("curpage"));

			if(map.get("pageSize")!=null){
				Integer pageSizeParam=Integer.valueOf(map.get("pageSize").toString());
				if(pageSizeParam>0)
					pageSize=pageSizeParam;
			}
			Integer pageNum = curpage;
			if (pageNum >= 0)
				pageNum = pageNum+ pageSize;
			map.put("pageSize", pageSize);
			map.put("curpage", pageNum.toString());

			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			return map;
		}
		

	}
    
}
