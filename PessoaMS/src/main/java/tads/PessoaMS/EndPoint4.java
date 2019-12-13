package tads.PessoaMS;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.Date;

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

import tads.EJB.MotoristaBean;
import tads.EJB.PessoaFisicaBean;
import tads.EJB.FuncionarioBean;
import tads.entidade.FuncionarioBD;
import tads.entidade.PessoaFisicaBD;
//import tads.entidades.UserBD;
//import tads.ms1.PasswordUtils;
//import tads.ms1.User;





/**
 * Root resource (exposed at "PessoaFisica" path)
 */
@Path("/funcionario")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class EndPoint4 {
	
	@EJB
    private FuncionarioBean FuncionarioBean;
	
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
    public Response getFuncionarioById(@PathParam("id") Long id) {
    	
    	return Response.ok(FuncionarioBean.consultarFuncionarioPorId(id)).build();
    }
	
    
	@POST
    @Path("/criarfuncionario")
	public Response createFuncionario(Funcionario func) {
    	String nome = func.getNome();
    	String sobrenome = func.getSobrenome();
    	String senha = func.getSenha();
    	String cargo = func.getCargo();
    	String email = func.getEmail();
    	String cpf = func.getCpf();
    	float salario = func.getSalario();
    	Date dataentrada = func.getDataentrada();
    	FuncionarioBD funcionario = FuncionarioBean.criarfuncionario();
    	funcionario.setNome(nome);
    	funcionario.setSenha(senha);
    	funcionario.setEmail(email);
    	funcionario.setSobrenome(sobrenome);
    	funcionario.setCargo(cargo);
    	funcionario.setSalario(salario);
    	funcionario.setDataentrada(dataentrada);
    	funcionario.setCpf(cpf);
    	FuncionarioBD newfuncionario = FuncionarioBean.persistirFuncionario(funcionario);
		if (newfuncionario != null)
			return Response.ok(newfuncionario).build();
		return Response.status(NOT_FOUND).build();
	}
    
    @PUT
    @Path("/editarfuncionario")
    public Response updateFuncionario(Funcionario func) {
    	String nome = func.getNome();
    	String sobrenome = func.getSobrenome();
    	String senha = func.getSenha();
    	String cargo = func.getCargo();
    	String email = func.getEmail();
    	String cpf = func.getCpf();
    	float salario = func.getSalario();
    	Date dataentrada = func.getDataentrada();
    	FuncionarioBD funcionario = FuncionarioBean.criarfuncionario();
    	funcionario.setNome(nome);
    	funcionario.setSenha(senha);
    	funcionario.setEmail(email);
    	funcionario.setSobrenome(sobrenome);
    	funcionario.setCargo(cargo);
    	funcionario.setSalario(salario);
    	funcionario.setDataentrada(dataentrada);
    	funcionario.setCpf(cpf);
    	  
		return Response.ok(FuncionarioBean.atualizaFuncionario(funcionario)).build();    	
    }
    
    @POST
	@Path("/loginfuncionario")
    public Response login(Funcionario func) {
		
    	String login = func.getNome();
    	String password = func.getSenha();
    	String token = func.getToken();
    	FuncionarioBD funcionario = FuncionarioBean.login(login, password, token);
		if (funcionario!=null) {
			return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
		}
		return Response.status(NOT_FOUND).build(); 
		
	
	}
    
    
    
   
}
