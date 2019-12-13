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
import tads.entidade.PessoaJuridicaBD;
import tads.entidade.PessoaJuridicaBD;

/**
 *
 * @author davi
 */
@Stateless
@LocalBean  
//@ValidateOnExecution(type = ExecutableType.ALL)
public class PessoaJuridicaBean {
 
    
	@PersistenceContext(unitName = "pu")
	private EntityManager em;
    
    public boolean existePessoaJ(PessoaJuridicaBD pessoaj){
    TypedQuery<PessoaJuridicaBD> query
                = em.createNamedQuery(PessoaJuridicaBD.PessoaJporCnpj, PessoaJuridicaBD.class);    
        query.setParameter(1, pessoaj.getCnpj());
        return !query.getResultList().isEmpty();
    }
    
    public void persistirPessoaJ(PessoaJuridicaBD pessoaj) {

        em.persist(pessoaj);
    }
    
    @TransactionAttribute(SUPPORTS)
    public PessoaJuridicaBD criarPessoaj() {

        return new PessoaJuridicaBD();
    }
    
    public PessoaJuridicaBD atualizaPessoaj(PessoaJuridicaBD pessoaj) {

        pessoaj = em.merge(pessoaj);
        em.flush();
        return pessoaj;
    }
    
    public PessoaJuridicaBD consultarPessoaJPorId(Long id) {

        return em.find(PessoaJuridicaBD.class, id);
    }
    
    public  PessoaJuridicaBD cadastrarPessoaJuridica(String nome, String senha,String token) {
		PessoaJuridicaBD pessoaj = new PessoaJuridicaBD();
		pessoaj.setNome(nome);
		pessoaj.setSenha(senha);
		em.persist(pessoaj);
		return pessoaj;
	}
    
    public  PessoaJuridicaBD login(String nome, String senha,String token) {
		String jpql = ("select pj from PessoaJuridicaBD pj where pj.nome= :pNome and pj.senha= :pSenha");
        Query query = em.createQuery(jpql);
        query.setParameter("pNome", nome);
        query.setParameter("pSenha", senha);
        PessoaJuridicaBD pessoaj = (PessoaJuridicaBD)query.getSingleResult();
        if(pessoaj != null) {
        	pessoaj.setToken(token);
        	atualizaPessoaj(pessoaj);
        }
		return pessoaj;
	}
    
}
