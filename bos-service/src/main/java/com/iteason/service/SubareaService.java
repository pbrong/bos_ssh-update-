package com.iteason.service;

import com.iteason.dao.BaseDao;
import com.iteason.domain.Subarea;
import com.iteason.utils.PageBean;

public interface SubareaService  {

	void saveSubarea(Subarea subarea);

	PageBean queryPage(PageBean pageBean);

}
