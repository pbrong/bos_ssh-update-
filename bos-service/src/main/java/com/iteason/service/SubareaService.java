package com.iteason.service;

import java.util.List;

import com.iteason.dao.BaseDao;
import com.iteason.domain.Subarea;
import com.iteason.utils.PageBean;

public interface SubareaService  {

	void saveSubarea(Subarea subarea);

	PageBean queryPage(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> findListNotAssociation();

}
