package com.iteason.service;

import java.util.List;

import com.iteason.domain.Function;
import com.iteason.utils.PageBean;

public interface FunctionService {

	List<Function> findAll();

	void save(Function function);

	void pageQuery(PageBean pageBean);

}
