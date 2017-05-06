package com.kunkun.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.management.Query;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kunkun.dao.CatagoryDao;
import com.kunkun.domain.Catagory;
import com.kunkun.exception.DaoException;
import com.kunkun.util.DBCPUtil;

@SuppressWarnings("unused")
public class CatagoryDaoImpl implements CatagoryDao {

    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    
	public void save(Catagory catagory) {
		try {
			qr.update("insert into catagory (id,name,description) values (?,?,?)", catagory.getId(),catagory.getName(),catagory.getDescription());
		} catch (SQLException e) {
			throw new DaoException();
		}
		
	}

	
	public List<Catagory> findAll() {
		try {
			return qr.query("select * from category", new BeanListHandler<Catagory>(Catagory.class));
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	
	public Catagory findById(String catagoryId) {
		try {
			return qr.query("select * from category where id=?", new BeanHandler<Catagory>(Catagory.class),catagoryId);
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

}
