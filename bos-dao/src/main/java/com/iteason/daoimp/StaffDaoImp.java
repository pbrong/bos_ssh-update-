package com.iteason.daoimp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.iteason.dao.StaffDao;
import com.iteason.domain.Staff;
@Repository
public class StaffDaoImp extends BaseDaoImp<Staff> implements StaffDao {

	@Override
	public void deleteStaffs(String id) {
		// 更新deltag为0
		DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
		dc.add(Restrictions.eq("id", id));
		List<Staff> staffs = (List<Staff>) getHibernateTemplate().findByCriteria(dc);
		if(staffs.size() != 0){
			Staff staff = staffs.get(0);
			staff.setDeltag("0");
			getHibernateTemplate().update(staff);
		}
	}

	
}
