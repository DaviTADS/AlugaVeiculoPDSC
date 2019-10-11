package tads.gateway;

public class PessoaJuridica extends Pessoa {

	// public static final String PessoaJporCnpj = "PessoaJporCnpj";
	// public static final String consultaCreditoj = "consultaCreditoj";

	// private List<Aluguel> alugueis;

	private String razaosocial;
	private String cnpj;
	private String creditos;
	private String token; 
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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
