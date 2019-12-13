package tads.AluguelMS;

import java.util.Date;
import java.util.List;


public class Aluguel {

	public Long pessoaId;
	public List<Long> veiculoId;
	public Long id;
	public Date datainicio;
	public Date datafinal;
	public String preco;

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
