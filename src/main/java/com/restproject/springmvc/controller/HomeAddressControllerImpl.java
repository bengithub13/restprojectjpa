package com.restproject.springmvc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.restproject.service.HomeAddressServiceImp;
@Controller
public class HomeAddressControllerImpl implements HomeAddressController {
	@Autowired
	HomeAddressServiceImp homeAddressService;
	
	public ModelAndView getAllAddresses() {
		Logger.getRootLogger().info("all address lookup");
		return new ModelAndView("homeAddress","addressesObject",homeAddressService.getAllAddressList());	
	}

	/**
	 * 1st parameter to ModelAndView call = view name
	 * 2nd parameter = model data name
	 * 3rd parameter = model data
	 * 	 */
	
	public ModelAndView getAllAddressesByZip(String zipCode) {
		Logger.getRootLogger().info("all address lookup for a zipcode");
		return new ModelAndView("homeAddress","addressesObject",homeAddressService.getAllAddressListByZipCode(zipCode));	
	}

	
	public ModelAndView getAllAddressesByStreet(String street) {
		Logger.getRootLogger().info("all address lookup for street");
		return new ModelAndView("homeAddress","addressesObject",homeAddressService.getAllAddressByStreet(street));	
	}

}
