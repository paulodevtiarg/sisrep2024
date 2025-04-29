/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.osmiki.sisrep.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author leoln
 */
@Entity
public class CondicoesPagamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCondicoesPagamento;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "fornecedor", nullable = false)
    private Fornecedor fornecedor;
    private boolean inativo;
    @ManyToOne
    @JoinColumn(name = "usuario", nullable = true)
    private Usuario usuario;
    private LocalDateTime dataAlteracao;

    public Integer getIdCondicoesPagamento() {
        return idCondicoesPagamento;
    }

    public void setIdCondicoesPagamento(Integer idCondicoesPagamento) {
        this.idCondicoesPagamento = idCondicoesPagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    
}
