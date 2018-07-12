package com.iteason.daoimp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.iteason.dao.RegionDao;
import com.iteason.domain.Region;
import com.iteason.utils.PageBean;
@Repository
public class RegionDaoImp extends BaseDaoImp<Region> implements RegionDao {


}
