package com.ibm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	

	@POST
	@Path("/pricechanged/{productId}/{newPrice}")
	@Produces(MediaType.TEXT_PLAIN)
	public void pricechanged(@PathParam("productId") String productId, @PathParam("newPrice") String newPrice) {
		System.out.println("Serverless call invoked for product ID : "+productId+", price has changed to "+newPrice);	
		//int statusCode = sendEmailNotificationWithQuarkus(productId, newPrice);		
		//String resp = emailService.sendemail("{ data: 'test json data' }");
       // System.out.println("Serverless Response = "+resp);	
		
		/*
		 * if(statusCode == 201) return "Notification sent!"; else return
		 * "Failure to send notification!";
		 */
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
	      try {

	          URL url = new URL("http://dummy.restapiexample.com/api/v1/create");
	          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	          conn.setDoOutput(true);
	          conn.setRequestMethod("POST");
	          conn.setRequestProperty("Content-Type", "application/json");

	          String input = "{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}";

	          OutputStream os = conn.getOutputStream();
	          os.write(input.getBytes());
	          os.flush();

	          if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	              throw new RuntimeException("Failed : HTTP error code : "
	                  + conn.getResponseCode());
	          }

	          BufferedReader br = new BufferedReader(new InputStreamReader(
	                  (conn.getInputStream())));

	          String output;
	          System.out.println("Output from Server .... \n");
	          while ((output = br.readLine()) != null) {
	              System.out.println(output);
	          }

	          conn.disconnect();

	        } catch (MalformedURLException e) {

	          e.printStackTrace();

	        } catch (IOException e) {

	          e.printStackTrace();

	       }	    
		return "Hello! Notification service is accessible";
	}
	
}