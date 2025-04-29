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
import java.math.BigDecimal;

/**
 *
 * @author Leonardo
 */
@Entity
public class ProdutoTabPreco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProdutoTabPreco;
    @ManyToOne
    @JoinColumn(name = "produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "tabela_preco")
    private TabelaPreco tabelaPreco;
    private BigDecimal preco;
}
