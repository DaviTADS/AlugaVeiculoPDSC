package tads.EJB;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.TypedQuery;

import tads.entidade.AluguelBD;



@Stateless
//@ValidateOnExecution(type = ExecutableType.ALL)
public class AluguelBean{
	
	
		@PersistenceContext(unitName = "pu")
		private EntityManager em;	
    
		public AluguelBD consultarAluguelPorId(Long id) {

			AluguelBD aluguel = em.find(AluguelBD.class, id);
	        //System.out.println(aluguel.getNome());
	        return aluguel;
	    }
	     
	    @TransactionAttribute(SUPPORTS)
	    public AluguelBD criarAluguel() {

	        return new AluguelBD();
	    }
	    
	    public AluguelBD persistirAluguel(AluguelBD aluguel) {

	        em.persist(aluguel);
	        return aluguel;
	    }

	    public AluguelBD atualizaAluguel(AluguelBD aluguel) {

	        aluguel = em.merge(aluguel);
	        em.flush();
	        return aluguel;
	    } 
	    
	    public boolean existeAluguel(AluguelBD aluguel) {
	        TypedQuery<AluguelBD> query
	                = em.createNamedQuery(AluguelBD.AluguelPorDataInicio, AluguelBD.class);
	        query.setParameter(1, aluguel.getDatainicio());
	        return !query.getResultList().isEmpty();
	    }
	    @TransactionAttribute(SUPPORTS)
	    public List<AluguelBD> aluguelPorPreco(String preco){
	        TypedQuery<AluguelBD> query = em.createNamedQuery(AluguelBD.AluguelPorPreco, AluguelBD.class);
	        query.setParameter(1, preco);
	        
	        List <AluguelBD> alugueis = new ArrayList<AluguelBD>();
	        alugueis = query.getResultList(); 
	        return alugueis;
	    }
	    
	    @TransactionAttribute(SUPPORTS)
	    public List<AluguelBD> aluguelPorDataInicial(Date datainicio){
	        
	        TypedQuery<AluguelBD> query = em.createNamedQuery(AluguelBD.AluguelPorDataInicio, AluguelBD.class);
	        query.setParameter(1, datainicio);
	        
	        return query.getResultList();
	        
	    }
	    @TransactionAttribute(SUPPORTS)
	    public List<AluguelBD> aluguelPorDataFinal(Date datafinal){
	        
	        TypedQuery<AluguelBD> query = em.createNamedQuery(AluguelBD.AluguelPorDataFinal, AluguelBD.class);
	        query.setParameter(1, datafinal);
	        
	        return query.getResultList();
	        
	    }
	    @TransactionAttribute(SUPPORTS)
	    public List<AluguelBD> aluguelPorPessoa(){
	        
	        TypedQuery<AluguelBD> query = em.createNamedQuery(AluguelBD.AluguelPorPessoa, AluguelBD.class);
	        
	        return query.getResultList();
	        
	    }
    
}
