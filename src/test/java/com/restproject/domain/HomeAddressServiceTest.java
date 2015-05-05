package com.restproject.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.restproject.dao.AddressDAO;
import com.restproject.dto.AddressesDTO;
import com.restproject.service.HomeAddressServiceImp;
import com.restproject.utility.HomeAddressMapperImpl;

@RunWith(MockitoJUnitRunner.class)
@Transactional

@ContextConfiguration(locations = { "classpath:/META-INF/applicationContext.xml" })
public class HomeAddressServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Mock
	private AddressDAO homeAddressDAO;
//	@Resource(name="HomeAddressService")	
//	@Autowired
	private HomeAddressServiceImp homeAddressService;
	
	private List<HomeAddress> addressList;
@Before
public void Setup(){
	logger.info("homeaddressservice testing  beginning");
	homeAddressService=new HomeAddressServiceImp(homeAddressDAO,new HomeAddressMapperImpl());
	Country country;
	country = new Country();
	country.setId(1600L);
	country.setCountry("china");
	country.setDate();

	HomeAddress address1; 
	address1= new HomeAddress();
	address1.setId(600l);
	address1.setAddress("29 Michael Ln");
	address1.setZipCode("11040");
	address1.setCountry(country);
	HomeAddress address2 = new HomeAddress();
	address2.setId(601l);
	address2.setAddress("33 peter ln");
	address2.setZipCode("11040");
	address2.setCountry(country);
	addressList=new ArrayList<HomeAddress>();
	addressList.add(address1);
	addressList.add(address2);
}
	
	/*
	// Mockito has a fluent API. You can use the verify() method to ensure that a method was called.
	// when(....).thenReturn(....) can be used to specify a condition and a return value for this condition. 
	// Normal save, return id
	when(someDao.save("first")).thenReturn(1);
	 
	// Another save but return a different id
	when(someDao.save("second")).thenReturn(2);
	 
	// Get exception
	when(someDao.save("third")).thenThrow(new DataAccessException("this is expected for third"));
	
	*/
	/*
	 *    final int id = 123;
    Mockito.doReturn(createOwner()).when(ownerRepository).findById(id); 
    Mockito.doReturn(createOwner()).when(ownerRepository).findById(Mockito.anyInt());
    
    The line
1
	
Mockito.verify(petRepository, Mockito.times(1)).save(pet);

says:
Dear Mockito, please check during the test execution, that petRepository.save() will be invoked one time (no zero, no more than one) and with the argument pet (as it is created by the createPet() support method).
	 */
	@Test
	public void test() {
		
		Mockito.doReturn(createAddressList()).when(homeAddressDAO).findByZipCode(Mockito.anyString());
		AddressesDTO addressesDTO=homeAddressService.getAllAddressListByZipCode("11040");
		logger.info("homeaddressservice testing ..."+addressesDTO.getHomeAddresses().get(0).getStreet()+" "+addressesDTO.getHomeAddresses().get(1).getStreet());
		assertEquals(2,addressesDTO.getHomeAddresses().size());
	}

	
	public List<HomeAddress> createAddressList(){
		return  addressList;
		
	}
}
