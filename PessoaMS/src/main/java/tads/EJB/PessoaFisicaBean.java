package tads.EJB;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;


import tads.entidade.PessoaFisicaBD;



@Stateless(name = "PessoafBean")
@LocalBean  
//@ValidateOnExecution(type = ExecutableType.ALL)
public class PessoaFisicaBean {
    
    
    @PersistenceContext(name = "AlugaVeiculoWeb", type = TRANSACTION)
    protected static EntityManager em;
    
    public boolean existePessoaF( PessoaFisicaBD pessoaf){
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
    
    public static PessoaFisicaBD consultarPessoaFPorId(Long id) {
    	PessoaFisicaBD pessoaf = em.find(PessoaFisicaBD.class, id);
        System.out.println(pessoaf.getNome());
        return pessoaf;
        
    }
    
    public String imprimeNome(Long id){
    	PessoaFisicaBD pessoaf = em.find(PessoaFisicaBD.class, id);
        
        return pessoaf.getNome();
        
    }
    
    public static PessoaFisicaBD cadastrarPessoaFisica(String nome, String senha,String token) {
		PessoaFisicaBD pessoaf = new PessoaFisicaBD();
		pessoaf.setNome(nome);
		pessoaf.setSenha(senha);
		em.persist(pessoaf);
		return pessoaf;
	}
    
    public static PessoaFisicaBD login(String nome, String senha,String token) {
		String jpql = ("select pf from PessoaFisicaBD pf where pf.nome= :pNome and pf.senha= :pSenha and pf.token= :pToken");
        Query query = em.createQuery(jpql);
        query.setParameter("pNome", nome);
        query.setParameter("pSenha", senha);
        query.setParameter("pToken", token);
        PessoaFisicaBD pessoaf = (PessoaFisicaBD)query.getSingleResult();
		return pessoaf;
	}
    
}
