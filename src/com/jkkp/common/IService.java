package com.jkkp.common;

import java.util.List;
import java.util.Map;

import com.jkkp.utils.Pagination;

public interface IService<T, V, PK> {

	public void save(T entity);

	public void update(T entity);

	public T findById(PK id);

	public void deleteById(PK id);

	public void delete(T entity);
	
	public void updateByExample(T entity,Object obj);
	
	public void updateByPrimaryKeySelective(T entity);
	
	public void updateByPrimaryKey(T entity);

	public List<T> select(T entity);

	public List<T> selectByProperty(String property, Object value);
	
	List<T> selectByProperty(String[] properties, Object[] values);
	
	public List<T> selectByProperty(String property, Object value, String orderColumn, boolean asc);

	public int selectCount(T entity);

	public void saveSelective(T entity);

	public Pagination<?> findPagination(T entity);

	public Pagination<?> findPagination(T entity, int pageNum);

	public Pagination<?> findPagination(T entity, int pageNum, int limit);

	public Object convertBeanToView(T entity);

	public Pagination<?> pagination();

	public Pagination<?> pagination(int pageNum);

	public Pagination<?> pagination(int pageNum, int limit);

	public Pagination<?> paginationCustom();
	
	public Pagination<?> paginationCustom(Map<String, Object> params);
}
