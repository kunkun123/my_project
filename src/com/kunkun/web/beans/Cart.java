package com.kunkun.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.kunkun.domain.Book;

public class Cart implements Serializable {
	
    private Map<String, CartItem> items = new HashMap<String, CartItem>();
    private int totalQuantity;
    private float amount;// 总金额
    
	public Map<String, CartItem> getItems() {
		return items;
	}
	public void setItems(Map<String, CartItem> items) {
		this.items = items;
	}
	public int getTotalQuantity() {
		totalQuantity = 0;
		for(Map.Entry<String, CartItem> item:items.entrySet()){
			totalQuantity+=item.getValue().getQuantity();
		}
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public float getAmount() {
		amount=0;
		for(Map.Entry<String, CartItem> item:items.entrySet()){
			amount+=item.getValue().getTotalPrice();
		}
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void addBook(Book book){
	    if(items.containsKey(book.getId())){
		//书籍已经存在
		CartItem item = items.get(book.getId());
		item.setQuantity(item.getQuantity()+1);
	    }else{
		CartItem item = new CartItem(book);
		item.setQuantity(1);
		items.put(book.getId(), item);
	}
	}
}
