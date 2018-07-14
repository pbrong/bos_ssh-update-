package com.iteason.daoimp;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.iteason.dao.BaseDao;
import com.iteason.utils.PageBean;

/**
 * 
 * @author 阿荣
 * BaseDao<T>接口的实现类
 * @param <T>
 */
public class BaseDaoImp<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	private Class clazz;//用于接受运行期泛型类型
	
	public BaseDaoImp(){
		//获得当前类型的带有泛型类型的父类
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得运行期的泛型类型
		clazz = (Class) pt.getActualTypeArguments()[0];
	}

	
	@Override
	public void save(Object entity) {
		// 增
		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(Object entity) {
		// 删
		getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void update(Object entity) {
		// 改
		getHibernateTemplate().update(entity);
		
	}

	@Override
	public T findById(Serializable id) {
		// 通过id查
		return (T) getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll(DetachedCriteria c) {
		// 查询全部
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(c);
		return list;
	}


	@Override
	public PageBean pageQuery(PageBean pageBean) {
		//获取当前页
		int currentPage = pageBean.getCurrentPage();
		//获取当前页容量
		int pageSize = pageBean.getPageSize();
		//查询total总数量
		DetachedCriteria dc = pageBean.getDc();
			//设置查询数量的语句
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		Long total = list.get(0);
		pageBean.setTotal(total.intValue());
		//查询分页数据
			//清空查询数量的语句
		dc.setProjection(null);
		//指定hibernate框架封装对象的方式
			dc.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult = (pageBean.getCurrentPage()-1)*pageSize;
		int maxResult = pageSize;
		List<T> rows = (List<T>) getHibernateTemplate().findByCriteria(dc,firstResult,maxResult);
		pageBean.setRows(rows);
		//返回封装好的PageBean
		return pageBean;
	}


	@Override
	public void saveOrUpdate(T entity) {
		//保存或更新
		this.getHibernateTemplate().saveOrUpdate(entity);
		
	}

}
