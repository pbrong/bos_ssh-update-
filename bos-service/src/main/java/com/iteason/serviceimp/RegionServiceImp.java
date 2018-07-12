package com.iteason.serviceimp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.RegionDao;
import com.iteason.domain.Region;
import com.iteason.domain.Subarea;
import com.iteason.service.RegionService;
import com.iteason.utils.PageBean;
@Service
@Transactional
public class RegionServiceImp implements RegionService {
	@Autowired
	private RegionDao regionDao;
	/**
	 * 批量保存Region对象
	 */
	@Override
	public void saveBatch(List<Region> regionList) {
		for(Region region:regionList){
			regionDao.saveOrUpdate(region);
		}

	}
	@Override
	public PageBean queryPage(PageBean pageBean) {
		// region查询
		PageBean pageQuery = regionDao.pageQuery(pageBean);
		return pageQuery;
	}
	@Override
	public List<Region> findAll() {
		//查询所有分区
		DetachedCriteria dc = DetachedCriteria.forClass(Region.class);
		List<Region> list = regionDao.findAll(dc);
		return list;
	}

}
