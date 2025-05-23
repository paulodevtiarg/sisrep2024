package br.com.osmiki.sisrep.dtos;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
		
	
	@Column(name = "id_pessoa")
    private Integer idPessoa;
	
    @Basic(optional = false)
    @NotNull
    @NotBlank(message = "Nome obrigatório")
    @Column(name = "nome")
    private String nome;
    
    @Basic(optional = false)
    @NotNull
    @NotBlank(message = "CPF Obrigatório")
    @Column(name = "cpf")
    private String cpf;
    
    @Basic(optional = false)
    @NotNull
    @NotBlank(message = "Senha Obrigatória")
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inativo")
    private boolean inativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proximo_numero_assistencia")
    private int proximoNumeroAssistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proximo_numero_inadimplencia_cliente")
    private int proximoNumeroInadimplenciaCliente;
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proximo_numero_contas_pagar")
    private int proximoNumeroContasPagar;
    
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @NotNull
    @NotBlank(message = "E-Mail Obrigatório")
    @Column(name = "email")
    private String email;
    @Size(max = 20)
    @Column(name = "email_senha")
    private String emailSenha;
    @Column(name = "gerente_fornecedor")
    private Boolean gerenteFornecedor;
    @Column(name = "cliente")
    private Boolean cliente;
    @Column(name = "data_alteracao_senha")
    private LocalDateTime dataAlteracaoSenha;
    
    
    
    public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
    
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	public int getProximoNumeroAssistencia() {
		return proximoNumeroAssistencia;
	}
	public void setProximoNumeroAssistencia(int proximoNumeroAssistencia) {
		this.proximoNumeroAssistencia = proximoNumeroAssistencia;
	}
	public int getProximoNumeroInadimplenciaCliente() {
		return proximoNumeroInadimplenciaCliente;
	}
	public void setProximoNumeroInadimplenciaCliente(int proximoNumeroInadimplenciaCliente) {
		this.proximoNumeroInadimplenciaCliente = proximoNumeroInadimplenciaCliente;
	}
	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public int getProximoNumeroContasPagar() {
		return proximoNumeroContasPagar;
	}
	public void setProximoNumeroContasPagar(int proximoNumeroContasPagar) {
		this.proximoNumeroContasPagar = proximoNumeroContasPagar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailSenha() {
		return emailSenha;
	}
	public void setEmailSenha(String emailSenha) {
		this.emailSenha = emailSenha;
	}
	public Boolean getGerenteFornecedor() {
		return gerenteFornecedor;
	}
	public void setGerenteFornecedor(Boolean gerenteFornecedor) {
		this.gerenteFornecedor = gerenteFornecedor;
	}
	public Boolean getCliente() {
		return cliente;
	}
	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}
	public LocalDateTime getDataAlteracaoSenha() {
		return dataAlteracaoSenha;
	}
	public void setDataAlteracaoSenha(LocalDateTime dataAlteracaoSenha) {
		this.dataAlteracaoSenha = dataAlteracaoSenha;
	}
    
    
    

}
