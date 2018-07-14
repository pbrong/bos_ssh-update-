package com.iteason.serviceimp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.StaffDao;
import com.iteason.domain.Staff;
import com.iteason.service.StaffService;
import com.iteason.utils.PageBean;
@Service
@Transactional
public class StaffServiceImp implements StaffService {
	@Autowired
	private StaffDao staffDao;
	
	@Override
	public void addStaff(Staff staff) {
		// 增加取派员
		staffDao.save(staff);
	}

	@Override
	public PageBean queryPage(PageBean pageBean) {
		PageBean pageQuery = staffDao.pageQuery(pageBean);
		return pageQuery;
	}

	@Override
	public void deleteStaffs(String id) {
		//  修改staff的deltag为0
		staffDao.deleteStaffs(id);
		
	}

	@Override
	public Staff findById(String id) {
		// 通过id先查出来
		return staffDao.findById(id);
	}

	@Override
	public void update(Staff staff) {
		// 修改staff
		staffDao.update(staff);
	}

	@Override
	public List<Staff> findListNotDelete() {
		// 查找所有未作废的staff
		DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
		dc.add(Restrictions.eq("deltag", "1"));
		return staffDao.findByCriteria(dc);
	}

}
