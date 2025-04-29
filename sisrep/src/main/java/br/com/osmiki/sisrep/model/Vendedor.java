/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Leonardo
 */
@Entity
public class Vendedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;
    @NotNull
    @NotBlank(message = "Nome obrigatório")
    private String nome;
    @NotNull
    @NotBlank(message = "CPF obrigatório")
    private String cpf;
    private String rg;
    private BigDecimal comissaoPropria;
    private BigDecimal comissaoClienteNaoPropria;
    @ManyToOne
    @JoinColumn(name="endereco", nullable = true)
    private Endereco endereco;
    private Integer gerenteVendedor;
    private String email;
    @ManyToOne
    @JoinColumn(name="usuario", nullable = true, insertable=false, updatable=false)
    private Usuario usuario;
    private boolean vendedorInterno;
    private String senha;
    private boolean inativo;
    private Integer proximoNumeroPedido;
    private Integer proximoNumeroAssistencia;
    private Integer proximoNumeroInadimplenciaCliente;
    private Integer proximoNumeroContasPagar;
    private LocalDateTime dataAlteracao;
    private boolean comissaoApenasFaturamento;
    private String emailSenha;
    private LocalDate dataFimComissao;
    @OneToMany
    private List<TelefoneVendedor> telefoneVendedor;

    public Vendedor() {
    }

    public Vendedor(Integer idPessoa) {
        this.idPessoa = idPessoa;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public BigDecimal getComissaoPropria() {
        return comissaoPropria;
    }

    public void setComissaoPropria(BigDecimal comissaoPropria) {
        this.comissaoPropria = comissaoPropria;
    }

    public BigDecimal getComissaoClienteNaoPropria() {
        return comissaoClienteNaoPropria;
    }

    public void setComissaoClienteNaoPropria(BigDecimal comissaoClienteNaoPropria) {
        this.comissaoClienteNaoPropria = comissaoClienteNaoPropria;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getGerenteVendedor() {
        return gerenteVendedor;
    }

    public void setGerenteVendedor(Integer gerenteVendedor) {
        this.gerenteVendedor = gerenteVendedor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isVendedorInterno() {
        return vendedorInterno;
    }

    public void setVendedorInterno(boolean vendedorInterno) {
        this.vendedorInterno = vendedorInterno;
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

    public Integer getProximoNumeroPedido() {
        return proximoNumeroPedido;
    }

    public void setProximoNumeroPedido(Integer proximoNumeroPedido) {
        this.proximoNumeroPedido = proximoNumeroPedido;
    }

    public Integer getProximoNumeroAssistencia() {
        return proximoNumeroAssistencia;
    }

    public void setProximoNumeroAssistencia(Integer proximoNumeroAssistencia) {
        this.proximoNumeroAssistencia = proximoNumeroAssistencia;
    }

    public Integer getProximoNumeroInadimplenciaCliente() {
        return proximoNumeroInadimplenciaCliente;
    }

    public void setProximoNumeroInadimplenciaCliente(Integer proximoNumeroInadimplenciaCliente) {
        this.proximoNumeroInadimplenciaCliente = proximoNumeroInadimplenciaCliente;
    }

    public Integer getProximoNumeroContasPagar() {
        return proximoNumeroContasPagar;
    }

    public void setProximoNumeroContasPagar(Integer proximoNumeroContasPagar) {
        this.proximoNumeroContasPagar = proximoNumeroContasPagar;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public boolean isComissaoApenasFaturamento() {
        return comissaoApenasFaturamento;
    }

    public void setComissaoApenasFaturamento(boolean comissaoApenasFaturamento) {
        this.comissaoApenasFaturamento = comissaoApenasFaturamento;
    }

    public String getEmailSenha() {
        return emailSenha;
    }

    public void setEmailSenha(String emailSenha) {
        this.emailSenha = emailSenha;
    }

    public LocalDate getDataFimComissao() {
        return dataFimComissao;
    }

    public void setDataFimComissao(LocalDate dataFimComissao) {
        this.dataFimComissao = dataFimComissao;
    }

    public List<TelefoneVendedor> getTelefoneVendedor() {
        return telefoneVendedor;
    }

    public void setTelefoneVendedor(List<TelefoneVendedor> telefoneVendedor) {
        this.telefoneVendedor = telefoneVendedor;
    }
    
    
}
