package com.iteason.utils;

import org.junit.Test;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.iteason.domain.Role;

public class TestC extends HibernateDaoSupport{
 @Test
 public void fun1(){
	 Role role = new Role();
	 role.setId("12");
	this.getHibernateTemplate().save(role);
 }
}
