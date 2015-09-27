package com.restproject.dao;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class DAOProxyFactory {
//	@Autowired
//	private SessionFactory sessionFactory;
//	@PersistenceContext
	@PersistenceContext(unitName="restProjectPU", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	
	public DAOProxyFactory(){
		super();
	}
	
	//generic since we using a generic parameter type T in the method parameter, we have to declare the Type in signature
	//header<T>
	//notice the prototype scope of these generic DAO implementation as defined in xml. Using this scope means that the Spring container will create a new instance of the DAO each time it is requested (including on autowiring). That will allow a service to use multiple DAOs with different parameters for different entities, as needed.
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
