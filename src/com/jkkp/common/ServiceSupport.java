                                            package com.jkkp.common;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.beanutils.BeanUtils;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jkkp.utils.Pagination;

@SuppressWarnings(value = { "unchecked", "rawtypes" })
public abstract class ServiceSupport<T, V, PK> implements IService<T, V, PK> {

	protected abstract Mapper<T> getMapper();

	public Class<T> getPersistentClass() {
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<V> getViewClass() {
		return (Class<V>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	public void save(T entity) {
		this.getMapper().insert(entity);
	}

	public void update(T entity) {
		this.getMapper().updateByPrimaryKey(entity);
	}

	public T findById(PK id) {
		return this.getMapper().selectByPrimaryKey(id);
	}

	public void deleteById(PK id) {
		this.getMapper().deleteByPrimaryKey(id);
	}

	public void updateByExample(T entity, Object obj) {
		this.getMapper().updateByExample(entity, obj);
	}

	public void updateByPrimaryKeySelective(T entity) {
		this.getMapper().updateByPrimaryKeySelective(entity);
	}
	
	public void updateByPrimaryKey(T entity) {
		this.getMapper().updateByPrimaryKey(entity);
	}

	public void delete(T entity) {
		this.getMapper().deleteByPrimaryKey(entity);
	}

	public List<T> select(T entity) {
		return this.getMapper().select(entity);
	}

	public List<T> selectByProperty(String property, Object value) {
		try {
			T entity = this.getPersistentClass().newInstance();
			if (value != null) {
				BeanUtils.setProperty(entity, property, value);
			}
			return this.getMapper().select(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<T> selectByProperty(String property, Object value, String orderColumn, boolean asc) {
		Example example = new Example(this.getPersistentClass());
		example.createCriteria().andEqualTo(property, value);
		example.setOrderByClause(orderColumn + (asc ? " asc" : " desc"));
		return this.getMapper().selectByExample(example);
	}

	public List<T> selectByProperty(String[] properties, Object[] values) {
		Assert.assertNotNull("属性列表不能为空", properties);
		Assert.assertNotNull("查询条件列表不能为空", values);
		Assert.assertEquals(properties.length, values.length);
		try {
			T entity = this.getPersistentClass().newInstance();
			for (int i = 0; i < properties.length; i++) {
				BeanUtils.setProperty(entity, properties[i], values[i]);
			}
			return this.getMapper().select(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<T> selectByProperty(String[] properties, Object[] values,
			String orderColumn, boolean asc) {
		Assert.assertNotNull("属性列表不能为空", properties);
		Assert.assertNotNull("查询条件列表不能为空", values);
		Assert.assertEquals(properties.length, values.length);
		try {
			Example example = new Example(this.getPersistentClass());
			Criteria criteria = example.createCriteria();
			for (int i = 0; i < properties.length; i++) {
				criteria.andEqualTo(properties[i], values[i]);
			}
			example.setOrderByClause(orderColumn + (asc ? " asc" : " desc"));
			return this.getMapper().selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int selectCount(T entity) {
		return this.getMapper().selectCount(entity);
	}

	public void saveSelective(T entity) {
		this.getMapper().insertSelective(entity);
	}

	public Pagination<?> findPagination(T entity) {
		return this.findPagination(entity, Pagination.DEFAULT_PAGENUM, Pagination.DEFAULT_LIMIT);
	}

	public Pagination<?> findPagination(T entity, int pageNum) {
		return this.findPagination(entity, pageNum, Pagination.DEFAULT_LIMIT);
	}

	public Pagination<?> findPagination(T entity, int pageNum, int limit) {
		PageHelper.startPage(pageNum, limit);
		List<T> dataList = this.getMapper().select(entity);
		long total = ((Page<T>) dataList).getTotal();

		if (Pagination.isConvert()) {
			Pagination.clearConvert();
			return Pagination.newInstance(pageNum, this.convertBeanToView(dataList), total, limit);
		}
		return Pagination.newInstance(pageNum, dataList, total, limit);
	}

	public V convertBeanToView(T entity) {
		try {
			V dest = this.getViewClass().newInstance();
			BeanUtils.copyProperties(dest, entity);
			return dest;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<V> convertBeanToView(List<T> entityList) {
		List<V> viewList = new ArrayList<V>(entityList.size());
		for (T t : entityList) {
			viewList.add(this.convertBeanToView(t));
		}
		return viewList;
	}

	public Pagination<?> pagination() {
		return this.pagination(Pagination.DEFAULT_PAGENUM, Pagination.DEFAULT_LIMIT);
	}

	public Pagination<?> pagination(int pageNum) {
		return this.pagination(pageNum, Pagination.DEFAULT_LIMIT);
	}

	public Pagination<?> pagination(int pageNum, int limit) {
		Mapper<T> mapper = this.getMapper();
		Class clazz = mapper.getClass();
		try {
			Map<String, Object> params = this.prepare(Pagination.getSearchParams());
			if (params == null) {
				params = new HashMap<String, Object>();
				params.put("rowStart", (pageNum - 1) * limit);
				params.put("limit", limit);
			}
			Method dataMethod = clazz.getDeclaredMethod("findPage", Map.class);
			List dataList = (List) dataMethod.invoke(mapper, params);
			Method countMethod = clazz.getDeclaredMethod("countPage", Map.class);
			long total = (Long) countMethod.invoke(mapper, params);
			System.out.println("params【" + params + "】");
			return Pagination.newInstance(pageNum, dataList, total, limit);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Map<String, Object> prepare(Map<String, Object> params) {
		return params;
	}

	public Pagination<?> paginationCustom() {
		return paginationCustom(null);
	}

	public Pagination<?> paginationCustom(Map<String, Object> params) {
		Map<String, Object> config = Pagination.getPageParams();
		Pagination.clearPageParams();
		String dataStatement = (String) config.get("dataStatement");
		String countStatement = (String) config.get("countStatement");
		Map<String, Object> data = (Map<String, Object>) config.get("params");
		if (params != null) {
			data.putAll(params);
		}
		Mapper mapper = (Mapper) config.get("mapper");
		Class clazz = mapper.getClass();
		try {
			int pageNum = (Integer) data.get("pageNum");
			int limit = (Integer) data.get("limit");
			Method dataMethod = clazz.getDeclaredMethod(dataStatement, Map.class);
			List dataList = (List) dataMethod.invoke(mapper, data);

			Method countMethod = clazz.getDeclaredMethod(countStatement, Map.class);
			long total = (Long) countMethod.invoke(mapper, data);
			return Pagination.newInstance(pageNum, dataList, total, limit);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
