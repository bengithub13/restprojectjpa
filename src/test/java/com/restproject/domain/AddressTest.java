package com.restproject.domain;

import static org.junit.Assert.assertNotNull;

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
//@TestExecutionListeners(TransactionalTestExecutionListener.class)
//   DependencyInjectionTestExecutionListener.class, ServletTestExecutionListener.class})

 @Transactional
//public class CountryTests extends AbstractTransactionalJUnit4SpringContextTests{
@ContextConfiguration(locations={"classpath:/META-INF/applicationContext.xml"})

public class AddressTest extends AbstractTransactionalJUnit4SpringContextTests{
	//@Autowired
	//protected SessionFactory sessionFactory;
	@PersistenceContext
	protected EntityManager entityManager;
	protected Country  country;
	HomeAddress address1;
	HomeAddress address2;
	HomeAddress address3;
	
	@Before
	public void setUp() throws Exception {
		
	//Session session=SessionFactoryUtils.getSession(sessionFactory,false);	
	logger.info("DAO testing  beginning");
	country = new Country();
	
	country.setId(600L);
	country.setCountry("china");
	country.setDate();
	//country= (Country)sessionFactory.getCurrentSession().get(Country.class, 600L);
	address1 = new HomeAddress();
	address1.setId(703l);
	address1.setAddress("19 peter Ln");
	address1.setZipCode("11040");
	address1.setCountry(country);
	address2 = new HomeAddress();
	address2.setId(704l);
	address2.setAddress("11420 Queens Blvd");
	address2.setZipCode("11375");
	address2.setCountry(country);
	address3 = new HomeAddress();
	address3.setId(705l);
	address3.setAddress("14 leslie ln");
	address3.setZipCode("11040");
	address3.setCountry(country);
	
	Set<HomeAddress> homeAddresses = new HashSet<HomeAddress>();
	homeAddresses.add(address1);
	homeAddresses.add(address2);
	homeAddresses.add(address3);


	country.setAddress(homeAddresses);
	//session.flush();
	//session.save(country);
	entityManager.flush();
	entityManager.persist(country);
	}

	@Test
//	@Rollback(false)
	public void test() {
		
//		HomeAddress loaded = (HomeAddress)sessionFactory.getCurrentSession().get(HomeAddress.class, address1.getId()) ;
		HomeAddress loaded = (HomeAddress)entityManager.find(HomeAddress.class, address1.getId());
		assertNotNull(loaded);
	
	}


	

}
