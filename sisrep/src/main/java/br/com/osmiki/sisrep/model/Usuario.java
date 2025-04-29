/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Basic(optional = false)
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
    @Column(name = "email")
    private String email;
    @Size(max = 20)
    @Column(name = "email_senha")
    private String emailSenha;
    @Column(name = "gerente_fornecedor")
    private Boolean gerenteFornecedor;
    @Column(name = "cliente")
    private Boolean cliente;
    private LocalDateTime dataAlteracaoSenha;

    public Usuario() {
    }

    public Usuario(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Usuario(Integer idPessoa, String nome, String cpf, String senha, boolean inativo, int proximoNumeroAssistencia, int proximoNumeroInadimplenciaCliente, int proximoNumeroContasPagar) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.inativo = inativo;
        this.proximoNumeroAssistencia = proximoNumeroAssistencia;
        this.proximoNumeroInadimplenciaCliente = proximoNumeroInadimplenciaCliente;
        this.proximoNumeroContasPagar = proximoNumeroContasPagar;
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
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.osmiki.sisrep.model.Usuario[ idPessoa=" + idPessoa + " ]";
    }
    
}
