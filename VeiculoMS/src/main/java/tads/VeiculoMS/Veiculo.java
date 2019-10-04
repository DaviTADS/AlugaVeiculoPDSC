package tads.VeiculoMS;

import java.util.Collection;


public class Veiculo {

		protected Long id;
		private Collection<String> placas;
		protected String anofabricacao;
	    protected String modelo;
	    protected String fabricante;
	    protected int capacidade;
	    protected String tipo;
	    protected String porte;
	    protected String descricao;
		
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Collection<String> getPlacas() {
			return placas;
		}
		public void setPlacas(Collection<String> placas) {
			this.placas = placas;
		}
		public String getAnofabricacao() {
			return anofabricacao;
		}
		public void setAnofabricacao(String anofabricacao) {
			this.anofabricacao = anofabricacao;
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
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	    
	    
	
}
