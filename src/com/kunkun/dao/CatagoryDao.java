package com.kunkun.dao;

import java.util.List;

import com.kunkun.domain.Catagory;

public interface CatagoryDao {

	void save(Catagory catagory);

	List<Catagory> findAll();

	Catagory findById(String catagoryId);


}
