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
import java.io.Serializable;

/**
 *
 * @author Leonardo
 */
@Entity
public class LinhaProduto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLinhaProduto;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "fornecedor")
    private Fornecedor fornecedor;
    private boolean inativo;

    public LinhaProduto() {
    }

    public LinhaProduto(Integer idLinhaProduto) {
        this.idLinhaProduto = idLinhaProduto;
    }

    public Integer getIdLinhaProduto() {
        return idLinhaProduto;
    }

    public void setIdLinhaProduto(Integer idLinhaProduto) {
        this.idLinhaProduto = idLinhaProduto;
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
    
}
