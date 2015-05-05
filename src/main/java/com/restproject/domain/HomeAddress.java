package com.restproject.domain;



import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.restproject.dao.DAOProxyFactory;
@Entity
@NamedQueries({
	// if more than one address domain is returned..the owners children are returned with a full outer join and we end up getting all owners for 
	// all zipcodes- not just owners for each address
	//maybe the service bean will just have to format the dtos accordingly with a query on address and a seperate query on owners for each address domain.
	@NamedQuery(name="com.restproject.domain.HomeAddress.findByZipCode",query="from HomeAddress a where a.zipCode=?"),	
	// since we are getting unique address, the owner childrens are correctly joined.
	@NamedQuery(name="com.restproject.domain.HomeAddress.findByStreet",query="from HomeAddress a where a.street like ?"),
	@NamedQuery(name="com.restproject.domain.HomeAddress.findAll",query="from HomeAddress a")})


@Table(name="address")
@SequenceGenerator(name=AbstractDomain.GENERATED_VALUE_STRATEGY,sequenceName="ADDRESS_SEQ")
public class HomeAddress extends AbstractDomain{
	private static final long serialVersionUID=2l;
	

@Column(name="street")
private String street;

@Column(name="zip_code")
private String zipCode;

@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
@JoinColumn(name="country_fk",nullable=false)
//Many addresses per one country..each  country can be in many addresses,
private Country country;


// ARTICLE ABOUT LAZY LOADING  WHICH BY NATURE RETURNS A PROXY OBJECT IN PLACE OF THE RELATED OBJECT THAT IS LAZY LOADED.  IF THE SESSION IS CLOSED
// AND LATER WE TRY TO ACCESS THE ATTRIBUTES OF THE PARENT OBJECT..THE CONNECTION IS ALREADY CLOSED AND WE WILL GET A
//org.hibernate.LazyInitializationException: could not initialize proxy - no Session EXCEPTION
//http://javarevisited.blogspot.com/2014/04/orghibernatelazyinitializationException-Could-not-initialize-proxy-no-session-hibernate-java.html


//joincolumn in manytomany pivot table should always be set to the other side's foreign key to this domain
// doesnt matter which side owns the relationship in bidirectional many to many relationship, but it does matter the joincolumn and inversejoincolumn be the other side.
// of the owner.
@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)

@JoinTable(name="OWNER_ADDRESS_PIVOT",
joinColumns={@JoinColumn(name="ADDRESS_FK", referencedColumnName="ID")},
inverseJoinColumns={@JoinColumn(name="OWNER_FK",referencedColumnName="ID")})
private List<Owner> owners;


public String getStreet(){
return this.street;
}

public void setAddress(String street){
this.street=street;	
}


public String getZipCode(){
return this.zipCode;
}

public void setZipCode(String zipCode){

this.zipCode= zipCode;


}

public void setCountry(Country country){
	
	this.country=country;
	
}
public Country getCountry(){
	
return this.country;
	
}

/*
public Set<Owner> getOwner() {  
    return owners; 

}  


public void setOwner(Set<Owner> owners) {  
   this.owners = owners;  
}
*/
public List<Owner> getOwner() {  
    return owners; 

}  





public void setOwner(List<Owner> owners) {  
   this.owners = owners;  
}  


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((street == null) ? 0 : street.hashCode());
	result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (!(obj instanceof HomeAddress))
		return false;
	HomeAddress other = (HomeAddress) obj;
	if (street == null) {
		if (other.street != null)
			return false;
	} else if (!street.equals(other.street))
		return false;
	if (zipCode == null) {
		if (other.zipCode != null)
			return false;
	} else if (!zipCode.equals(other.zipCode))
		return false;
	return true;
}

}
