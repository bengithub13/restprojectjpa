package com.restproject.domain;



import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;





/** Address Unit test
 * @author Ben Poon
 * @version 1.0
 						*/

@Entity
@Table(name="country")
@SequenceGenerator(name=AbstractDomain.GENERATED_VALUE_STRATEGY, sequenceName="COUNTRY_SEQ")
public class Country extends AbstractDomain{
	private static final long serialVersionUID=2l;

@Column(name="country")
private String country;

@Column(name="last_update")
private Date date;

//bi-directional relationship.  only one side is the owner of the relationship.  mappedby attributes refer to the property name
//of the association on the owener side.  only the owner side specifies the join column.
//if i left off mappedBy attribute, hibernate will default to use a join table to be the owner of the relationship
//hibernate created a new join table if i didnt specify the mappedby attribute
//using mappedby attribute specifies the only of the relationhiop is on the manytoone side where it has the foreign key

//@OneToMany(cascade=CascadeType.ALL)

@OneToMany(mappedBy="country",cascade=CascadeType.ALL)
private Set<HomeAddress> homeAddresses;




public String getCountry(){
return this.country;
}

public void setCountry(String country){
this.country=country;	
}


public Date getDate(){
return this.date;
}

public void setDate(){

this.date= new Date(System.currentTimeMillis());

}

public Set<HomeAddress> getAddress() {  
    return homeAddresses;  
}  

/** set address set which are the parents - bibernate will add the foreign key object and id
 * 
 * @param homeAddresses
 */
public void setAddress(Set<HomeAddress> homeAddresses) {  
    this.homeAddresses = homeAddresses;  
}  
}
