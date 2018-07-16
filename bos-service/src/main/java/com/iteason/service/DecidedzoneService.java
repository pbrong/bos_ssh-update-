package com.iteason.service;

import com.iteason.dao.BaseDao;
import com.iteason.domain.Decidedzone;
import com.iteason.utils.PageBean;

public interface DecidedzoneService {

	void save(Decidedzone decidezone, String[] subareaid);

	void pageQuery(PageBean pageBean);
	
}
