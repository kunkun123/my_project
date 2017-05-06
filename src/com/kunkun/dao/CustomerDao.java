package com.kunkun.dao;


import java.util.List;
import java.util.Map;

import com.kunkun.domain.Customer;

public interface CustomerDao {

	void save(Customer c);

	Customer findByCode(String code);

	void update(Customer c);

	Customer findCustomer(String username, String password);
	
	Customer findById(String customerId);
	
	Customer findByUsername(String username);
}
