package tads.EJB;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.TypedQuery;

import tads.entidade.MotoristaBD;
import tads.entidade.PessoaFisicaBD;



@Stateless
//@ValidateOnExecution(type = ExecutableType.ALL)
public class MotoristaBean {
	
	
		@PersistenceContext(unitName = "pu")
		private EntityManager em;
    
		 public boolean existeMotorista(MotoristaBD motorista){
			    TypedQuery<MotoristaBD> query
			                = em.createNamedQuery(MotoristaBD.MotoristaPorReputacao, MotoristaBD.class);    
			        query.setParameter(1, motorista.getHabilitacoes());
			        return !query.getResultList().isEmpty();
			    }
			    
			    public void persistirMotorista(MotoristaBD motorista) {

			        em.persist(motorista);
			    }
			    
			    @TransactionAttribute(SUPPORTS)
			    public MotoristaBD criarMotorista() {

			        return new MotoristaBD();
			    }
			    
			    public MotoristaBD atualizaMotorista(MotoristaBD motorista) {

			        motorista = em.merge(motorista);
			        em.flush();
			        return motorista;
			    }
			    
			    public MotoristaBD consultarMotoristaPorId(Long id) {

			        return em.find(MotoristaBD.class, id);
			    }
			    
			  
}
