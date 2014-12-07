package com.stanford.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stanford.bookstore.dao.BookDAO;
import com.stanford.bookstore.model.Cart;

public class ShowCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/pages/cart.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String[] ids = req.getParameterValues("id");
			Cart cart = (Cart) req.getSession().getAttribute("cart");
			for (String id : ids) {
				cart.addNewItem(BookDAO.getBook(Integer.valueOf(id)));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/pages/welcome.jsp").forward(req, resp);
	}

}
