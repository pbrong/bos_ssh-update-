package com.iteason.daoimp;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.iteason.dao.IBaseDao;

/**
 * 
 * @author 阿荣
 * IBaseDao<T>接口的实现类
 * @param <T>
 */
public class BaseDaoImp<T> extends HibernateDaoSupport implements IBaseDao<T> {
	
	
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

}
