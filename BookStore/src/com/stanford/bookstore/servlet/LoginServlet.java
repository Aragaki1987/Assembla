package com.stanford.bookstore.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stanford.bookstore.dao.UserDAO;
import com.stanford.bookstore.model.Cart;
import com.stanford.bookstore.model.User;

public class LoginServlet extends HttpServlet {

	public LoginServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User user = null;
		try {
			user = UserDAO.getUserByName(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user != null && user.getPassword().equals(password)) {
			// init cart if login success
			Cart cart = new Cart();
			req.getSession().setAttribute("cart", cart);
			req.getSession().setAttribute("user", user);
			req.getRequestDispatcher("/catalog").forward(req, resp);
		} else {
			req.setAttribute("error",
					new String[] { "Your username/password is invalid" });
			req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
		}

	}
}
