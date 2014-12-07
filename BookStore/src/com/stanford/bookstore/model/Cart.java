package com.stanford.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<Book> items;
	
	public Cart() {
		items = new ArrayList<Book>();
	}

	public List<Book> getItems() {
		return items;
	}

	public void setItems(List<Book> items) {
		this.items = items;
	}
	
	public Book getItemById(Integer id) {
		for(Book book : items) {
			if(book.getId().intValue() == id.intValue()) {
				return book;
			}
		}
		return null;
	}
	
	public void addNewItem(Book book) {
		if(getItemById(book.getId()) == null) {
			items.add(book);
		}
	}

}
