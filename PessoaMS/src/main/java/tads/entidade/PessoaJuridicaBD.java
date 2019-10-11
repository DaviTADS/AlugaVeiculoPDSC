package tads.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



/**
 *
 * @author davi
 */
@Entity
@Table(name = "TB_PessoaJuridica",catalog = "alugaveiculos")

@NamedQueries(
        {
            @NamedQuery(
                    name = PessoaJuridicaBD.PessoaJporCnpj,
                    query = "SELECT j FROM PessoaJuridica j WHERE j.cnpj = ?1"
            ),
            @NamedQuery(
                    name = PessoaJuridicaBD.consultaCreditoj,
                    query = "SELECT j FROM PessoaJuridica j WHERE j.creditos = ?1"
            )
        }
)

@DiscriminatorValue(value="J")
@PrimaryKeyJoinColumn(name="ID_Pessoa", referencedColumnName = "ID_Pessoa")
public class PessoaJuridicaBD extends PessoaBD implements Serializable {
    
       public static final String PessoaJporCnpj = "PessoaJporCnpj"; 
       public static final String consultaCreditoj = "consultaCreditoj";  
       
//    @OneToMany(mappedBy = "pessoa",fetch = FetchType.LAZY,
//        cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Aluguel> alugueis; 
    
//    @NotBlank
//    @Size(max = 50)
   // @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{com.mycompany.alugaveiculo.PessoaJuridica.razaosocial}")
    @Column(name = "TXT_RAZAOSOCIAL", length = 50, nullable = false)
    private String razaosocial;
    
//    @NotNull
    //@CNPJ
    @Column(name = "TXT_CNPJ", nullable = false)
    private String cnpj;
    
//    @NotBlank
    @Column(name = "TXT_CREDITOS")
    private String creditos;


    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }


}
