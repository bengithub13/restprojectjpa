package com.restproject.dto;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="owner")
public class OwnerDTO {
private String firstName;
private String lastName;

@XmlElement(name="owner_first_name")
public String getFirstName(){
return this.firstName;
}

public void setFirstName(String firstName){
	this.firstName=firstName;
}

@XmlElement(name="owner_last_name")
public String getLastName(){
return this.lastName;
}

public void setLastName(String lastName){
	this.lastName=lastName;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (!(obj instanceof OwnerDTO))
		return false;
	OwnerDTO other = (OwnerDTO) obj;
	if (firstName == null) {
		if (other.firstName != null)
			return false;
	} else if (!firstName.equals(other.firstName))
		return false;
	if (lastName == null) {
		if (other.lastName != null)
			return false;
	} else if (!lastName.equals(other.lastName))
		return false;
	return true;
}


}
