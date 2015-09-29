package com.jkkp.common.search;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.DateUtil;
import com.jkkp.utils.SpringContextUtil;

@Service("searchService")
@SuppressWarnings("unchecked")
public class SearchServiceImpl implements ISearchService {

	private final static String MODE_PRODUCT = "Product";
	private final static Version CURRENT_VERSION = Version.LUCENE_42;
	private static final int INDEX_PAGE_SIZE = 30;
	private static final int DEFAULT_LIMIT = 10;
	private static final int DEFAULT_PAGENUM = 1;
	@Value("#{application['lucene.path']}")
	private String basePath;
	@Value("#{application['lucene.mode']}")
	private String mode;

	private static Class<?>[] INDEX_CLASS = { Supplier.class, Design.class, Appointment.class };

	@Override
	public void index() {
		if (!MODE_PRODUCT.equals(mode)) {
			return;
		}
		for (Class<?> clazz : INDEX_CLASS) {
			this.indexClass(clazz);
		}
	}

	protected <T> void indexClass(Class<T> clazz) {
		Mapper<?> mapper = this.getClassMapper(clazz);
		int totalCount = mapper.selectCount(null);
		int pageCount = (totalCount / INDEX_PAGE_SIZE) + (totalCount % INDEX_PAGE_SIZE == 0 ? 0 : 1);
		for (int index = 0; index < pageCount; index++) {
			PageHelper.startPage(index + 1, INDEX_PAGE_SIZE);
			List<T> datalist = (List<T>) mapper.select(null);
			this.createIndex(datalist);
		}
	}

	private Mapper<?> getClassMapper(Class<?> clazz) {
		String mapperName = clazz.getSimpleName() + "Mapper";
		mapperName = mapperName.substring(0, 1).toLowerCase() + mapperName.substring(1);
		return (Mapper<?>) SpringContextUtil.getBean(mapperName);
	}

	protected <T> void createIndex(List<T> entityList) {
		IndexWriter writer = null;
		try {
			if (entityList == null || entityList.isEmpty()) {
				return;
			}
			Class<?> clazz = entityList.get(0).getClass();
			String indexPath = basePath + clazz.getSimpleName();
			File file = new File(indexPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			File[] fileList = file.listFiles();
			if (fileList.length > 0) {
				return;
			}

			Directory directory = FSDirectory.open(file);
			writer = new IndexWriter(directory, new IndexWriterConfig(CURRENT_VERSION, new StandardAnalyzer(
					CURRENT_VERSION)));
			java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
			Document doc = null;
			for (T entity : entityList) {
				doc = new Document();
				for (java.lang.reflect.Field field : fields) {
					Object value = this.getPropertyValue(entity, field);
					if (value != null) {
						doc.add(new Field(field.getName(), String.valueOf(value), TextField.TYPE_STORED));
					}
				}
				writer.addDocument(doc);
				writer.commit();
			}
			System.out.println("索引的数目：" + writer.numDocs());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public <T> void createIndex(T entity) {
		IndexWriter writer = null;
		try {
			Class<?> clazz = entity.getClass();
			String indexPath = basePath + clazz.getSimpleName();
			File file = new File(indexPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			Directory directory = FSDirectory.open(file);
			writer = new IndexWriter(directory, new IndexWriterConfig(CURRENT_VERSION, new StandardAnalyzer(
					CURRENT_VERSION)));
			java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
			Document doc = new Document();
			for (java.lang.reflect.Field field : fields) {
				Object value = this.getPropertyValue(entity, field);
				if (value != null) {
					doc.add(new Field(field.getName(), String.valueOf(value), TextField.TYPE_STORED));
				}
			}
			writer.addDocument(doc);
			writer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void deleteIndex(Class<?> clazz, String field, Object value) {
		IndexWriter writer = null;
		try {
			String indexPath = basePath + clazz.getSimpleName();
			File file = new File(indexPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			Directory directory = FSDirectory.open(file);
			writer = new IndexWriter(directory, new IndexWriterConfig(CURRENT_VERSION, new StandardAnalyzer(
					CURRENT_VERSION)));
			writer.deleteDocuments(new Term(field, String.valueOf(value)));
			writer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public <T> void updateIndex(T entity, String fieldName) {
		IndexWriter writer = null;
		try {
			Class<?> clazz = entity.getClass();
			String indexPath = basePath + clazz.getSimpleName();
			File file = new File(indexPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			Directory directory = FSDirectory.open(file);
			Analyzer analyzer = new StandardAnalyzer(CURRENT_VERSION);
			writer = new IndexWriter(directory, new IndexWriterConfig(CURRENT_VERSION, analyzer));
			java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
			Document doc = new Document();
			for (java.lang.reflect.Field field : fields) {
				Object value = this.getPropertyValue(entity, field);
				if (value != null) {
					doc.add(new Field(field.getName(), String.valueOf(value), TextField.TYPE_STORED));
				}
			}
			Object value = this.getPropertyValue(entity, clazz.getDeclaredField(fieldName));
			writer.updateDocument(new Term(fieldName, String.valueOf(value)), doc, analyzer);
			writer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public <T> Object getPropertyValue(T entity, java.lang.reflect.Field field) {
		try {
			return PropertyUtils.getProperty(entity, field.getName());
		} catch (NoSuchMethodException e) {
		} catch (InvocationTargetException e) {
		} catch (IllegalAccessException e) {
		}
		return null;
	}

	public <T> void setPropertyValue(T item, java.lang.reflect.Field field, String value) {
		try {
			String type = field.getType().getName();
			if (type.equals("int") || type.equals("java.lang.Integer")) {
				PropertyUtils.setProperty(item, field.getName(), CommonUtil.stringToInteger(value));
			} else if (type.equals("long") || type.equals("java.lang.Long")) {
				PropertyUtils.setProperty(item, field.getName(), CommonUtil.stringToLong(value));
			} else if (type.equals("float") || type.equals("java.lang.Float")) {
				PropertyUtils.setProperty(item, field.getName(), CommonUtil.stringToFloat(value));
			} else if (type.equals("double") || type.equals("java.lang.Double")) {
				PropertyUtils.setProperty(item, field.getName(), CommonUtil.stringToDouble(value));
			} else if (type.equals("java.math.BigDecimal")) {
				PropertyUtils.setProperty(item, field.getName(), new java.math.BigDecimal(value));
			} else if (type.equals("Date")) {
				PropertyUtils.setProperty(item, field.getName(), DateUtil.parse(value));
			} else if (type.equals("java.lang.String")) {
				PropertyUtils.setProperty(item, field.getName(), value);
			}
		} catch (NoSuchMethodException e) {
		} catch (InvocationTargetException e) {
		} catch (IllegalAccessException e) {
		}
	}
	
	public <T> List<T> queryEntity(T entity) {
		return this.queryEntity(entity, DEFAULT_PAGENUM, DEFAULT_LIMIT);
	}
	
	public <T> List<T> queryEntity(T entity, int pageNum) {
		return this.queryEntity(entity, pageNum, DEFAULT_LIMIT);
	}

	public <T> List<T> queryEntity(T entity, int pageNum, int limit) {
		List<T> entityList = new ArrayList<T>();
		try {
			Class<?> clazz = entity.getClass();
			File file = new File(basePath + clazz.getSimpleName());
			if (!file.exists() || file.list().length < 1) {
				return entityList;
			}

			Directory directory = FSDirectory.open(file);
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);

			BooleanQuery booleanQuery = new BooleanQuery();
			java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
			for (java.lang.reflect.Field field : fields) {
				Object value = this.getPropertyValue(entity, field);
				if (value != null) {
					QueryParser parser = new QueryParser(CURRENT_VERSION, field.getName(), new StandardAnalyzer(
							CURRENT_VERSION));
					booleanQuery.add(parser.parse(String.valueOf(value)), BooleanClause.Occur.MUST);
				}
			}
			TopDocs topDocs = searcher.search(booleanQuery, limit);
			
			int index = (pageNum - 1) * limit;
			if (index > 0) {
				ScoreDoc scoreDoc = topDocs.scoreDocs[index - 1];
				topDocs = searcher.searchAfter(scoreDoc, booleanQuery, limit);
			}
			System.out.println("总共有【" + topDocs.totalHits + "】条匹配结果");

			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				T item = (T) clazz.newInstance();
				Document targetDoc = searcher.doc(scoreDoc.doc);
				for (java.lang.reflect.Field field : fields) {
					String value = targetDoc.get(field.getName());
					if (!StringUtils.isNotEmpty(value)) {
						continue;
					}
					this.setPropertyValue(item, field, value);
				}
				entityList.add(item);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityList;
	}
}
