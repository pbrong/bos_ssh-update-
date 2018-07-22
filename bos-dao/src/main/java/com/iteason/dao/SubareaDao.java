package com.iteason.dao;

import java.util.List;

import com.iteason.domain.Subarea;

public interface SubareaDao extends BaseDao<Subarea>{

	List<Object> findSubareasGroupByProvince();

}
