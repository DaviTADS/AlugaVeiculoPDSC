package tads.entidade;



//import alugaveiculoweb.validadores.ValidaFabricante;
//import alugaveiculoweb.validadores.ValidaTipo;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author davi
 */
@Entity
@Table(name = "TB_Veiculo",catalog = "alugaveiculos")
@NamedQueries(
        {
            @NamedQuery(
                    name = VeiculoBD.VeiculoPorTipo,
                    query = "SELECT v FROM Veiculo v WHERE v.tipo = ?1"
            ),
            @NamedQuery(
                    name = VeiculoBD.VeiculoPorMotorista,
                    query = "SELECT v FROM Veiculo v WHERE v.motorista IS NOT NULL"
            )
        }
)
public class VeiculoBD implements Serializable {
 
    public static final String VeiculoPorTipo = "VeiculoPorTipo";
    public static final String VeiculoPorMotorista = "VeiculoPorMotorista";
    
    @Id
    @Column(name = "ID_Veiculo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    
    @Size(max = 50)
    @ElementCollection
    @CollectionTable(name = "TB_Placa",
            joinColumns = @JoinColumn(name = "ID_Veiculo"))
    @Column(name = "TXT_PLACA", table = "TB_Placa", nullable = false)
    private Collection<String> placas;
    
    
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
//    @JoinColumn(name = "ID_Motorista", referencedColumnName = "ID_Pessoa")
//    private Motorista motorista;
    
    @Size(max=4,min=4)
    @NotBlank
    @Column(name = "DT_ANOF", nullable = true)
    protected String anofabricacao;
    
    @NotBlank
    @Size(max = 40)
    @Column(name="TXT_MODELO",length = 40 , nullable = false)
    protected String modelo;
    
    @NotBlank
    @Size(max = 20)
    //@ValidaFabricante
    @Column(name="TXT_FABRICANTE",length = 20 , nullable = false)
    protected String fabricante;
    
    @NotNull
    @Column(name="NUM_CAPACIDADE", nullable = false)
    protected int capacidade;
    
    @NotBlank
    //@ValidaTipo
    @Column(name="TXT_TIPO", nullable = false)
    protected String tipo;
    
    @NotBlank
    @Size(max = 40)
    @Column(name="TXT_PORTE",length = 40 , nullable = false)
    protected String porte;
    
    @NotBlank
    @Size(max = 200)
    @Column(name = "TXT_DESC", length = 200 , nullable = false)
    protected String descricao;

    public Collection<String> getPlacas() {
        return placas;
    }

    public void setPlacas(Collection<String> placas) {
        this.placas = placas;
    }
    
//    public Motorista getMotorista() {
//        return motorista;
//    }
//
//    public void setMotorista(Motorista motorista) {
//        this.motorista = motorista;
//    }

    public String getAnofabricacao() {
        return anofabricacao;
    }

    public void setAnofabricacao(String anofabricacao) {
        this.anofabricacao = anofabricacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    protected Byte imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public Byte getImagem() {
        return imagem;
    }

    public void setImagem(Byte imagem) {
        this.imagem = imagem;
    }

}
