package tads.gateway;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import tads.util.JwTokenHelper;
import tads.jwtConfiguration.JsonTokenNeeded;

@Path("/")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class Gateway {
	
	@Context
	private HttpServletRequest httpRequest;

    @GET
    @Path("/all_users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allUsers() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/ms1/api/users/all");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response;
    }
    
    @POST
    @Path("/new_user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User newUser) {
    	
    	String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer".length()).trim();
        System.out.println(token);
        
        
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/ms1/api/users/signup");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    }
    
//    @POST
//	@Path("/login")
//    public Response login(User user) {
//		
//    		
//		
//		String token = JwTokenHelper.getInstance().generateToken(login, password);
//		return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
//		
//		//return Response.status(NOT_FOUND).build(); 
//	}
    
}
