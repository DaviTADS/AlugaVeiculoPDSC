package tads.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Funcionario")
@DiscriminatorValue(value="F")
@NamedQueries(
        {
              @NamedQuery(
                    name = FuncionarioBD.FuncionarioPorCargo,
                    query = "SELECT f FROM FuncionarioBD f WHERE f.cargo = ?1"
            )
        }
)

@PrimaryKeyJoinColumn(name="ID_Pessoa", referencedColumnName = "ID_Pessoa")
public class FuncionarioBD extends PessoaBD implements Serializable{
	
	public static final String FuncionarioPorCargo = "FuncionarioPorCargo";
	
	@Column(name = "TXT_CARGO", nullable = false)
    private String cargo;
    
    @Column(name = "TXT_SOBRENOME", length = 60, nullable = false)
    private String sobrenome;
    
    @Column(name = "TXT_CPF", nullable = false)
    private String cpf;
    
    @Column(name = "DT_ENTRADA", nullable = false)
    private Date dataentrada;
    
    @Column(name = "DT_SAIDA", nullable = false)
    private Date datasaida;
    
    @Column(name = "FT_SALARIO", nullable = false)
    private float salario;
    
    @Column(name = "FT_TOKEN", nullable = false)
    private String token;

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

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

	public Date getDataentrada() {
		return dataentrada;
	}

	public void setDataentrada(Date dataentrada) {
		this.dataentrada = dataentrada;
	}

	public Date getDatasaida() {
		return datasaida;
	}

	public void setDatasaida(Date datasaida) {
		this.datasaida = datasaida;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
    

}
