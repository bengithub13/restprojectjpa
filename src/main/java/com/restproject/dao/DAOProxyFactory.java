package com.restproject.dao;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class DAOProxyFactory {
//	@Autowired
//	private SessionFactory sessionFactory;
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public DAOProxyFactory(){
		super();
	}
	
	//generic since we using a generic parameter type T in the method parameter, we have to declare the Type in signature
	//header<T>
	@SuppressWarnings("unchecked")
	public <T>T newInstance(Class<? extends GenericDAO<T>> daointerface,Class<T>domainClass){
		JPAGenericDAOImpl<T> daoImpl=new JPAGenericDAOImpl<T>(entityManager) ;
		
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
