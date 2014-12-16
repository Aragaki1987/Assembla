package com.stanford.example.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.stanford.example.dao.BookDAO;
import com.stanford.example.model.Book;

public class CatalogAction extends ActionSupport{
	
	private List<Book> catalogs;
	
	public CatalogAction () {
		try {
			catalogs = BookDAO.getBookList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String excecute() {
		return SUCCESS;
	}

	public List<Book> getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(List<Book> catalogs) {
		this.catalogs = catalogs;
	}
	
	

}
