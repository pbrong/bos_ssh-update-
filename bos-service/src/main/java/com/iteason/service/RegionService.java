package com.iteason.service;

import java.util.List;

import com.iteason.domain.Region;
import com.iteason.domain.Subarea;
import com.iteason.utils.PageBean;

/**
 * 
 * @author 阿荣
 * @Description: 区域
 * @date: 2018年7月12日 下午2:08:33
 */
public interface RegionService {

	public void saveBatch(List<Region> regionList);

	public PageBean queryPage(PageBean pageBean);

	public List<Region> findAll();

	
}
