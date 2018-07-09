package com.iteason.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.iteason.domain.User;

public interface UserDao extends IBaseDao<User>{

	User findUserByUsernameAndPassword(String username, String password);

	void editPassword(String id, String password);


}
