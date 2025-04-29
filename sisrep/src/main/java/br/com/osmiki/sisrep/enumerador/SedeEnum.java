/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.enumerador;

/**
 *
 * @author Leonardo
 */
public enum SedeEnum {
    PROPRIA(0,"Própria"),
    ALUGADA(1,"Alugada");
    
    private final int id;
    private final String descricao;

    private SedeEnum(int id, String nome) {
        this.id = id;
        this.descricao = nome;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
