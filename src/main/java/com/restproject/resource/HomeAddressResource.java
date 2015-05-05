package com.restproject.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



import com.restproject.dto.AddressesDTO;



//http://localhost:8181/rest-project/services/HomeAddress/11040
@Path("/HomeAddress")
@Produces({"application/xml", "application/json"})
	public interface HomeAddressResource  {
	    
    @GET
	@Path("/all")
    @Produces("application/xml")
    AddressesDTO getAllAddresses();
 
	
	    @GET
		//@Path("/zipcode/{zipCode}")
	   
	@Produces("application/json")    
	  @Path("/{zipCode}")
	    AddressesDTO getAllAddressesByZip(@PathParam("zipCode") String zipCode);
	
	 
	    @GET
@Produces("application/json")    
@Path("/street/{street}")
  AddressesDTO getAllAddressesByStreet(@PathParam("street")String street);
}



