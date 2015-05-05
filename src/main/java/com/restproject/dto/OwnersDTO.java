package com.restproject.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="owners")
public class OwnersDTO {
private List<OwnerDTO> owners;

public OwnersDTO(){
	
}
public OwnersDTO(List<OwnerDTO> owners){
	this.owners=owners;
}

@XmlElement(name="all_owners")
public List<OwnerDTO> getOwnerss(){
	return owners;
}

public void setOwners(List<OwnerDTO> owners){
	this.owners=owners;
}

}