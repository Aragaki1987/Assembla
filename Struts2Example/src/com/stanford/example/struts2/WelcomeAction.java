package com.stanford.example.struts2;

import com.opensymphony.xwork2.ActionSupport;

public class WelcomeAction extends ActionSupport{
	private String username;
	private String password;
	
	public String execute() throws Exception {
		System.out.println("WelcomeAction.execute() is called...");
		if(username.equals("Admin"))
			return SUCCESS;
		else 
			return ERROR;
	}
	
	/*public void validate() {
		if(username.isEmpty())
			addFieldError("username", "UserName is required");
		if(password.isEmpty())
			addFieldError("password", "Password is required");
	}
*/
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
	
	
	
	
}
