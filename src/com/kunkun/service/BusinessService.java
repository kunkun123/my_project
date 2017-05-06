package com.kunkun.service;

import java.util.List;
import java.util.Map;

import com.kunkun.commons.Page;
import com.kunkun.domain.Book;
import com.kunkun.domain.Catagory;
import com.kunkun.domain.Customer;
import com.kunkun.domain.Order;

public interface BusinessService {
	/**
	 * 增加一个分类记录
	 * 
	 * @param catagory
	 */
	void addCatagory(Catagory catagory);

	/**
	 * 查询所有分类
	 * 
	 * @return
	 */
	List<Catagory> findAllCatagories();

	/**
	 * 根据ID查询分类
	 * 
	 * @param catagoryId
	 * @return没有查到返回null
	 */
	Catagory findCatagoryById(String catagoryId);
	/**
	 * 添加一本图书
	 * @param book
	 */
	void addBook(Book book);
	/**
	 * 根据页码显示该页的全部图书信息
	 * @param pagenum 用户要看的页码，默认为1
	 * @return 封装分页信息的类
	 */
	Page findAllBookPageRecords(String pagenum);
	/**
	 * 根据页码显示该页的全部图书信息,并根据分类进行查询
	 * @param pagenum 用户要看的页码，默认为1
	 * @return 封装分页信息的类
	 */
	Page findAllBookPageRecords(String pagenum,String catagoryId);
	Book findBookById(String bookId);
	/**
	 * 根据每本书的ID在展现详细信息的时候展现所有的评论
	 * @param bookId	书籍的ID		
	 * @return	评论对应的人及相应的评论
	 */
	Map<Object, Map<String, Object>> showAllComments(String bookId);
	/**
	 * 验证用户的登录信息
	 * @param username	登录名
	 * @param password	密码
	 * @return
	 */
	Customer customerLogin(String username, String password);

	void genOrder(Order order);
	void regitsCustomer(Customer c);
	/**
	 * 根据激活码激活账户
	 * @param code
	 */
	void activeCustomer(String code);
	/**
	 * 查询所有订单
	 * @return	order的集合
	 */
	List<Order> findOrders();
	/**
	 * 根据评论的id进行回复的显示
	 * @param commentsId 	评论的ID		
	 * @return	对应评论的人及相应的回复
	 */
	Map<Object, Map<String, Object>> showAllReplys(String bookId);
	/**
	 * 保存回复
	 * @param commentId	评论id	
	 * @param userId	用户ID
	 * @param content	回复的内容
	 */
	void saveReply(String commentId, String content,String userId);
	
}
