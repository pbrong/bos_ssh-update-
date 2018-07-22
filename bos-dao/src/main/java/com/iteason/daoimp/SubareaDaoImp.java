package com.iteason.daoimp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iteason.dao.SubareaDao;
import com.iteason.domain.Subarea;
@Repository
public class SubareaDaoImp  extends BaseDaoImp<Subarea> implements SubareaDao{


	public List<Object> findSubareasGroupByProvince() {
		//通过省查询出数量
		String hql ="SELECT r.province ,count(*) FROM Subarea s LEFT OUTER JOIN s.region r Group BY r.province";		
		List<Object> list = (List<Object>) getHibernateTemplate().find(hql);
		return list;
	}

}
