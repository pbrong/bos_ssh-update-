package com.iteason.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.UserDao;
import com.iteason.domain.User;
import com.iteason.service.UserService;
@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userDao;
	/***
	 * 用户登录
	 */
	public User login(User user) {
		return userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public void editPassword(String id, String password) {
		// 根据用户id修改密码
			userDao.editPassword(id,password);
		
	}
	

}
