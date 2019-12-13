package tads.entidade;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


/**
 *
 * @author davi
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC_Pessoa",
discriminatorType = DiscriminatorType.STRING, length = 1)
@Table(name = "TB_Pessoa",catalog = "alugaveiculos")
public abstract class PessoaBD implements Serializable {
   
    
    @Id
    @Column(name = "ID_Pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    protected Long id;
    
    
    @ElementCollection
    @CollectionTable(name = "TB_Telefone",
    joinColumns = @JoinColumn(name = "ID_Pessoa"))
    @Column(name = "TXT_TELEFONE", table = "TB_Telefone", nullable = true)
    protected Collection<String> telefones;
    

    @Column(name = "TXT_NOME", length = 30, nullable = false)
    protected String nome;
    

    @Column(name = "TXT_SENHA", length = 8, nullable = false)
    protected String senha;
    
//    @NotNull
//    @Email
    @Column(name="TXT_EMAIL")
    protected String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Collection<String> telefones) {
        this.telefones = telefones;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

 
    
}
