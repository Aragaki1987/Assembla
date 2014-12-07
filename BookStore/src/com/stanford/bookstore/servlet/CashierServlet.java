package com.stanford.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stanford.bookstore.model.Book;
import com.stanford.bookstore.model.Cart;

public class CashierServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Book> items = ((Cart)req.getSession().getAttribute("cart")).getItems();
		
		if(items != null && !items.isEmpty()) {	
			req.getRequestDispatcher("/pages/checkout.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "You must get items in your card before checkout");
			req.getRequestDispatcher("/catalog").forward(req, resp);
		}
		
	}

}
