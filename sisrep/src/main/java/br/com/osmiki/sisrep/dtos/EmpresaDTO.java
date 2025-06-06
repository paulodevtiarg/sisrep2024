package br.com.osmiki.sisrep.dtos;


import jakarta.validation.constraints.Email;

public class EmpresaDTO {
	
    private Integer id;
		
   
    private String nome;
    
  
    private String cnpj;
    

    private String logradouro;
    
   
    private String complemento;
    
    
    private String bairro;
    
    
    private MunicipioDTO municipio;
    
    
    private String cep;
    
   
    private String telefone_fixo;
    
   
    private String telefone_celular;
    
    @Email(message = "Email inválido")
    private String email;
    
    
    private String razao_social;
    
   
    private String ie;
	

    
    private String im;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCnpj() {
		return cnpj;
	}



	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}



	public String getLogradouro() {
		return logradouro;
	}



	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}



	public String getComplemento() {
		return complemento;
	}



	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}



	public String getBairro() {
		return bairro;
	}



	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public MunicipioDTO getMunicipio() {
		return municipio;
	}



	public void setMunicipio(MunicipioDTO municipio) {
		this.municipio = municipio;
	}



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getTelefone_fixo() {
		return telefone_fixo;
	}



	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}



	public String getTelefone_celular() {
		return telefone_celular;
	}



	public void setTelefone_celular(String telefone_celular) {
		this.telefone_celular = telefone_celular;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getRazao_social() {
		return razao_social;
	}



	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}



	public String getIe() {
		return ie;
	}



	public void setIe(String ie) {
		this.ie = ie;
	}



	public String getIm() {
		return im;
	}



	public void setIm(String im) {
		this.im = im;
	}
	
	

}
