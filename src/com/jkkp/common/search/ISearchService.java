package com.jkkp.common.search;

import java.util.List;

public interface ISearchService {

	void index();

	<T> void createIndex(T entity);

	void deleteIndex(Class<?> clazz, String field, Object value);

	<T> void updateIndex(T entity, String fieldName);

	<T> List<T> queryEntity(T entity);

	<T> List<T> queryEntity(T entity, int pageNum);

	<T> List<T> queryEntity(T entity, int pageNum, int limit);
}
