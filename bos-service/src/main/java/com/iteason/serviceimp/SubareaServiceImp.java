package com.iteason.serviceimp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.SubareaDao;
import com.iteason.daoimp.BaseDaoImp;
import com.iteason.domain.Subarea;
import com.iteason.service.SubareaService;
import com.iteason.utils.PageBean;
@Service
@Transactional
public class SubareaServiceImp implements SubareaService {
	@Autowired
	private SubareaDao subareaDao;
	
	@Override
	public void saveSubarea(Subarea subarea) {
		// 保存分区
		subareaDao.save(subarea);
	}


}
