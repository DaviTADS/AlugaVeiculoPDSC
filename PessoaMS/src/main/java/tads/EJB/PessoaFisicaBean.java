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


import tads.entidade.PessoaFisicaBD;



@Stateless
//@ValidateOnExecution(type = ExecutableType.ALL)
public class PessoaFisicaBean {
	
	
		@PersistenceContext(unitName = "pu")
		private EntityManager em;
    
    public boolean existePessoaF( PessoaFisicaBD pessoaf){
    TypedQuery<PessoaFisicaBD> query
                = em.createNamedQuery(PessoaFisicaBD.PessoaFporCpf, PessoaFisicaBD.class);    
        query.setParameter(1, pessoaf.getCpf());
        return !query.getResultList().isEmpty();
    }
    
    //@PermitAll
    public  PessoaFisicaBD persistirPessoaF(PessoaFisicaBD pessoaf) {
    	
    	for(int i=0;i<3;i++) {
    		System.out.println(pessoaf.getNome().toString());
    		System.out.println(pessoaf.getSenha().toString());
    		//System.out.println(pessoaf.getToken().toString());
    		System.out.println(pessoaf.getEmail().toString());
    		System.out.println("");
    	}
    	
        em.persist(pessoaf);
        return pessoaf;
    }
    
    @TransactionAttribute(SUPPORTS)
    //@PermitAll
    public PessoaFisicaBD criarPessoaf() {

        return new PessoaFisicaBD();
    }
    
    public PessoaFisicaBD atualizaPessoaF(PessoaFisicaBD pessoaf) {

        pessoaf = em.merge(pessoaf);
        em.flush();
        return pessoaf;
    }
    
    public PessoaFisicaBD consultarPessoaFPorId(Long id) {
    	PessoaFisicaBD pessoaf = em.find(PessoaFisicaBD.class, id);
        System.out.println(pessoaf.getNome());
        return pessoaf;
        
    }
    
    public String imprimeNome(Long id){
    	PessoaFisicaBD pessoaf = em.find(PessoaFisicaBD.class, id);
        
        return pessoaf.getNome();
        
    }
    
    public  PessoaFisicaBD cadastrarPessoaFisica(String nome, String senha,String token) {
		PessoaFisicaBD pessoaf = new PessoaFisicaBD();
		pessoaf.setNome(nome);
		pessoaf.setSenha(senha);
		pessoaf.setToken(token);
		em.persist(pessoaf);
		return pessoaf;
	}
    
    public  PessoaFisicaBD login(String nome, String senha,String token) {
		String jpql = ("select pf from PessoaFisicaBD pf where pf.nome= :pNome and pf.senha= :pSenha");
        Query query = em.createQuery(jpql);
        query.setParameter("pNome", nome);
        query.setParameter("pSenha", senha);
        PessoaFisicaBD pessoaf = (PessoaFisicaBD)query.getSingleResult();
        if(pessoaf != null) {
        	pessoaf.setToken(token);
        	atualizaPessoaF(pessoaf);
        }
		return pessoaf;
	}
    
}
