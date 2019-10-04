package tads.EJB;


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

import tads.entidade.PessoaFisicaBD;

@Stateless(name = "PessoafBean")
@LocalBean  
@ValidateOnExecution(type = ExecutableType.ALL)
public class PessoaFisicaBean {
    
    
    @PersistenceContext(name = "AlugaVeiculoWeb", type = TRANSACTION)
    protected static EntityManager em;
    
    public boolean existePessoaF(@NotNull PessoaFisicaBD pessoaf){
    TypedQuery<PessoaFisicaBD> query
                = em.createNamedQuery(PessoaFisicaBD.PessoaFporCpf, PessoaFisicaBD.class);    
        query.setParameter(1, pessoaf.getCpf());
        return !query.getResultList().isEmpty();
    }
    
    //@PermitAll
    public void persistirPessoaF(PessoaFisicaBD pessoaf) {

        em.persist(pessoaf);
    }
    
    @TransactionAttribute(SUPPORTS)
    //@PermitAll
    public static PessoaFisicaBD criarPessoaf() {

        return new PessoaFisicaBD();
    }
    
    public static PessoaFisicaBD atualizaPessoaF(PessoaFisicaBD pessoaf) {

        pessoaf = em.merge(pessoaf);
        em.flush();
        return pessoaf;
    }
    
    public static PessoaFisicaBD consultarPessoaFPorId(@NotNull Long id) {
    	PessoaFisicaBD pessoaf = em.find(PessoaFisicaBD.class, id);
        System.out.println(pessoaf.getNome());
        return pessoaf;
        
    }
    
    public String imprimeNome(@NotNull Long id){
    	PessoaFisicaBD pessoaf = em.find(PessoaFisicaBD.class, id);
        
        return pessoaf.getNome();
        
    }
    
    
}
