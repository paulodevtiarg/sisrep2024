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
import java.io.Serializable;

/**
 *
 * @author Leonardo
 */
@Entity
public class TipoDespesa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoDespesa;
    private String nome;
    private boolean inativo;

    public TipoDespesa() {
    }

    public TipoDespesa(Integer idTipoDespesa) {
        this.idTipoDespesa = idTipoDespesa;
    }

    public Integer getIdTipoDespesa() {
        return idTipoDespesa;
    }

    public void setIdTipoDespesa(Integer idTipoDespesa) {
        this.idTipoDespesa = idTipoDespesa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }
    
}
