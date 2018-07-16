package com.iteason.bos.web.action.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iteason.crm.service.Customer;
import com.iteason.crm.service.CustomerService;
import com.iteason.domain.Decidedzone;
import com.iteason.service.DecidedzoneService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class DecidedzoneAction extends ActionSupport implements ModelDriven<Decidedzone> {
	private Decidedzone decidezone =  new Decidedzone();
	@Override
	public Decidedzone getModel() {
		return decidezone;
	}
	
	@Autowired
	private DecidedzoneService decidedzoneService;
	
		//属性驱动，接收多个分区id
		private String[] subareaid;
		public void setSubareaid(String[] subareaid) {
			this.subareaid = subareaid;
		}
		
	@Autowired
	private CustomerService proxy;	
		
		
		
		
		public String addDecidedzone(){
			decidedzoneService.save(decidezone,subareaid);
			return "toDecidedzone";
		}
		
		public String testa(){
			List<Customer> list = proxy.findAll();
			System.out.println(list);
			return null;
		}
}
