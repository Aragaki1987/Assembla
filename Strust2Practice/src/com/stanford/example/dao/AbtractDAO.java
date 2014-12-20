package com.stanford.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class AbtractDAO {
	private static SessionFactory sessionFactory = getSessionFactory();

	public static SessionFactory getSessionFactory() {		
		if (sessionFactory == null) {
			try { // Create the SessionFactory
				sessionFactory = new Configuration().configure()
						.buildSessionFactory();
			} catch (Exception ex) {
				System.err.println("sessionFactory error " + ex);
				// throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}
	
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
}
