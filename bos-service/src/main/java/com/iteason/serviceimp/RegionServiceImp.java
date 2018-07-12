package com.iteason.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.RegionDao;
import com.iteason.domain.Region;
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

}
