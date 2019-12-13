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

    
    @POST
    @Path("/new_pessoafisica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(PessoaFisica newUser) {
    	
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoafisica/criarpessoafisica");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok(newUser.getToken()).build();
    }
    
    @GET
    @Path("/getpessoafisica/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPessoaFisicaByID(PessoaFisica pessoaf) {
    	
    	Long pessoaid = pessoaf.getId();
    	
    	Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoafisica/{pessoaid}");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(pessoaf, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok(pessoaf.getToken()).build();
    	
    }
    
    @POST
    @Path("/editapessoafisica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editaPessoaf(PessoaFisica changeUser) {
    	
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoafisica/atualizarpessoafisica");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(changeUser, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok(changeUser.getToken()).build();
    }

    @POST
    @Path("/loginpessoaf")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginpessoaf(PessoaFisica pessoaf) {
    	
    	String token = JwTokenHelper.getInstance().generateToken(pessoaf.nome, pessoaf.getSenha());
        pessoaf.setToken(token);
        System.out.println(token);
    	
    	Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoafisica/loginpessoaf");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(pessoaf, MediaType.APPLICATION_JSON));
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok(token).build();
    	
    }
    
    @POST
    @Path("/new_pessoajuridica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPessoaJuridica(PessoaJuridica newUser) {
    	
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoajuridica/criarpessoajuridica");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok(newUser.getToken()).build();
    }
    
    @POST
    @Path("/editapessoajuridica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editaPessoaJuridica(PessoaJuridica newUser) {
    	
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoajuridica/atualizarpessoajuridica");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok(newUser.getToken()).build();
    }
    
    @POST
    @Path("/loginpessoaj")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginpessoaj(PessoaJuridica pessoaj) {
    	
    	String token = JwTokenHelper.getInstance().generateToken(pessoaj.nome, pessoaj.getSenha());
        pessoaj.setToken(token);
        System.out.println(token);
    	
    	Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoajuridica/loginpessoaj");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(pessoaj, MediaType.APPLICATION_JSON));
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok(token).build();
    	
    }
    
    @GET
    @Path("/getpessoajuridica/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPessoaJuridicaByID(PessoaJuridica pessoaj) {
    	
    	Long pessoaid = pessoaj.getId();
    	
    	Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/pessoajuridica/{pessoaid}");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(pessoaj, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok(pessoaj.getToken()).build();
    	
    }
    
    
    @GET
    @Path("/veiculos")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getVeiculobyId(Veiculo veiculo) {
    	
    	Long veiculoid = veiculo.getId();
        
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/VeiculoMS/api/veiculos/{veiculoid}");
		
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
    
    
    @POST
    @Path("/new_motorista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMotorista(Motorista newMotorista) {
    	
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/motorista/criarmotorista");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newMotorista, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    }
    
    @POST
    @Path("/new_funcionario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFuncionario(Funcionario newFuncionario) {
    	
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/funcionario/criarfuncionario");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newFuncionario, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok(newFuncionario.getToken()).build();
    }
    
    
    @POST
    @Path("/new_aluguel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAluguel(Aluguel newAluguel) {
    	
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/Aluguel/api/aluguel/criaraluguel");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newAluguel, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    }
    
    @POST
    @Path("/edita_aluguel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaAluguel(Aluguel changealuguel) {
    	
        Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/Aluguel/api/aluguel/editaraluguel");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(changealuguel, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    }
    
    @GET
    @Path("/getaluguel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAluguelByID(Aluguel aluguel) {
    	
    	Long aluguelId = aluguel.getId();
    	
    	Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/PessoaMS/api/aluguel/{aluguelId}");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(aluguel, MediaType.APPLICATION_JSON));
		 
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));		
    	
		return Response.ok().build();
    	
    }
    
}
