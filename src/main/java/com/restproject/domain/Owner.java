package com.restproject.domain;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;


@Entity
@Table(name="owner")

@SequenceGenerator(name=AbstractDomain.GENERATED_VALUE_STRATEGY,sequenceName="OWNER_SEQ")
public class Owner extends AbstractDomain {

	private static final long serialVersionUID=2l;
@Column(name="first_name")
private String firstName;

@Column(name="last_name")
private String lastName;


@ManyToMany(fetch=FetchType.LAZY,mappedBy="owners",cascade=CascadeType.ALL)
private Set<HomeAddress> addressSet;


public String getFirstName(){
return this.firstName;
}

public void setFirstName(String firstName){
this.firstName=firstName;	
}

public String getLastName(){
return this.lastName;
}

public void setLastName(String lastName){
this.lastName=lastName;	
}



public Set<HomeAddress> getHomAddress() {  
    return addressSet;  
}  


public void setAddress(Set<HomeAddress> addressSet) {  
    this.addressSet = addressSet;  
} 


	

}
