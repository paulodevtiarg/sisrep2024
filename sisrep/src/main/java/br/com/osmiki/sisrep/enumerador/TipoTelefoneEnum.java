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
public enum TipoTelefoneEnum {
    FONE(0,"Fone"),
    CELULAR(1,"Celular");

    private final int id;
    private final String desc;

    TipoTelefoneEnum(int ordinal, String name) {
            this.id= ordinal;
            this.desc= name;
    }

    public int getId() {
            return id;
    }
    public String getDesc() {
            return desc;
    }	
}
