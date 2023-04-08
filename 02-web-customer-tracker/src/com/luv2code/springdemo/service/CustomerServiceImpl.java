package com.luv2code.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.repository.CustomerDAO;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject the dao layer
	@Autowired
	private CustomerDAO theCustomerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers(){
		return theCustomerDAO.getCustomers();
	}
	
	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		theCustomerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return theCustomerDAO.getCustomer(theId);
	}
	
	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		theCustomerDAO.deleteCustomer(theId);
	}
	
	
}
