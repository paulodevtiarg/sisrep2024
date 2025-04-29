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
public class Despesa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDespesa;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "tipo_despesa", nullable = false)
    private TipoDespesa tipoDespesa;
    private String numeroDespesa;
    private boolean inativo;

    public Despesa() {
    }

    public Despesa(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Integer getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public String getNumeroDespesa() {
        return numeroDespesa;
    }

    public void setNumeroDespesa(String numeroDespesa) {
        this.numeroDespesa = numeroDespesa;
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }
    
}
