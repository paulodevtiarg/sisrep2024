/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String ie;
    private String email;
    private String nomeComprador;
    private String obs;
    private boolean enviarPedidoEmail;
    private boolean inativo;
    private boolean bloquearVenda;
    private Integer numeroLoja;
    //regiao
    @ManyToOne
    @JoinColumn(name="regiao", nullable = true)
    private Regiao regiao;
    //vendedor
    @ManyToOne
    @JoinColumn(name="vendedor", nullable = true)
    private Vendedor vendedor;
    //usuario
    @ManyToOne
    @JoinColumn(name="usuario", nullable = true)
    private Usuario usuario;
    //endereco
    @ManyToOne
    @JoinColumn(name="endereco", nullable = true, insertable = true, updatable = true)
    private Endereco endereco;
    //enderecoEntrega
    @ManyToOne
    @JoinColumn(name="endereco_entrega", nullable = true, insertable = false, updatable = false)
    private Endereco enderecoEntrega;
    //enderecoCobranca
    @ManyToOne
    @JoinColumn(name="endereco_cobranca", nullable = true, insertable = false, updatable = false)
    private Endereco enderecoCobranca;
    private BigDecimal debito;
    private Integer sede;
    private Integer situacaoCliente;
    private LocalDateTime dataCadastro;
    private LocalDate dataUltimaCompra;
    private boolean filial;
    private Integer matriz;
    private String codigo;
    private boolean exclusivoVendedor;
    private LocalDateTime dataAlteracao;
    private boolean centroDistribuicao;
    private LocalDate dataFundacao;
    private boolean postoVenda;
    //telefone
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cliente")
    private List<TelefoneCliente> telefones;


    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean isEnviarPedidoEmail() {
        return enviarPedidoEmail;
    }

    public void setEnviarPedidoEmail(boolean enviarPedidoEmail) {
        this.enviarPedidoEmail = enviarPedidoEmail;
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public boolean isBloquearVenda() {
        return bloquearVenda;
    }

    public void setBloquearVenda(boolean bloquearVenda) {
        this.bloquearVenda = bloquearVenda;
    }

    public Integer getNumeroLoja() {
        return numeroLoja;
    }

    public void setNumeroLoja(Integer numeroLoja) {
        this.numeroLoja = numeroLoja;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Endereco getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(Endereco enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public BigDecimal getDebito() {
        return debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public Integer getSede() {
        return sede;
    }

    public void setSede(Integer sede) {
        this.sede = sede;
    }

    public Integer getSituacaoCliente() {
        return situacaoCliente;
    }

    public void setSituacaoCliente(Integer situacaoCliente) {
        this.situacaoCliente = situacaoCliente;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(LocalDate dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
    }

    public boolean isFilial() {
        return filial;
    }

    public void setFilial(boolean filial) {
        this.filial = filial;
    }

    public Integer getMatriz() {
        return matriz;
    }

    public void setMatriz(Integer matriz) {
        this.matriz = matriz;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isExclusivoVendedor() {
        return exclusivoVendedor;
    }

    public void setExclusivoVendedor(boolean exclusivoVendedor) {
        this.exclusivoVendedor = exclusivoVendedor;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public boolean isCentroDistribuicao() {
        return centroDistribuicao;
    }

    public void setCentroDistribuicao(boolean centroDistribuicao) {
        this.centroDistribuicao = centroDistribuicao;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public boolean isPostoVenda() {
        return postoVenda;
    }

    public void setPostoVenda(boolean postoVenda) {
        this.postoVenda = postoVenda;
    }

    public List<TelefoneCliente> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneCliente> telefones) {
        this.telefones = telefones;
    }
    
    
}
