package com.iteason.serviceimp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.FunctionDao;
import com.iteason.domain.Function;
import com.iteason.service.FunctionService;
import com.iteason.utils.PageBean;

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

}
