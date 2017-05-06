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
	 * ����һ�������¼
	 * 
	 * @param catagory
	 */
	void addCatagory(Catagory catagory);

	/**
	 * ��ѯ���з���
	 * 
	 * @return
	 */
	List<Catagory> findAllCatagories();

	/**
	 * ����ID��ѯ����
	 * 
	 * @param catagoryId
	 * @returnû�в鵽����null
	 */
	Catagory findCatagoryById(String catagoryId);
	/**
	 * ���һ��ͼ��
	 * @param book
	 */
	void addBook(Book book);
	/**
	 * ����ҳ����ʾ��ҳ��ȫ��ͼ����Ϣ
	 * @param pagenum �û�Ҫ����ҳ�룬Ĭ��Ϊ1
	 * @return ��װ��ҳ��Ϣ����
	 */
	Page findAllBookPageRecords(String pagenum);
	/**
	 * ����ҳ����ʾ��ҳ��ȫ��ͼ����Ϣ,�����ݷ�����в�ѯ
	 * @param pagenum �û�Ҫ����ҳ�룬Ĭ��Ϊ1
	 * @return ��װ��ҳ��Ϣ����
	 */
	Page findAllBookPageRecords(String pagenum,String catagoryId);
	Book findBookById(String bookId);
	/**
	 * ����ÿ�����ID��չ����ϸ��Ϣ��ʱ��չ�����е�����
	 * @param bookId	�鼮��ID		
	 * @return	���۶�Ӧ���˼���Ӧ������
	 */
	Map<Object, Map<String, Object>> showAllComments(String bookId);
	/**
	 * ��֤�û��ĵ�¼��Ϣ
	 * @param username	��¼��
	 * @param password	����
	 * @return
	 */
	Customer customerLogin(String username, String password);

	void genOrder(Order order);
	void regitsCustomer(Customer c);
	/**
	 * ���ݼ����뼤���˻�
	 * @param code
	 */
	void activeCustomer(String code);
	/**
	 * ��ѯ���ж���
	 * @return	order�ļ���
	 */
	List<Order> findOrders();
	/**
	 * �������۵�id���лظ�����ʾ
	 * @param commentsId 	���۵�ID		
	 * @return	��Ӧ���۵��˼���Ӧ�Ļظ�
	 */
	Map<Object, Map<String, Object>> showAllReplys(String bookId);
	/**
	 * ����ظ�
	 * @param commentId	����id	
	 * @param userId	�û�ID
	 * @param content	�ظ�������
	 */
	void saveReply(String commentId, String content,String userId);
	
}
