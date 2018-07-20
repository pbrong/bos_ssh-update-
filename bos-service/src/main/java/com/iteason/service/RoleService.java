package com.iteason.service;

import java.util.List;

import com.iteason.domain.Role;
import com.iteason.utils.PageBean;

public interface RoleService {

	void save(Role role, String functionIds);

	void pageQuery(PageBean pageBean);

	List<Role> findAll();

}
