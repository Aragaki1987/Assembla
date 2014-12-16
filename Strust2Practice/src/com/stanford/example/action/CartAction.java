package com.stanford.example.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.stanford.example.dao.BookDAO;
import com.stanford.example.model.Cart;

public class CartAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	
	private List<Integer> ids;
	private Cart cart;

	public String execute() {
		
		try {
			if (ids != null && !ids.isEmpty()) {
				/*cart = (Cart) ActionContext.getContext().getSession()
						.get("cart");*/
				cart = (Cart) session.get("cart");
				for (Integer id : ids) {					
					cart.addNewItem(BookDAO.getBook(id));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
		
	}
}
