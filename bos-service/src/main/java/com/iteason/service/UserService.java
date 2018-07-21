package com.iteason.service;

import java.util.List;

import com.iteason.domain.User;
import com.iteason.utils.PageBean;

public interface UserService {

	public User login(User user);

	public void editPassword(String id, String password);

	public void pageQuery(PageBean pageBean);

	
	
}
