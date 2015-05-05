package com.restproject.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="addresses")
public class AddressesDTO {
private List<HomeAddressDTO> homeAddresses;

public AddressesDTO(){
	
}
public AddressesDTO(List<HomeAddressDTO> homeAddresses){
	this.homeAddresses=homeAddresses;
}

@XmlElement(name="address")
public List<HomeAddressDTO> getHomeAddresses(){
	return homeAddresses;
}

public void setHomeAddresses(List<HomeAddressDTO> homeAddresses){
	this.homeAddresses=homeAddresses;
}

}
