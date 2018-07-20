package com.iteason.daoimp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import com.iteason.dao.RoleDao;
import com.iteason.domain.Role;
import com.iteason.domain.User;
import com.iteason.utils.PageBean;
@Repository
public class RoleDaoImp extends BaseDaoImp<Role> implements RoleDao {

	@Override
	public void save(Role entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Role entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Role entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(Role entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Role findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll(DetachedCriteria c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findByCriteria(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return null;
	}

}
