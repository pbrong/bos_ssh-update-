package com.iteason.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.DecidedzoneDao;
import com.iteason.dao.SubareaDao;
import com.iteason.domain.Decidedzone;
import com.iteason.domain.Subarea;
import com.iteason.service.DecidedzoneService;
import com.iteason.utils.PageBean;
@Service
@Transactional
public class DecidedzoneServiceImp implements DecidedzoneService {
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private SubareaDao subareaDao;
	/**
	 * 保存定区
	 */
	@Override
	public void save(Decidedzone decidezone, String[] subareaid) {
		 //先保存定区，再查出相应的分区并添加定区id，更新分区，让分区来维护外键
		decidedzoneDao.save(decidezone);
		for(String id :subareaid){
			//根据查询出分区
			Subarea subarea = subareaDao.findById(id);
			//由多的一方维护外键
			subarea.setDecidedzone(decidezone);
		}

	}
	/**
	 * 查询定区
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		// 
		 pageBean = decidedzoneDao.pageQuery(pageBean);
		
	}

}
