package com.kunkun.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.kunkun.dao.OrderDao;
import com.kunkun.dao.impl.OrderDaoImpl;
import com.kunkun.commons.Page;
import com.kunkun.dao.BookDao;
import com.kunkun.dao.CatagoryDao;
import com.kunkun.dao.CustomerDao;
import com.kunkun.dao.impl.BookDaoImpl;
import com.kunkun.dao.impl.CatagoryDaoImpl;
import com.kunkun.dao.impl.CustomerDaoImpl;
import com.kunkun.domain.Book;
import com.kunkun.domain.Catagory;
import com.kunkun.domain.Customer;
import com.kunkun.domain.Order;
import com.kunkun.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
    private CatagoryDao catagoryDao = new CatagoryDaoImpl();
    private BookDao bookDao =new BookDaoImpl();
	private CustomerDao customerDao = new CustomerDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	public void addCatagory(Catagory catagory) {
		catagory.setId(UUID.randomUUID().toString());
		catagoryDao.save(catagory);
	}

	
	public List<Catagory> findAllCatagories() {
		return catagoryDao.findAll();
	}

	public Catagory findCatagoryById(String catagoryId) {
		return catagoryDao.findById(catagoryId);
	}


	
	public void addBook(Book book) {
		book.setId(UUID.randomUUID().toString());
		bookDao.save(book);
	}


	public Page findAllBookPageRecords(String pagenum) {
		int currentPageNum = 1;
		if(pagenum!=null){
			currentPageNum = Integer.parseInt(pagenum);
		}
		int totalRecords = bookDao.findAllBooksNumber();
		Page page = new Page(currentPageNum, totalRecords);
		page.setRecords(bookDao.findPageBooks(page.getStartIndex(),page.getPageSize()));
		return page;
	}


	public Book findBookById(String bookId) {
		return bookDao.findById(bookId);
	}


	@Override
	public Page findAllBookPageRecords(String pagenum, String catagoryId) {
		int currentPageNum = 1;
		if(pagenum!=null){
			currentPageNum = Integer.parseInt(pagenum);
		}
		int totalRecords = bookDao.findCategoryBooksNumber(catagoryId);
		Page page = new Page(currentPageNum, totalRecords);
		page.setRecords(bookDao.findPageBooks(page.getStartIndex(),page.getPageSize(),catagoryId));
		return page;
	}

	/**
	 * 从数据库中查询出要获得评论内容及登录名
	 */
	@Override
	public Map<Object, Map<String, Object>> showAllComments(String bookId) {
		Map<Object, Map<String, Object>> comments = new HashMap<Object, Map<String,Object>>();
		BookDao b = new BookDaoImpl();
		comments = b.getComments(bookId);
		return comments;
	}
	
	/**
	 * 验证登录
	 */
	public Customer customerLogin(String username, String password) {
		Customer c = customerDao.findCustomer(username,password);
		if(c==null)
			return null;
		if(!c.isActived())
			return null;
		return c;
	}


	public void genOrder(Order order) {
		if(order.getCustomer()==null)
			throw new IllegalArgumentException("订单的客户不能为空");
		order.setId(UUID.randomUUID().toString());
		orderDao.save(order);
	}

	public void regitsCustomer(Customer c) {
		c.setId(UUID.randomUUID().toString());
		customerDao.save(c);
	}
	public void activeCustomer(String code) {
		Customer c = customerDao.findByCode(code);
		c.setActived(true);
		customerDao.update(c);
	}


	@Override
	public List<Order> findOrders() {
		OrderDaoImpl o = new OrderDaoImpl();
		List<Order> list = o.findNeedOperatorOrders();
		return list;
	}

	/**
	 * 从数据库中查询所有评论的所有回复
	 */
	@Override
	public Map<Object, Map<String, Object>> showAllReplys(String bookId) {
		Map<Object, Map<String, Object>> replys = new HashMap<Object, Map<String,Object>>();
		BookDao b = new BookDaoImpl();
		replys = b.getReplys(bookId);
	//	System.out.println("new"+replys.toString());
		return replys;
	}


	public void saveReply(String commentId,String content, String userId) {
		// TODO Auto-generated method stub
		BookDao b = new BookDaoImpl();
		b.saveReply(commentId, content,userId);
	}
	
	
	public static void main(String[] args) {
		BusinessServiceImpl b = new BusinessServiceImpl();
		System.out.println(b.showAllReplys("0ae4a3bf-81ab-4727-8514-9f81e1b07968"));
	}
}
