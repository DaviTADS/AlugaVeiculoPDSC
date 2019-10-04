package tads.VeiculoMS;

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

import tads.EJB.VeiculoBean;
import tads.entidade.VeiculoBD;


/**
 * Root resource (exposed at "veiculo" path)
 */
@Path("veiculo")
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
    public Response getVeiculoById(@PathParam("id") Long id) {
    	
    	return Response.ok(VeiculoBean.consultarPorId(id)).build();
    }
    
    
    @POST
    @Path("/criarveiculo")
	public Response create(VeiculoBD Newveiculo) {
    	String modelo = Newveiculo.getModelo();
    	String descricao = Newveiculo.getDescricao();
    	String anofabricacao = Newveiculo.getAnofabricacao();
    	VeiculoBD veiculo = VeiculoBean.criarVeiculo();
    	veiculo.setModelo(modelo);
    	veiculo.setAnofabricacao(anofabricacao);
    	veiculo.setDescricao(descricao);
    	VeiculoBean.persistirVeiculo(veiculo);
		if (veiculo != null)
			return Response.ok(veiculo).build();
		return Response.status(NOT_FOUND).build();
	}
    
    
    @PUT
    public Response updateUser(Veiculo changeVeiculo) {
    	  VeiculoBD veiculo = new VeiculoBD();
    	  String descricao = changeVeiculo.getDescricao();
    	  String anofabricacao = changeVeiculo.getAnofabricacao();
    	  veiculo.setDescricao(descricao);
    	  veiculo.setAnofabricacao(anofabricacao);
    	  
		return Response.ok(VeiculoBean.atualizaVeiculo(veiculo)).build();    	
    }
    
   
}
