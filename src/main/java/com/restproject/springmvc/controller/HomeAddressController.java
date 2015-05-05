package com.restproject.springmvc.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@RequestMapping("/HomeAddress")
public interface HomeAddressController {
	// http://localhost:8181/rest-project/services/springmvc/HomeAddress/all
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	ModelAndView getAllAddresses();

	// http://localhost:8181/rest-project/services/springmvc/HomeAddress/zipCode?zipCode=11040
	@RequestMapping(value = "/zipCode", method = RequestMethod.GET)
	ModelAndView getAllAddressesByZip(@RequestParam("zipCode") String zipCode);

	// http://localhost:8181/rest-project/services/springmvc/HomeAddress/street?street=29%20Michael%20Lane
	@RequestMapping(value = "/street", method = RequestMethod.GET)
	ModelAndView getAllAddressesByStreet(@RequestParam("street") String street);
}
