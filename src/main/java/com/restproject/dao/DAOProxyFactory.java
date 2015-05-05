package com.restproject.dao;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;




public class DAOProxyFactory {
	@Autowired
	private SessionFactory sessionFactory;
	
	public DAOProxyFactory(){
		super();
	}
	
	//generic since we using a generic parameter type T in the method parameter, we have to declare the Type in signature
	//header<T>
	@SuppressWarnings("unchecked")
	public <T>T newInstance(Class<? extends GenericDAO<T>> daointerface,Class<T>domainClass){
		GenericDAOImpl<T> daoImpl=new GenericDAOImpl<T>(sessionFactory) ;
		
		InvocationHandler handler=new HibernateDAOHandler<T>(daoImpl,domainClass);

	return  (T)Proxy.newProxyInstance(
	                            DAOProxyFactory.class.getClassLoader(),
	                            new Class[]{daointerface},handler);
	                         
}

	public void cleanup(){
	    Logger logger = LoggerFactory.getLogger(DAOProxyFactory.class);
	    logger.info("cleanup dao proxy");
	}
}
