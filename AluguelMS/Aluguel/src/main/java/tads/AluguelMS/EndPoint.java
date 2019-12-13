package tads.AluguelMS;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import tads.EJB.AluguelBean;
import tads.entidade.AluguelBD;



/**
 * Root resource (exposed at "Aluguel" path)
 */
@Path("/aluguel")
public class EndPoint {
	
	@EJB
    private AluguelBean AluguelBean;
	
	@Context
	private HttpServletRequest httpRequest;
//
//    /**
//     * Method handling HTTP GET requests. The returned object will be sent
//     * to the client as "text/plain" media type.
//     *
//     * @return String that will be returned as a text/plain response.
//     */
	@GET
    @Path("/{id}")
	@Produces(APPLICATION_JSON)
    public Response consultaAluguelPorId(@PathParam("id") Long id) {
    	
    	return Response.ok(AluguelBean.consultarAluguelPorId(id)).build();
    }
    
	@POST
    @Path("/criaraluguel")
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public Response create(Aluguel aluguel) {
    	Date datainicio = aluguel.getDatainicio();
    	Date datafim = aluguel.getDatafinal();
    	String preco = aluguel.getPreco();
    	Long pessoaid = aluguel.getPessoaId();
    	List <Long> veiculos = new ArrayList<Long>(); 
    	veiculos = aluguel.getVeiculoId();
    	AluguelBD newaluguel = AluguelBean.criarAluguel();
    	newaluguel.setDatainicio(datainicio);
    	newaluguel.setDatafinal(datafim);
    	newaluguel.setPreco(preco);
    	newaluguel.setPessoaId(pessoaid);
    	newaluguel.setVeiculoId(veiculos);
    	AluguelBD aluguelnew = AluguelBean.persistirAluguel(newaluguel);
		if (aluguelnew != null)
			return Response.ok(aluguelnew).build();
		return Response.status(NOT_FOUND).build();
	}
	
	@PUT
    @Path("/editaraluguel")
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public Response editaAluguel(Aluguel aluguel) {
    	Date datainicio = aluguel.getDatainicio();
    	Date datafim = aluguel.getDatafinal();
    	String preco = aluguel.getPreco();
    	Long pessoaid = aluguel.getPessoaId();
    	List <Long> veiculos = new ArrayList<Long>(); 
    	veiculos = aluguel.getVeiculoId();
    	AluguelBD newaluguel = AluguelBean.criarAluguel();
    	newaluguel.setDatainicio(datainicio);
    	newaluguel.setDatafinal(datafim);
    	newaluguel.setPreco(preco);
    	newaluguel.setPessoaId(pessoaid);
    	newaluguel.setVeiculoId(veiculos);
    	AluguelBD aluguelnew = AluguelBean.atualizaAluguel(newaluguel);
		if (aluguelnew.getPreco() == newaluguel.getPreco())
			return Response.ok(aluguelnew).build();
		return Response.status(NOT_FOUND).build();
	}

	
//	@GET
//    @Path("/{preco}")
//	@Produces(APPLICATION_JSON)
//	public Response consultaAluguelPorPreco(@PathParam("preco") String preco) {
//    	
//    	return Response.ok(AluguelBean.aluguelPorPreco(preco)).build();
//    }
	   
    
}
