package com.iteason.serviceimp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.FunctionDao;
import com.iteason.domain.Function;
import com.iteason.domain.User;
import com.iteason.service.FunctionService;
import com.iteason.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class FunctionServiceImp implements FunctionService {

	@Autowired
	private FunctionDao functionDao;
	@Override
	public List<Function> findAll() {
		// 查询所有的功能
		List<Function> list = functionDao.findAll();
		return list;
	}
	@Override
	public void save(Function function) {
		// 保存Function
		functionDao.save(function);
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		// 分页查询
		functionDao.pageQuery(pageBean);
	}
	@Override
	public List<Function> findMenu() {
		//  通过当前用户返回相应的权限菜单
		List<Function> list;
		User user = (User) ActionContext.getContext().getSession().get("loginUser");
		if(user.getUsername().equals("admin")){
			//是超级管理员
			list = functionDao.findAllMenu();
		}else{
			//其他用户，根据用户id查询菜单
			list = functionDao.findMenuByUserId(user.getId());
		}
		return list;
	}

}
