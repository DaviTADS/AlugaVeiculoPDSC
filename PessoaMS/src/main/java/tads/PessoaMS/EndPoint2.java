package tads.PessoaMS;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import tads.EJB.PessoaFisicaBean;
import tads.EJB.PessoaJuridicaBean;
import tads.entidade.PessoaFisicaBD;
import tads.entidade.PessoaJuridicaBD;




/**
 * Root resource (exposed at "PessoaJuridica" path)
 */
@Path("/pessoajuridica")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class EndPoint2 {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
	@EJB
    private PessoaJuridicaBean PessoaJuridicaBean;
	
	@Context
	private HttpServletRequest httpRequest;

	
	
	@GET
    @Path("{id}")
    public Response getPessoaJuridicaById(@PathParam("id") Long id) {
    	
    	return Response.ok(PessoaJuridicaBean.consultarPessoaJPorId(id)).build();
    }
	
	@POST
    @Path("/signup")
	public Response createPessoaJuridica(PessoaJuridica NewUser) {
    	String login = NewUser.getNome();
    	String senha = NewUser.getSenha();
    	String token = NewUser.getToken();
    	PessoaJuridicaBD user = PessoaJuridicaBean.cadastrarPessoaJuridica(login, PasswordUtils.digestPassword(senha), token);
		if (user != null)
			return Response.ok(user).build();
		return Response.status(NOT_FOUND).build();
	}
    
    
    @POST
    @Path("/criarpessoajuridica")
	public Response create(PessoaJuridica NewPessoaj) {
    	String nome = NewPessoaj.getNome();
    	String email = NewPessoaj.getEmail();
    	String cnpj = NewPessoaj.getCnpj();
    	String creditos = NewPessoaj.getCreditos();
    	String razaosocial = NewPessoaj.getRazaosocial();
    	PessoaJuridicaBD pessoaf = PessoaJuridicaBean.criarPessoaj();
    	pessoaf.setNome(nome);
    	pessoaf.setRazaosocial(razaosocial);
    	pessoaf.setEmail(email);
    	pessoaf.setCnpj(cnpj);
    	pessoaf.setCreditos(creditos);
		if (pessoaf != null)
			return Response.ok(pessoaf).build();
		return Response.status(NOT_FOUND).build();
	}
    
    
    @PUT
    @Path("/atualizarpessoajuridica")
    public Response updateVeiculo(PessoaJuridica changepessoaj) {
    	String nome = changepessoaj.getNome();
    	String email = changepessoaj.getEmail();
    	String cnpj = changepessoaj.getCnpj();
    	String creditos = changepessoaj.getCreditos();
    	String razaosocial = changepessoaj.getRazaosocial();
    	PessoaJuridicaBD pessoaj = new PessoaJuridicaBD();
    	pessoaj.setNome(nome);
    	pessoaj.setRazaosocial(razaosocial);
    	pessoaj.setEmail(email);
    	pessoaj.setCnpj(cnpj);
    	pessoaj.setCreditos(creditos);
    	  
		return Response.ok(PessoaJuridicaBean.atualizaPessoaj(pessoaj)).build();    	
    }
    
    
    
   
}
