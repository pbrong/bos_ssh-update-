package com.iteason.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.StaffDao;
import com.iteason.domain.Staff;
import com.iteason.service.StaffService;
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

}
