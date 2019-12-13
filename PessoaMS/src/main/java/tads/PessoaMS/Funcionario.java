package tads.PessoaMS;

import java.util.Date;



public class Funcionario extends Pessoa {

	
    private String cargo;
    
   
    private String sobrenome;
    
    
    private String cpf;
    
    
    private Date dataentrada;
    
   
    private Date datasaida;
    
    
    private float salario;
    
    
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
