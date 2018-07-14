package com.iteason.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.iteason.utils.PageBean;

import net.sf.ehcache.search.expression.Criteria;

/**
 * 
 * @author Administrator
 * 抽取常用方法
 * @param <T>
 */
public interface BaseDao<T> {
      public void save(T entity);
      public void delete(T entity);
      public void update(T entity);
      public void saveOrUpdate(T entity);
      public T findById(Serializable id);
      public List<T> findAll(DetachedCriteria c);
      public PageBean pageQuery(PageBean pageBean);
      public List<T> findByCriteria(DetachedCriteria dc);
}
