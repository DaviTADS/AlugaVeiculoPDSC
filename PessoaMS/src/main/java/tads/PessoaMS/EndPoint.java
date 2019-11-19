package tads.PessoaMS;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tads.EJB.PessoaFisicaBean;
import tads.entidade.PessoaFisicaBD;
//import tads.entidades.UserBD;
//import tads.ms1.PasswordUtils;
//import tads.ms1.User;





/**
 * Root resource (exposed at "PessoaFisica" path)
 */
@Path("pessoafisica")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class EndPoint {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@GET
    @Path("{id}")
    public Response getPessoaFisicaById(@PathParam("id") Long id) {
    	
    	return Response.ok(PessoaFisicaBean.consultarPessoaFPorId(id)).build();
    }
	
//	@POST
//    @Path("/signup")
//	public Response createPessoaFisica(PessoaFisica NewUser) {
//    	String login = NewUser.getNome();
//    	String senha = NewUser.getSenha();
//    	String token = NewUser.getToken();
//    	PessoaFisicaBD user = PessoaFisicaBean.cadastrarPessoaFisica(login, PasswordUtils.digestPassword(senha), token);
//		if (user != null)
//			return Response.ok(user).build();
//		return Response.status(NOT_FOUND).build();
//	}
    
	@POST
    @Path("/criarpessoafisica")
	public Response create(PessoaFisica NewPessoaf) {
    	String nome = NewPessoaf.getNome();
    	String senha = NewPessoaf.getSenha();
    	String token = NewPessoaf.getToken();
    	//PessoaFisicaBD pessoaf = PessoaFisicaBean.cadastrarPessoaFisica(nome, senha, token);
    	PessoaFisicaBD pessoaf = PessoaFisicaBean.criarPessoaf();
    	pessoaf.setNome(nome);
    	pessoaf.setSenha(senha);
    	pessoaf.setToken(token);
    	PessoaFisicaBD pessoafisica = PessoaFisicaBean.persistirPessoaF(pessoaf);
		if (pessoafisica != null)
			return Response.ok(pessoafisica).build();
		return Response.status(NOT_FOUND).build();
	}
	
//    @POST
//    @Path("/criarpessoafisica")
//	public Response create(PessoaFisica NewPessoaf) {
//    	String nome = NewPessoaf.getNome();
//    	String email = NewPessoaf.getEmail();
//    	String cpf = NewPessoaf.getCpf();
//    	String creditos = NewPessoaf.getCreditos();
//    	String sobrenome = NewPessoaf.getSobrenome();
//    	PessoaFisicaBD pessoaf = PessoaFisicaBean.criarPessoaf();
//    	pessoaf.setNome(nome);
//    	pessoaf.setSobrenome(sobrenome);
//    	pessoaf.setEmail(email);
//    	pessoaf.setCpf(cpf);
//    	pessoaf.setCreditos(creditos);
//		if (pessoaf != null)
//			return Response.ok(pessoaf).build();
//		return Response.status(NOT_FOUND).build();
//	}
    
    
    @PUT
    @Path("/atualizarpessoafisica")
    public Response updateVeiculo(PessoaFisica changepessoaf) {
    	String nome = changepessoaf.getNome();
    	String email = changepessoaf.getEmail();
    	String cpf = changepessoaf.getCpf();
    	String creditos = changepessoaf.getCreditos();
    	String sobrenome = changepessoaf.getSobrenome();
    	PessoaFisicaBD pessoaf = new PessoaFisicaBD();
    	pessoaf.setNome(nome);
    	pessoaf.setSobrenome(sobrenome);
    	pessoaf.setEmail(email);
    	pessoaf.setCpf(cpf);
    	pessoaf.setCreditos(creditos);
    	  
		return Response.ok(PessoaFisicaBean.atualizaPessoaF(pessoaf)).build();    	
    }
    
    @POST
	@Path("/loginpessoaf")
    public Response login(PessoaFisica pessoa) {
		
    	String login = pessoa.getNome();
    	String password = pessoa.getSenha();
    	String token = pessoa.getToken();
    	PessoaFisicaBD pessoaf = PessoaFisicaBean.login(login, password, token);
		if (pessoaf!=null) {
			//String token = JwTokenHelper.getInstance().generateToken(login, password);
			return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
		}
		return Response.status(NOT_FOUND).build(); 
		
	
	}
    
    
    
   
}
