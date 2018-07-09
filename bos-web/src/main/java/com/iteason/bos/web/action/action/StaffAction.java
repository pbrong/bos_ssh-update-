package com.iteason.bos.web.action.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iteason.domain.Staff;
import com.iteason.service.StaffService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
public class StaffAction extends ActionSupport implements ModelDriven<Staff> {
	private Staff staff = new Staff();
	@Override
	public Staff getModel() {
		return staff;
	}
	
	@Autowired
	private StaffService staffService;
	/**
	 * 增加取派员staffService
	 * @return
	 * @throws Exception
	 */
	public String addStaff() throws Exception {
		//保存取派员
		staffService.addStaff(staff);
		return "toStaff";
	}

	
	
}
