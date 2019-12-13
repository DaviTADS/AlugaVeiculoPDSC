package tads.EJB;


import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
//import  javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import  javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;


import tads.entidade.VeiculoBD;

@Stateless(name = "VeiculoBean")
@LocalBean
public class VeiculoBean {

	@PersistenceContext(unitName = "pu")
	private EntityManager em;

    
    public boolean existeVeiculo(VeiculoBD veiculo) {
        TypedQuery<VeiculoBD> query
                = em.createNamedQuery(VeiculoBD.VeiculoPorTipo, VeiculoBD.class);
        query.setParameter(1, veiculo.getTipo());
        return !query.getResultList().isEmpty();
    }
    
    
    public  void persistirVeiculo(VeiculoBD veiculo) {

        em.persist(veiculo);
    }
    
//    @TransactionAttribute(SUPPORTS)
    public  VeiculoBD criarVeiculo() {

        return new VeiculoBD();
    }

    public  VeiculoBD atualizaVeiculo(VeiculoBD veiculo) {

        veiculo = em.merge(veiculo);
        em.flush();
        return veiculo;
    }

    public  String consultarPorId(Long id) {
    	VeiculoBD vei = em.find(VeiculoBD.class, id);
        return vei.getModelo();
    }

//    @TransactionAttribute(SUPPORTS)
    public List<VeiculoBD> consultaVeiculosComMotorista() {
        TypedQuery<VeiculoBD> query = em.createNamedQuery(VeiculoBD.VeiculoPorMotorista, VeiculoBD.class);

        return query.getResultList();
    }

//    @TransactionAttribute(SUPPORTS)
    public List<VeiculoBD> consultaVeiculosPorTipo(String tipo) {
        TypedQuery<VeiculoBD> query = em.createNamedQuery(VeiculoBD.VeiculoPorTipo, VeiculoBD.class);
        query.setParameter(1, tipo);
        
        return query.getResultList();
    }

}
