package com.restproject.resource;







import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.restproject.dto.AddressesDTO;
import com.restproject.service.HomeAddressServiceImp;

@Controller("homeaddress")
public class HomeAddressResourceImpl implements HomeAddressResource{
	

@Autowired
HomeAddressServiceImp homeAddressService;

public AddressesDTO getAllAddressesByZip(String zipCode) {
	Logger.getRootLogger().info("all address lookup for a zipcode");
	return homeAddressService.getAllAddressListByZipCode(zipCode);	
}


public AddressesDTO getAllAddresses(){
	Logger.getRootLogger().info("all address lookup all zipcodes");
	//return homeAddressService.getAllAddressListByZipCode("11040");	
	return homeAddressService.getAllAddressList();
	}



public AddressesDTO getAllAddressesByStreet(String street) {
	// TODO Auto-generated method stub
	Logger.getRootLogger().info("get addressby street" + " " +street);
	return homeAddressService.getAllAddressByStreet(street);	

}
}
