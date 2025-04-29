/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.repository;

import br.com.osmiki.sisrep.model.TabelaPreco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo
 */
public interface TabelaPrecoRepository  extends JpaRepository<TabelaPreco, Integer>{

    public TabelaPreco findByDescricao(String descricao);
    

}
