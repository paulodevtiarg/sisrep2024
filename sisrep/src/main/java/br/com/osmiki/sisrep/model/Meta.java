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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Leonardo
 */
@Entity
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeta;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDateTime dataAlteracao;
    @ManyToOne
    @JoinColumn(name = "fornecedor", nullable = true)
    private Fornecedor fornecedor;
    @ManyToOne
    @JoinColumn(name = "usuario", nullable = true)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "linha_produto", nullable = true)
    private LinhaProduto linhaProduto;
    @ManyToOne
    @JoinColumn(name = "produto", nullable = true)
    private Produto produto;
    private BigDecimal metaFornecedorPorQtd;
    private BigDecimal metaFornecedorPorValor;
    private BigDecimal metaLinhaProdutoPorQtd;
    private BigDecimal metaLinhaProdutoPorValor;
    private BigDecimal metaProdutoPorQtd;
    private BigDecimal metaProdutoPorValor;
    private boolean metaDaEmpresa;
    @ManyToOne
    @JoinColumn(name = "vendedor", nullable = true)
    private Vendedor vendedor;

    public Meta() {
    }

    public Meta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LinhaProduto getLinhaProduto() {
        return linhaProduto;
    }

    public void setLinhaProduto(LinhaProduto linhaProduto) {
        this.linhaProduto = linhaProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getMetaFornecedorPorQtd() {
        return metaFornecedorPorQtd;
    }

    public void setMetaFornecedorPorQtd(BigDecimal metaFornecedorPorQtd) {
        this.metaFornecedorPorQtd = metaFornecedorPorQtd;
    }

    public BigDecimal getMetaFornecedorPorValor() {
        return metaFornecedorPorValor;
    }

    public void setMetaFornecedorPorValor(BigDecimal metaFornecedorPorValor) {
        this.metaFornecedorPorValor = metaFornecedorPorValor;
    }

    public BigDecimal getMetaLinhaProdutoPorQtd() {
        return metaLinhaProdutoPorQtd;
    }

    public void setMetaLinhaProdutoPorQtd(BigDecimal metaLinhaProdutoPorQtd) {
        this.metaLinhaProdutoPorQtd = metaLinhaProdutoPorQtd;
    }

    public BigDecimal getMetaLinhaProdutoPorValor() {
        return metaLinhaProdutoPorValor;
    }

    public void setMetaLinhaProdutoPorValor(BigDecimal metaLinhaProdutoPorValor) {
        this.metaLinhaProdutoPorValor = metaLinhaProdutoPorValor;
    }

    public BigDecimal getMetaProdutoPorQtd() {
        return metaProdutoPorQtd;
    }

    public void setMetaProdutoPorQtd(BigDecimal metaProdutoPorQtd) {
        this.metaProdutoPorQtd = metaProdutoPorQtd;
    }

    public BigDecimal getMetaProdutoPorValor() {
        return metaProdutoPorValor;
    }

    public void setMetaProdutoPorValor(BigDecimal metaProdutoPorValor) {
        this.metaProdutoPorValor = metaProdutoPorValor;
    }

    public boolean isMetaDaEmpresa() {
        return metaDaEmpresa;
    }

    public void setMetaDaEmpresa(boolean metaDaEmpresa) {
        this.metaDaEmpresa = metaDaEmpresa;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    
}
