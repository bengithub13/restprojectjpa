package com.restproject.domain;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;


//@RunWith(SpringJUnit4ClassRunner.class)

@Transactional

@ContextConfiguration(locations = { "classpath:/META-INF/applicationContext.xml" })
public class OwnerAddressTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	protected SessionFactory sessionFactory;
	protected Owner owner1 ;
	protected Owner owner2 ;
	protected Owner owner3;
	protected List<Owner> address1Owners =new ArrayList<Owner>();
	protected List<Owner> address2Owners =new ArrayList<Owner>();
	protected List<Owner> address3Owners =new ArrayList<Owner>();
	protected List<Owner> address4Owners =new ArrayList<Owner>();


	protected HomeAddress address1;
	protected HomeAddress address2;
	protected HomeAddress address3;
	protected HomeAddress address4;
	protected Set<HomeAddress> owner1Addresses=new HashSet<HomeAddress>();;
	protected Set<HomeAddress> owner2Addresses=new HashSet<HomeAddress>();;
	protected Set<HomeAddress> owner3Addresses=new HashSet<HomeAddress>();;
	protected Set<HomeAddress> owner4Addresses=new HashSet<HomeAddress>();;
	
	protected Country country;
	
	// log4j native logger code
	//tatic final Logger logger=Logger.getLogger(CountryTests.class);
	static Logger logger=LoggerFactory.getLogger(OwnerAddressTest.class);
	@Before
	public void setUp() throws Exception {
		logger.info("Test beginning");
		Session session = SessionFactoryUtils.getSession(sessionFactory, false);
		owner1 = new Owner();
		owner1.setId(21600L);
		owner1.setFirstName("Ben");
		owner1.setLastName("Poon");
		owner2 = new Owner();
		owner2.setId(21601L);
		owner2.setFirstName("Gina");
		owner2.setLastName("Wong");
		owner3 = new Owner();
		owner3.setId(21602L);
		owner3.setFirstName("Kathy");
		owner3.setLastName("Poon");
		address1Owners.add(owner3);
		address2Owners.add(owner1);
		address2Owners.add(owner2);
		address3Owners.add(owner1);
		address3Owners.add(owner2);
		address4Owners.add(owner1);

		
		country=new Country();
		country.setId(2220l);
		country.setCountry("United States");
		country.setDate();
		
		//country= (Country)sessionFactory.getCurrentSession().get(Country.class, 600L);
		
		address1=new HomeAddress();
		address1.setId(21600l);
		address1.setAddress("79 Foch Ave");
		address1.setZipCode("10305");	
		address1.setOwner(address1Owners);		
	    address1.setCountry(country);
	    address2=new HomeAddress();
	    address2.setId(21601l);
		address2.setAddress("11420 Queens Blvd");
		address2.setZipCode("11375");
		address2.setOwner(address2Owners);
		address2.setCountry(country);
		address3=new HomeAddress();
		address3.setId(21602l);
		address3.setAddress("29 Michael Lane");
		address3.setZipCode("11040");
		address3.setOwner(address3Owners);
		address3.setCountry(country);
		address4=new HomeAddress();
		address4.setId(21604l);
		address4.setAddress("20 Peter Lane");
		address4.setZipCode("11040");
		address4.setOwner(address4Owners);
		address4.setCountry(country);
		

		
		owner3Addresses.add(address1);
		owner1Addresses.add(address2);
		owner1Addresses.add(address3);
		owner1Addresses.add(address4);
		owner2Addresses.add(address2);
		owner2Addresses.add(address3);
		
		
//		session.save(address1);
//		session.save(address2);
//		session.save(address3);
		
		owner1.setAddress(owner1Addresses);
		owner2.setAddress(owner2Addresses);
		owner3.setAddress(owner3Addresses);
		
		session.flush();
		session.save(address1);
		session.save(address2);
		session.save(address3);
		
//		session.save(owner1);
//		session.save(owner2);
//		session.save(owner3);
		
	}

	@Test
	//@Rollback(false)
	public void test() {
		//BasicConfigurator.configure();   log4j native intialization
		logger.info("Testing");
		Owner loaded = (Owner) sessionFactory.getCurrentSession().get(
				Owner.class, owner1.getId());
		HomeAddress loaded2 = (HomeAddress) sessionFactory.getCurrentSession().get(
				HomeAddress.class, address4.getId());
		assertNotNull(loaded);
	}

}

