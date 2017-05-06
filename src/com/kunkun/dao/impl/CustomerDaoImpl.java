package com.kunkun.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.kunkun.dao.CustomerDao;
import com.kunkun.domain.Book;
import com.kunkun.domain.Customer;
import com.kunkun.exception.DaoException;
import com.kunkun.util.DBCPUtil;

public class CustomerDaoImpl implements CustomerDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public void save(Customer c) {
		try {
			qr.update("insert into customer (id,username,password,phone,address,email,actived,code) values (?,?,?,?,?,?,?,?)", 
					c.getId(),
					c.getUsername(),
					c.getPassword(),
					c.getPhone(),
					c.getAddress(),
					c.getEmail(),
					c.isActived(),
					c.getCode());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Customer findByCode(String code) {
		try {
			return qr.query("select * from customer where code=?", new BeanHandler<Customer>(Customer.class),code);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void update(Customer c) {
		try {
			qr.update("update customers set username=?,password=?,phone=?,address=?,email=?,actived=?,code=? where id=?", 
					c.getUsername(),
					c.getPassword(),
					c.getPhone(),
					c.getAddress(),
					c.getEmail(),
					c.isActived(),
					c.getCode(),
					c.getId());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Customer findCustomer(String username, String password) {
		try {
			return qr.query("select * from customer where username=? and password=?", new BeanHandler<Customer>(Customer.class),username,password);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Customer findById(String customerId) {
		try {
			return qr.query("select * from customer where id=?", new BeanHandler<Customer>(Customer.class),customerId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Customer findByUsername(String username) {
		try {
			return qr.query("select * from customer where username=?", new BeanHandler<Customer>(Customer.class),username);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}
