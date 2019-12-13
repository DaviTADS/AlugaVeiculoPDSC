package tads.PessoaMS;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tads.EJB.PessoaFisicaBean;
import tads.EJB.MotoristaBean;
import tads.entidade.MotoristaBD;
import tads.entidade.PessoaFisicaBD;

//import tads.entidades.UserBD;
//import tads.ms1.PasswordUtils;
//import tads.ms1.User;





/**
 * Root resource (exposed at "PessoaFisica" path)
 */
@Path("/motorista")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class EndPoint3 {
	
	@EJB
    private MotoristaBean MotoristaBean;
	
	@Context
	private HttpServletRequest httpRequest;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@GET
    @Path("/{id}")
    public Response getMotoristaById(@PathParam("id") Long id) {
    	
    	return Response.ok(MotoristaBean.consultarMotoristaPorId(id)).build();
    	
    }
	
    
	@POST
    @Path("/criarmotorista")
	public Response create(Motorista motorista) {
    	String nome = motorista.getNome();
    	String cpf = motorista.getCpf();
    	String sobrenome = motorista.getSobrenome();
    	String habilitacoes = motorista.getHabilitacoes();
    	String senha = motorista.getSenha();
    	String email = motorista.getEmail();
    	MotoristaBD newmot = MotoristaBean.criarMotorista();
    	newmot.setNome(nome);
    	newmot.setSenha(senha);
    	newmot.setEmail(email);
    	newmot.setCpf(cpf);
    	newmot.setHabilitacoes(habilitacoes);
    	newmot.setSobrenome(sobrenome);
    	MotoristaBD pessoafisica = MotoristaBean.persistirMotorista(newmot);
		if (pessoafisica != null)
			return Response.ok(pessoafisica).build();
		return Response.status(NOT_FOUND).build();
	}
	
    
    
    @PUT
    @Path("/editamotorista")
    public Response updateMotorista(Motorista motorista) {
    	String nome = motorista.getNome();
    	String cpf = motorista.getCpf();
    	String sobrenome = motorista.getSobrenome();
    	String habilitacoes = motorista.getHabilitacoes();
    	String senha = motorista.getSenha();
    	String email = motorista.getEmail();
    	MotoristaBD newmot = MotoristaBean.criarMotorista();
    	newmot.setNome(nome);
    	newmot.setSenha(senha);
    	newmot.setEmail(email);
    	newmot.setCpf(cpf);
    	newmot.setHabilitacoes(habilitacoes);
    	newmot.setSobrenome(sobrenome);
    	
    	return Response.ok(MotoristaBean.atualizaMotorista(newmot)).build();    	
    }
    
     
   
}
