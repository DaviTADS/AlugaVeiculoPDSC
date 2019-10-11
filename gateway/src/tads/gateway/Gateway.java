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
import javax.ws.rs.PUT;
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
    @Path("/new_pessoafisica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(PessoaFisica newUser) {
    	
    	String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer".length()).trim();
        newUser.setToken(token);
        System.out.println(token);
        
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoafisica/signup");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    }
    
    @POST
    @Path("/new_pessoajuridica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(PessoaJuridica newUser) {
    	
    	String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer".length()).trim();
        newUser.setToken(token);
        System.out.println(token);
        
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoajuridica/signup");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    }
    
    @GET
    @Path("/veiculos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getVeiculobyId(Veiculo veiculo) {
    	
        
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/VeiculoMS/api/veiculos/{id}");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(veiculo, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    }
    
    @POST
    @Path("/criarveiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVeiculo(Veiculo veiculo) {
    	
        
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/VeiculoMS/api/criarveiculo");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(veiculo, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    }
    
    @PUT
    @Path("/atualizarveiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVeiculo(Veiculo veiculo) {
    	
        
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/VeiculoMS/api/atualizarveiculo");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(veiculo, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    }
    
    
//    @POST
//	@Path("/login")
//    public Response login(User Pessoa) {
//		
//    		
//		
//		
//	}
    
}
