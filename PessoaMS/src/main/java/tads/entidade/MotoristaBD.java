package tads.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Motorista")
@NamedQueries(
        {
              @NamedQuery(
                    name = MotoristaBD.MotoristaPorReputacao,
                    query = "SELECT m FROM MotoristaBD m WHERE m.habilitacoes = ?1"
            ) 
//            @NamedQuery(
//                    name = Motorista.MotoristaComVeiculo,
//                    query = "SELECT TXT_SOBRENOME FROM TB_Motorista m INNER JOIN TB_Veiculo v ON (m.ID_Pessoa = v.ID_Motorista)"
//            )
        }
)
@DiscriminatorValue(value="M")
@PrimaryKeyJoinColumn(name="ID_Pessoa", referencedColumnName = "ID_Pessoa")
public class MotoristaBD extends PessoaBD implements Serializable {

	
	public static final String MotoristaComVeiculo = "MotoristaComVeiculo";
    public static final String MotoristaPorReputacao = "MotoristaPorReputacao";
    
   
    @Column(name = "TXT_Habilitacao", nullable = false)
    private String habilitacoes;
    
    @Column(name = "TXT_SOBRENOME", length = 60, nullable = false)
    private String sobrenome;
    
    
    @Column(name = "TXT_CPF", nullable = false)
    private String cpf;


	public String getSobrenome() {
		return sobrenome;
	}


	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getHabilitacoes() {
		return habilitacoes;
	}


	public void setHabilitacoes(String habilitacoes) {
		this.habilitacoes = habilitacoes;
	}
    
    
    
}
