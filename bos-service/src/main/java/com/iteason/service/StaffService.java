package com.iteason.service;

import com.iteason.domain.Staff;
import com.iteason.utils.PageBean;

public interface StaffService {

	public void addStaff(Staff staff);

	public PageBean queryPage(PageBean pageBean);

	public void deleteStaffs(String id);

	public Staff findById(String id);

	public void update(Staff staff);
}
