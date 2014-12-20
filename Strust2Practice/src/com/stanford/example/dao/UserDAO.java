package com.stanford.example.dao;

import org.hibernate.Query;
import org.hibernate.Transaction;

import com.stanford.example.model.User;

public class UserDAO extends AbtractDAO {

	public static User getUser(Integer id) throws Exception {
		return null;
	}

	public static User getUserByName(String username, String password)
			throws Exception {
		Transaction tx = getCurrentSession().beginTransaction();
		tx.begin();
		Query query = getCurrentSession()
				.createQuery(
						"select u from User u where u.username = :username and u.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);

		User user = (User) query.uniqueResult();
		tx.commit();
		return user;
	}
}
