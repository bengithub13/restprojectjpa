package com.restproject.dao;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;


public class GenericDAOImpl<T> implements
		GenericDAO<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public GenericDAOImpl(SessionFactory sessionFactory){
		super();
		this.sessionFactory=sessionFactory;
		
	}

	protected Session getSession() {
		return
		// HibernateUtil.getSession();

		//SessionFactoryUtils.getSession(sessionFactory, false);
				sessionFactory.openSession();
	}

	public void save(T entity) {
		Session hibernateSession = this.getSession();
		hibernateSession.saveOrUpdate(entity);
	}

	// when entity is no longer attached to an entity manager or context, but
	// the object still represents
	// objects in a database.
	// example is if session was closed reference to the object is still valid,
	// but hibernate is no longer tracking the changes of detached entities.
	// Changes to detached entity objects are not store in databse unless the
	// detached entity is
	// is mergged back with a new entity manager or session context.
	// once an entity is managed by hibernate, any changes to entity is going to
	// be detected and propogated back to the db.
	// We no longer need to insert/uodate/delete, the changes are sycrhonized at
	// the very last moment during the session's flush-time.
	// if merging entity has no equivalent in the current session then, one wll
	// be fetched from the database.
	// merging is merging changes to newly created instance and save to
	// database, not re-attaching old object.
	// merging a new transient entity will create a new instance , copy the
	// state of the entity to new instance which will be managed.
	// merging existiing detached entity will copy state to prexisiting managed
	// entity.
	// if we are dealing withand object in the same session, we should use
	// uodate() or saveOrUpdate().
	// note only the returned objec of merged() call is managed and tracked ,
	// not the original entitiy passed to merge.
	// persist. save changes arent guaranteed to be saved immediately. it might
	// happen at flush time.
	// persist also has to be attached to a session. it will not insert to
	// database if outside the trasaction boundary, while save()
	// always does a insert if it needs to even if outsde a transaction.
	// hence persist is good choice if running long transactions.
	// persist can only be used within a transaction, so it is safe and takes
	// care of cascading objects.
	// save will save entity to db even outside transaction...which causes only
	// primary to be saved. not cascading entities unless we flush.
	

	public void merge(T entity) {
		Session hibernateSession = this.getSession();
		hibernateSession.merge(entity);
	}

	public void delete(T entity) {
		Session hibernateSession = this.getSession();
		hibernateSession.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> findMany(Query query) {
		List<T> t;
		t =  (List<T>)query.list();
		return t;
	}	
	
	@SuppressWarnings("unchecked")


	//Query is an object to set to create a query lookup
	//example = Query query=getSession().getNamedQuery("getAccountById");
	//query.setLong("accountId",accountId);
	//querysetString("accuntType",Account.SAVING.toString());
	//Account account=(Account)Query.uniqueResult();
	public T findOne(Query query) {
		T t;
		t = (T) query.uniqueResult();
		return t;
	}


	@SuppressWarnings("unchecked")
public T findByID(Class<?> clazz, BigDecimal id) {
		Session hibernateSession = this.getSession();
		T t = null;
		t = (T) hibernateSession.get(clazz, id);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<?> clazz) {
		Session hibernateSession = this.getSession();
		List<T> t = null;
		Query query = hibernateSession.createQuery("from " + clazz.getName());
		t = (List<T>)query.list();
		return t;
	}
	
	/**
	 *  there are 2 parameter binding in hibernate query (hql) 1- named parameter , 2-positional parameter
	 * @param method
	 * @param domainClass
	 * @param queryArgs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> executeListQuery(Method method, Class<T> domainClass, Object[] queryArgs){
		Query queryObject=this.getSession().getNamedQuery(getQueryName(method,domainClass));
		
		if (queryObject.getNamedParameters().length==0){
			setPositionalParameters(queryObject,queryArgs);
		}
		else{
			setNamedParameters(queryObject,queryArgs);
		}
		
		return (List<T>) queryObject.list();
	}
	
	
	private void setPositionalParameters(Query queryObject, Object[] queryArgs){
		if (queryArgs!=null){
		for (int i=0;i<queryArgs.length;i++){
			queryObject.setParameter(i,queryArgs[i] );
		}
		}
	}
	
	private void setNamedParameters(Query queryObject, Object[] queryArgs){
		String[] namedParameters=queryObject.getNamedParameters();
		for (int i=0;i<queryArgs.length;i++){
			if (queryArgs[i] instanceof Collection){
				queryObject.setParameterList(namedParameters[i], (Collection<?>) queryArgs[i]);
			}
			else{
				queryObject.setParameter(namedParameters[i], queryArgs[i]);
			}
				
		}
	}
	
	
	
	/**
	 * 
	 * 
	 * @return fully qualified name of named query.  Packagename.classname.queryname
	 */
	public String getQueryName(Method method, Class<T> domainClass){
		String queryName=domainClass.getName()+"."+method.getName();
	return queryName;
	}
	
	
	public void cleanup(){
		
	}
}
