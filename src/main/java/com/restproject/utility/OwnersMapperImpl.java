package com.restproject.utility;

import org.springframework.stereotype.Component;


import com.restproject.domain.Owner;
import com.restproject.dto.OwnerDTO;

@Component
public class OwnersMapperImpl implements Mapper<Owner,OwnerDTO>{



	
	
	public OwnerDTO map(Owner source, OwnerDTO target) {
		// TODO Auto-generated method stub
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		return target;
	}

}