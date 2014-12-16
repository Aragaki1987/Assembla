package com.stanford.example.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.stanford.example.dao.UserDAO;
import com.stanford.example.model.Cart;
import com.stanford.example.model.User;

public class LoginAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	
	private String username;
	private String password;
	
	public String execute() {
		try {
			System.out.println("Start Authenticate User ....");
			User user = UserDAO.getUserByName(username, password);
			
			//If cannot find user so return to login page
			if(user == null) {
				System.out.println("Authenticate User Failed....");
				return INPUT;
			}
			System.out.println("Authenticate User Success....");
			//ActionContext.getContext().getSession().put("user", user);
			//ActionContext.getContext().getSession().put("cart", new Cart());
			session.put("user", user);
			session.put("cart", new Cart());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;		
	}
	
	
	
}
