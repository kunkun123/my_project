package com.kunkun.web.beans;

import java.io.Serializable;

import com.kunkun.domain.Book;

public class CartItem implements Serializable {
	private Book book;
	public CartItem(Book book){
		this.book = book;
	}
	private int quantity;//С������
	private float totalPrice;//���С��
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotalPrice() {
		return book.getPrice()*quantity;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
