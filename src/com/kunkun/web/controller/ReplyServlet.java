package com.kunkun.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kunkun.dao.BookDao;
import com.kunkun.dao.CatagoryDao;
import com.kunkun.dao.impl.BookDaoImpl;
import com.kunkun.dao.impl.CatagoryDaoImpl;
import com.kunkun.domain.Customer;
import com.kunkun.commons.Page;
import com.kunkun.domain.Book;
import com.kunkun.domain.Catagory;
import com.kunkun.service.BusinessService;
import com.kunkun.service.impl.BusinessServiceImpl;
import com.kunkun.web.beans.Cart;
import com.kunkun.web.beans.CartItem;

@SuppressWarnings("serial")
public class ReplyServlet extends HttpServlet {
	
	private BusinessService s = new BusinessServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        BookDao b = new BookDaoImpl();
        String userid = request.getParameter("userId");
        String commentid = request.getParameter("commentId"); 
        String reply = request.getParameter("content");
        System.out.println("-----------------------------"+reply);
        if(""==userid||userid.equals(null))
        	userid=null;
        b.saveReply(commentid, reply, userid);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}