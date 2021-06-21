package com.ibm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Incoming;


@Path("/")
public class ProductPriceResource {
	
//    @Inject
//    @RestClient
//    EmailService emailService;
    

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public void process(String message) {
		System.out.println(
				"Kafka message received with Quarkus reactive and Knative: product-price-updated - Sending email : " + message);
	}
	
	/*
	 * @Incoming("product-price-updated") public String processMessage(String
	 * message) { System.out.
	 * println("Kafka message received in Quarkus reactive: product-price-updated - "
	 * + message); return message; }
	 */  

	@GET
	@Path("/pricechanged/{productId}/{newPrice}")
	@Produces(MediaType.TEXT_PLAIN)
	public void pricechanged(@PathParam("productId") String productId, @PathParam("newPrice") String newPrice) {
		System.out.println("Serverless call invoked for product ID : "+productId+", price has changed to "+newPrice);	
		//int statusCode = sendEmailNotificationWithQuarkus(productId, newPrice);		
		//String resp = emailService.sendemail("test json data");
        // System.out.println("Serverless Response = "+resp);	
		
		/*
		 * if(statusCode == 201) return "Notification sent!"; else return
		 * "Failure to send notification!";
		 */
		
		try {

	          URL url = new URL("http://dummy.restapiexample.com/api/v1/create"); //Add email service URL here
	          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	          conn.setDoOutput(true);
	          conn.setRequestMethod("POST");
	          conn.setRequestProperty("Content-Type", "application/json");

	          String input = "{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}"; //construct email service payload

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
		
		
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello! Notification service is accessible";
	}
	
}