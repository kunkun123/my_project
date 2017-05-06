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
	 * 保存评论
	 */
	void saveComment(String userid,String bookid,String comment);
	/**
	 * 进行对应评论的回复的查询
	 * @param 评论的id
	 * @return	回复的集合
	 */
	Map<Object, Map<String, Object>> getReplys(String commentId);
	/**
	 * 保存回复
	 */
	void saveReply(String commentid, String reply, String userid);
}
