package com.stanford.example.struts2.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class PrintInterceptor implements Interceptor{

	@Override
	public void destroy() {
		System.out.println("Destroy() is called ...");
	}

	@Override
	public void init() {
		System.out.println("Init() is called ...");
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("Before execute() of WelcomeAction is called ...");
		arg0.invoke();
		System.out.println("After execute() of WelcomeAction is called ...");
		return null;
	}

}
