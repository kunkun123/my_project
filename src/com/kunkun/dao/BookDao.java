package com.kunkun.dao;

import java.util.List;
import java.util.Map;

import com.kunkun.domain.Book;

public interface BookDao {

	void save(Book book);

	Book findById(String bookId);

	int findAllBooksNumber();

	List findPageBooks(int startIndex, int pageSize);

	int findCategoryBooksNumber(String catagoryId);

	List findPageBooks(int startIndex, int pageSize, String catagoryId);

	Map<Object, Map<String, Object>> getComments(String bookid);
	/**
	 * ��������
	 */
	void saveComment(String userid,String bookid,String comment);
	/**
	 * ���ж�Ӧ���۵Ļظ��Ĳ�ѯ
	 * @param ���۵�id
	 * @return	�ظ��ļ���
	 */
	Map<Object, Map<String, Object>> getReplys(String commentId);
	/**
	 * ����ظ�
	 */
	void saveReply(String commentid, String reply, String userid);
}
