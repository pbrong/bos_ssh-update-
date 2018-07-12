package com.iteason.service;

import java.util.List;

import com.iteason.domain.Region;

/**
 * 
 * @author 阿荣
 * @Description: 区域
 * @date: 2018年7月12日 下午2:08:33
 */
public interface RegionService {

	public void saveBatch(List<Region> regionList);
	
}
