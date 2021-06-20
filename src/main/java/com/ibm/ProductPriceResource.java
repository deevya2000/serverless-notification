package com.ibm;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.smallrye.mutiny.Uni;


@Path("/")
public class ProductPriceResource {
	
	/*
	 * @Inject
	 * 
	 * @RestClient EmailService emailService;
	 */
    
    @GET
	@Path("/pricehello")
	@Produces(MediaType.APPLICATION_JSON)
	public String pricehello() {		
		//int statusCode = sendEmailNotificationWithQuarkus(productId, newPrice);
		System.out.println("inside");
		String resp = "";//emailService.sendemail("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}");
        System.out.println("Serverless Response = "+resp);        
        return resp;
	}
	

	@GET
	@Path("/pricechanged/{productId}/{newPrice}")
	@Produces(MediaType.TEXT_PLAIN)
	public void pricechanged(@PathParam("productId") String productId, @PathParam("newPrice") String newPrice) {
		System.out.println("Serverless call invoked for product ID : "+productId+", price has changed to "+newPrice);	
		//int statusCode = sendEmailNotificationWithQuarkus(productId, newPrice);		
		//String resp = emailService.sendemail("test json data");
         //System.out.println("Serverless Response = "+resp);	
		
		/*
		 * if(statusCode == 201) return "Notification sent!"; else return
		 * "Failure to send notification!";
		 */
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello! Notification service is accessible";
	}
	
	///////////////
	
    @Inject
    @RestClient
    CountriesService countriesService;

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Country> name(@PathParam String name) {
        return countriesService.getByName(name);
    }

    @GET
    @Path("/name-async/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Set<Country>> nameAsync(@PathParam String name) {
        return countriesService.getByNameAsync(name);
    }

    @GET
    @Path("/name-uni/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Set<Country>> nameMutiny(@PathParam String name) {
        return countriesService.getByNameAsUni(name);
    }
	
}