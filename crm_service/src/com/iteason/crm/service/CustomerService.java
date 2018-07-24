package com.iteason.crm.service;

import java.util.List;

import javax.jws.WebService;

import comiteason.crm.domain.Customer;
@WebService
public interface CustomerService {
	public List<Customer> findAll();
	public List<Customer> findListNotAssociation();
	public List<Customer> findListHasAssociation(String decidedzoneId);
	public void assigncustomerstodecidedzone(String decidedzoneId, Integer[] customerIds);
}
