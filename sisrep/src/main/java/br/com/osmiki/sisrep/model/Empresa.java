package br.com.osmiki.sisrep.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "empresa")
public class Empresa {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa")
    private Integer id;
		
    @Column(name = "nome_empresa")
    private String nome;
    
    @Column(name = "cnpj")
    private String cnpj;
    
    @Column(name = "logradouro")
    private String logradouro;
    
    @Column(name = "complemento")
    private String complemento;
    
    @Column(name = "bairro")
    private String bairro;
    
    @ManyToOne
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio municipio;
    
    @Column(name = "cep")
    private String cep;
    
    @Column(name = "telefone_fixo")
    private String telefone_fixo;
    
    @Column(name = "telefone_celular")
    private String telefone_celular;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "razao_social")
    private String razao_social;
    
    @Column(name = "ie")
    private String ie;
	

    @Column(name = "im")
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


	public Municipio getMunicipio() {
		return municipio;
	}


	public void setMunicipio(Municipio municipio) {
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
