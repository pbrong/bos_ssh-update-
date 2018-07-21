package com.iteason.serviceimp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public PageBean queryPage(PageBean pageBean) {
		// 分区模糊查询
		pageBean = subareaDao.pageQuery(pageBean);
		return pageBean;
	}

	@Override
	public List<Subarea> findAll() {
		// 查找所有subarea对象
		return subareaDao.findByCriteria(DetachedCriteria.forClass(Subarea.class));
	}

	@Override
	public List<Subarea> findListNotAssociation() {
		// 查询未关联的分区
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.isNull("decidedzone.id"));
		return subareaDao.findByCriteria(dc);
	}



}
