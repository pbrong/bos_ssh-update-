package com.iteason.daoimp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import com.iteason.dao.UserDao;
import com.iteason.domain.User;
@Repository
public class UserDaoImp extends BaseDaoImp<User> implements UserDao {
	/**
	 * 根据用户名和密码查询用户
	 */
	public User findUserByUsernameAndPassword(String username, String password) {
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username,password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	public void editPassword(String id, String password){
		/*String hql = "update User u set u.password = ? where u.id = ?";
		Object[] objects = {password,id};
		System.out.println(objects[0]+"..."+objects[1]);
		this.getHibernateTemplate().update(hql, objects);*/
		User user = getHibernateTemplate().get(User.class, id);
		user.setPassword(password);
		getHibernateTemplate().update(user);
	};
}
