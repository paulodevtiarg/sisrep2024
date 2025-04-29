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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Leonardo
 */
@Entity
public class TabelaPreco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTabela;
    @NotNull
    @NotBlank(message = "Nome obrigatório")
    private String descricao;
    private boolean inativo;
    private LocalDateTime dataAlteracao;

    public TabelaPreco() {
    }

    public TabelaPreco(Integer idTabela) {
        this.idTabela = idTabela;
    }

    public Integer getIdTabela() {
        return idTabela;
    }

    public void setIdTabela(Integer idTabela) {
        this.idTabela = idTabela;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    
}
