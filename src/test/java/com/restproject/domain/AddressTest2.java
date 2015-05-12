package com.restproject.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:/META-INF/applicationContext.xml" })
public class AddressTest2 extends AbstractTransactionalJUnit4SpringContextTests {
	// @Autowired
	// protected SessionFactory sessionFactory;
	@PersistenceContext
	protected EntityManager entityManager;
	protected Country country;
	HomeAddress address1;
	HomeAddress address2;
	HomeAddress address3;

	@Before
	public void setUp() throws Exception {

		//Session session = SessionFactoryUtils.getSession(sessionFactory, false);
		logger.info("DAO testing  beginning");
		country = new Country();
		country.setId(1600L);
		country.setCountry("china");
		country.setDate();

		address1 = new HomeAddress();
		address1.setId(1601l);
		address1.setAddress("45 Michael Ln");
		address1.setZipCode("11040");
		address1.setCountry(country);
		/*
		 * 
		 * 
		 * address2 = new HomeAddress(); address2.setId(601l);
		 * address2.setAddress("11420 Queens Blvd");
		 * address2.setZipCode("11375"); address2.setCountry(country); address3
		 * = new HomeAddress(); address3.setId(602l);
		 * address3.setAddress("14 peter ln"); address3.setZipCode("11040");
		 * address3.setCountry(country);
		 */
		Set<HomeAddress> homeAddresses = new HashSet<HomeAddress>();
		homeAddresses.add(address1);
		homeAddresses.add(address2);
		homeAddresses.add(address3);

		country.setAddress(homeAddresses);
	//	session.flush();
		// session.save(country);
	//	session.save(address1);
		
		entityManager.flush();
		entityManager.persist(country);
	}

	@Test
	// @Rollback(false)
	public void test() {

	//	HomeAddress loaded = (HomeAddress) sessionFactory.getCurrentSession()
		HomeAddress loaded = (HomeAddress) entityManager.find(HomeAddress.class, address1.getId());
		assertNotNull(loaded);

	}

}
