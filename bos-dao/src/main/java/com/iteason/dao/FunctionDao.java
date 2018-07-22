package com.iteason.dao;

import java.util.List;

import com.iteason.domain.Function;

public interface FunctionDao extends BaseDao<Function> {

	List<Function> findAll();

	List<Function> findAllMenu();

	List<Function> findMenuByUserId(String id);

}
