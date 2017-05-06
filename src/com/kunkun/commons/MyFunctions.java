package com.kunkun.commons;

import com.kunkun.domain.Catagory;
import com.kunkun.service.BusinessService;
import com.kunkun.service.impl.BusinessServiceImpl;

public class MyFunctions {
	private static BusinessService s = new BusinessServiceImpl();
	public static String showCategoryName(String categoryId){
		Catagory c = s.findCatagoryById(categoryId);
		if(c!=null){
			return c.getName();
		}
		return null;
	}
}
