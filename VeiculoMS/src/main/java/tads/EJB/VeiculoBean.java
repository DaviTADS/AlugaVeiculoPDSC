package tads.EJB;


import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

import tads.entidade.VeiculoBD;

@Stateless(name = "VeiculoBean")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class VeiculoBean {

    @PersistenceContext(name = "AlugaVeiculoWeb", type = TRANSACTION)
    protected static EntityManager em;

    
    public boolean existeVeiculo(@NotNull VeiculoBD veiculo) {
        TypedQuery<VeiculoBD> query
                = em.createNamedQuery(VeiculoBD.VeiculoPorTipo, VeiculoBD.class);
        query.setParameter(1, veiculo.getTipo());
        return !query.getResultList().isEmpty();
    }
    
    
    public static void persistirVeiculo(VeiculoBD veiculo) {

        em.persist(veiculo);
    }
    
    @TransactionAttribute(SUPPORTS)
    public static VeiculoBD criarVeiculo() {

        return new VeiculoBD();
    }

    public static VeiculoBD atualizaVeiculo(VeiculoBD veiculo) {

        veiculo = em.merge(veiculo);
        em.flush();
        return veiculo;
    }

    public static String consultarPorId(@NotNull Long id) {
    	VeiculoBD vei = em.find(VeiculoBD.class, id);
        return vei.getModelo();
    }

    @TransactionAttribute(SUPPORTS)
    public List<VeiculoBD> consultaVeiculosComMotorista() {
        TypedQuery<VeiculoBD> query = em.createNamedQuery(VeiculoBD.VeiculoPorMotorista, VeiculoBD.class);

        return query.getResultList();
    }

    @TransactionAttribute(SUPPORTS)
    public List<VeiculoBD> consultaVeiculosPorTipo(String tipo) {
        TypedQuery<VeiculoBD> query = em.createNamedQuery(VeiculoBD.VeiculoPorTipo, VeiculoBD.class);
        query.setParameter(1, tipo);
        
        return query.getResultList();
    }

}
