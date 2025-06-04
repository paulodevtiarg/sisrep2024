package br.com.osmiki.sisrep.dtos;

import java.time.LocalDateTime;

import br.com.osmiki.sisrep.model.Nivel;
import br.com.osmiki.sisrep.model.TipoAcesso;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
		
	
	
    private Integer idPessoa;
	    
    @NotNull
    @NotBlank(message = "Nome obrigatório")
    private String nome;    
   
    @NotNull
    @NotBlank(message = "CPF Obrigatório")
    private String cpf;
    
  
    @NotNull
    @NotBlank(message = "Senha Obrigatória")
    private String senha;
   
    @NotNull
    @Column(name = "inativo")
    private boolean inativo;
    
    
    @NotNull
    @Column(name = "proximo_numero_assistencia")
    private int proximoNumeroAssistencia;
       
    @NotNull
    @Column(name = "proximo_numero_inadimplencia_cliente")
    private int proximoNumeroInadimplenciaCliente;
       
    private LocalDateTime dataAlteracao;
 
    @NotNull
    private int proximoNumeroContasPagar;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @NotNull
    @NotBlank(message = "E-Mail Obrigatório")
    private String email;
    
    @Size(max = 20)
    private String emailSenha;
    
    
    private Boolean gerenteFornecedor;
    
  
    private Boolean cliente;
    
    private LocalDateTime dataAlteracaoSenha;
    
    private Nivel nivel; // Você precisaria criar NivelDTO
    
    private Integer id_empresa;
    
    private TipoAcesso tipoacesso;
    
    public TipoAcesso getTipoacesso() {
		return tipoacesso;
	}
	public void setTipoacesso(TipoAcesso tipoacesso) {
		this.tipoacesso = tipoacesso;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
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
