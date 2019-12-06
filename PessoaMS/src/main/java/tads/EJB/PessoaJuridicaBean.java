package tads.EJB;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;

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
    protected static EntityManager em;
    
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
    public static PessoaJuridicaBD criarPessoaj() {

        return new PessoaJuridicaBD();
    }
    
    public static PessoaJuridicaBD atualizaPessoaj(PessoaJuridicaBD pessoaj) {

        pessoaj = em.merge(pessoaj);
        em.flush();
        return pessoaj;
    }
    
    public static PessoaJuridicaBD consultarPessoaJPorId(Long id) {

        return em.find(PessoaJuridicaBD.class, id);
    }
    
    public static PessoaJuridicaBD cadastrarPessoaJuridica(String nome, String senha,String token) {
		PessoaJuridicaBD pessoaj = new PessoaJuridicaBD();
		pessoaj.setNome(nome);
		pessoaj.setSenha(senha);
		em.persist(pessoaj);
		return pessoaj;
	}
    
}
