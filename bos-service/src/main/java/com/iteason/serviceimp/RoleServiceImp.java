package com.iteason.serviceimp;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.RoleDao;
import com.iteason.domain.Function;
import com.iteason.domain.Role;
import com.iteason.service.RoleService;
import com.iteason.utils.PageBean;
@Service
@Transactional
public class RoleServiceImp implements RoleService{
    @Autowired
	private RoleDao roleDao;

	@Override
	public void save(Role role, String functionIds) {
		roleDao.save(role);
		// 保存角色，同时关联权限，Function放弃反转（inverse=true）
		String[] split = functionIds.split(",");
		for (String id : split) {
			Function function = new Function();
			function.setId(id);
			role.getFunctions().add(function);
		}
		
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		// 分页查询
		roleDao.pageQuery(pageBean);
	}

	@Override
	public List<Role> findAll() {
		// 
		List<Role> findByCriteria = roleDao.findByCriteria(DetachedCriteria.forClass(Role.class));
		return findByCriteria;
	}
	
}