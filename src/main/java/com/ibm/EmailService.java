package com.ibm;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1")
@RegisterRestClient
public interface EmailService {

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	String sendemail(String payload);

}