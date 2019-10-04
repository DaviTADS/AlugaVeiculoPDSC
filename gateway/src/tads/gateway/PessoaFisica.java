package tads.gateway;

public class PessoaFisica extends Pessoa {
    
    //public static final String PessoaFporCpf = "PessoaFporCpf";
    //public static final String consultaCreditof = "consultaCreditof";
    
   
   //private List<Aluguel> alugueis; 
   
   
   private String sobrenome;
   private String cpf;
   private String creditos;
   
   
   public String getCpf() {
       return cpf;
   }

   public void setCpf(String cpf) {
       this.cpf = cpf;
   }

   public String getCreditos() {
       return creditos;
   }

   public void setCreditos(String creditos) {
       this.creditos = creditos;
   }

//    public List<Aluguel> getAlugueis() {
//       return alugueis;
//   }
//
//   public void setAlugueis(List<Aluguel> alugueis) {
//       this.alugueis = alugueis;
//   }

   public String getSobrenome() {
       return sobrenome;
   }

   public void setSobrenome(String sobrenome) {
       this.sobrenome = sobrenome;
   }
   
}
