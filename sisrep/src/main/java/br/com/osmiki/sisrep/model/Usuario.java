/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Integer idPessoa;	

	@Column(name = "nome")
	private String nome;	
	
	@Column(name = "cpf")
	private String cpf;	
	
	@Column(name = "senha")
	private String senha;		
	
	@Column(name = "inativo")
	private boolean inativo;
	
	@Column(name = "proximo_numero_assistencia")
	private int proximoNumeroAssistencia;	
	
	@Column(name = "proximo_numero_inadimplencia_cliente")
	private int proximoNumeroInadimplenciaCliente;
	
	@Column(name = "data_alteracao")
	private LocalDateTime dataAlteracao;	
	
	@Column(name = "proximo_numero_contas_pagar")
	private int proximoNumeroContasPagar;
	
	@Column(name = "email")
	private String email;	
	
	@Column(name = "email_senha")
	private String emailSenha;
	
	@Column(name = "gerente_fornecedor")
	private Boolean gerenteFornecedor;
	
	@Column(name = "cliente")
	private Boolean cliente;
	
	@Column(name = "data_alteracao_senha")
	private LocalDateTime dataAlteracaoSenha;

	@ManyToOne
	@JoinColumn(name = "id_nivel", nullable = false)
	private Nivel nivel;
	
	
	@Column(name="id_empresa")
	private Integer id_empresa;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipoacesso")
	private TipoAcesso tipoacesso;
	
	
	
	
	public TipoAcesso getTipoacesso() {
		return tipoacesso;
	}

	public void setTipoacesso(TipoAcesso tipoacesso) {
		this.tipoacesso = tipoacesso;
	}

	public int getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

	public Usuario() {
	}

	public Usuario(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Usuario(Integer idPessoa, String nome, String cpf, String senha, boolean inativo,
			int proximoNumeroAssistencia, int proximoNumeroInadimplenciaCliente, int proximoNumeroContasPagar,
			Nivel nivel) {
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.inativo = inativo;
		this.proximoNumeroAssistencia = proximoNumeroAssistencia;
		this.proximoNumeroInadimplenciaCliente = proximoNumeroInadimplenciaCliente;
		this.proximoNumeroContasPagar = proximoNumeroContasPagar;
		this.nivel = nivel; 
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
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

	public boolean getInativo() {
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPessoa != null ? idPessoa.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.idPessoa == null && other.idPessoa != null)
				|| (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.osmiki.sisrep.model.Usuario[ idPessoa=" + idPessoa + " ]";
	}

}
