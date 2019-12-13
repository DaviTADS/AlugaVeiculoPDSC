package tads.EJB;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tads.entidade.FuncionarioBD;
import tads.entidade.PessoaFisicaBD;


@Stateless
public class FuncionarioBean {

	@PersistenceContext(unitName = "pu")
	private EntityManager em;
	
	
	public boolean existeFuncionario( FuncionarioBD funcionario){
	    TypedQuery<FuncionarioBD> query
	                = em.createNamedQuery(FuncionarioBD.FuncionarioPorCargo, FuncionarioBD.class);    
	        query.setParameter(1, funcionario.getCargo());
	        return !query.getResultList().isEmpty();
	    }
	
	public  FuncionarioBD persistirFuncionario(FuncionarioBD func) {
    	
        em.persist(func);
        return func;
    }
    
    @TransactionAttribute(SUPPORTS)
    public FuncionarioBD criarPessoaf() {

        return new FuncionarioBD();
    }
    
    public FuncionarioBD atualizaFuncionario(FuncionarioBD func) {

        func = em.merge(func);
        em.flush();
        return func;
    }
    
    public FuncionarioBD consultarFuncionarioPorId(Long id) {
    	FuncionarioBD func = em.find(FuncionarioBD.class, id);
        System.out.println(func.getNome());
        return func;
        
    }
	
}
