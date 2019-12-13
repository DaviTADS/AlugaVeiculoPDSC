package tads.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_Aluguel")
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = AluguelBD.AluguelPorPessoa,
                    query = "SELECT a.ID_Aluguel, a.DT_INICIO, a.DT_FINAL, a.TXT_PRECO FROM TB_Aluguel a INNER JOIN TB_Pessoa p ON (a.ID_Pessoa = p.ID_Pessoa)"                    
            )
        }
)
@NamedQueries(
        {
            @NamedQuery(
                    name = AluguelBD.AluguelPorDataInicio,
                    query = "Select a FROM AluguelBD a WHERE a.datainicio = ?1"
            ),
            @NamedQuery(
                    name = AluguelBD.AluguelPorPreco,
                    query = "Select a FROM AluguelBD a WHERE a.preco = ?1"
            ),
            @NamedQuery(
                    name = AluguelBD.AluguelPorDataFinal,
                    query = "Select a FROM AluguelBD a WHERE a.datafinal = ?1"
            )
        }

)
public class AluguelBD implements Serializable{
	
	public static final String AluguelPorDataInicio = "AluguelPorDataInicio";
    public static final String AluguelPorDataFinal = "AluguelPorDataFinal";
    public static final String AluguelPorPreco = "AluguelPorPreco";
    public static final String AluguelPorPessoa = "AluguelPorPessoa";
    
 

@Column(name = "ID_Pessoa")
private Long pessoaId;


@Column(name = "ID_Veiculo")
private List<Long> veiculoId;


@Id
@Column(name = "ID_Aluguel")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Temporal(TemporalType.DATE)
@Column(name = "DT_INICIO", nullable = false)
private Date datainicio;

@Temporal(TemporalType.DATE)
@Column(name = "DT_FINAL", nullable = false)
private Date datafinal;

@Column(name="TXT_PRECO",length = 20, nullable = false)
private String preco;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
    
    public Long getPessoaId() {
    	return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
    	this.pessoaId = pessoaId;
    }

    public List<Long> getVeiculoId() {
    	return veiculoId;
    }

    public void setVeiculoId(List<Long> veiculoId) {
    	this.veiculoId = veiculoId;
    }

}
