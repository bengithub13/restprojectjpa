package com.restproject.dao;

	
	import java.math.BigDecimal;
	import java.util.*;
	import org.hibernate.Query;
	 
	public interface GenericDAO<T> {
	 
		
	    public void save(T entity);
	 
	    public void merge(T entity);
	 
	    public void delete(T entity);
	 
	    public List<T> findMany(Query query);
	 
	    public T findOne(Query query);
	 
	    public List<T> findAll(Class<?> clazz);
	 
	    public T findByID(Class<?> clazz, BigDecimal id);
	    
	   public void cleanup();
	}

