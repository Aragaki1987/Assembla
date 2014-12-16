package com.stanford.example.model;

public class Book implements Comparable<Book>{
	private Integer id;
	private String name;
	private Integer price;
	private String author;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public int compareTo(Book o) {
		return this.name.compareTo(o.getName());		
	}
}
