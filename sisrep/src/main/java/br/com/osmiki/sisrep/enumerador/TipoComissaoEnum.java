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
public enum TipoComissaoEnum {
    FATURAMENTO(0, "Faturamento"),
    LIQUIDEZ(1, "Liquidez");
    
    private final int id;
    private final String tipo;

    private TipoComissaoEnum(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
}
