package com.ibm;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/")
public class ProductPriceResource {
	
    @Inject
    @RestClient
    EmailService emailService;
    
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
		String resp = emailService.sendemail("test json data");
         System.out.println("Serverless Response = "+resp);	
		
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
	
}