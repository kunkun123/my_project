package com.kunkun.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kunkun.dao.BookDao;
import com.kunkun.domain.Book;
import com.kunkun.exception.DaoException;
import com.kunkun.util.DBCPUtil;

public class BookDaoImpl implements BookDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public void save(Book book) {
		try{
			qr.update("insert into books (id,name,author,price,path,photoFileName,description,categoryId) values (?,?,?,?,?,?,?,?)", 
					book.getId(),
					book.getName(),
					book.getAuthor(),
					book.getPrice(),
					book.getPath(),
					book.getPhotoFileName(),
					book.getDescription(),
					book.getCategoryId());
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	public Book findById(String bookId) {
		try{
			return qr.query("select * from books where id=?", new BeanHandler<Book>(Book.class),bookId);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public int findAllBooksNumber() {
		try{
			Object obj = qr.query("select count(*) from books", new ScalarHandler(1));
			Long num = (Long)obj;
			return num.intValue();
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public List findPageBooks(int startIndex, int offset) {
		try{
			return qr.query("select * from books limit ?,?", new BeanListHandler<Book>(Book.class),startIndex,offset);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	
	public int findCategoryBooksNumber(String catagoryId) {
			try{
				Object obj = qr.query("select count(*) from books where categoryId=?", new ScalarHandler(1),catagoryId);
				Long num = (Long)obj;
				return num.intValue();
			}catch(Exception e){
				throw new DaoException(e);
			}
		}
	@Override
	public List findPageBooks(int startIndex, int pageSize, String catagoryId) {
		try{
			return qr.query("select * from books where categoryId=? limit ?,?", new BeanListHandler<Book>(Book.class),catagoryId,startIndex,pageSize);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	/**
	 * 根据书的ID进行评论的获取
	 */
	public Map<Object,Map<String, Object>> getComments(String bookid) {
		try {
			//return (List<String>) qr.query("select * from books where b.id=?)", new BeanHandler<Book>(Book.class),bookid);
			return qr.query("select c.id,u.username,c.commentContent from "
					+ "customer u inner join comments c inner join books b "
					+ "on c.customerId = u.id and c.bookid = b.id where b.id=?", new KeyedHandler(),bookid);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 保存评论内容
	 */
	public void saveComment(String userid, String bookid, String comment) {
		// TODO Auto-generated method stub
		try {
			qr.update("insert into comments (customerId,bookid,commentContent) values (?,?,?)",userid,bookid,comment);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 获取评论的回复。key为评论ID，value为用户名及回复内容
	 */
	public Map<Object, Map<String, Object>> getReplys(String bookId) {
		try {
			return qr.query("select r.id,c.id,u.username,r.content from customer u "
					+ "inner join comments c inner join books b inner join reply r"
					+ " on r.commentsId = c.id where b.id=?", new KeyedHandler(),bookId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 保存评论的回复
	 */
	public void saveReply(String commentId, String content,String userId) {
		// TODO Auto-generated method stub
		try {
			qr.update("insert into reply (commentsId,content,customerId) values (?,?,?)",commentId,content,userId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
}


