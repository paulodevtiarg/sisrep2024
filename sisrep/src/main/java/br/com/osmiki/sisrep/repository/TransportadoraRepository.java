/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.repository;

import br.com.osmiki.sisrep.model.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo
 */
public interface TransportadoraRepository  extends JpaRepository<Transportadora, Integer>{
    
    //ENVIAR CNPJ COM %
    Transportadora findTopByCnpjLikeOrderByCodigoDesc(String cnpj);
    Transportadora findFirstByCnpjLikeOrderByCodigoDesc( String cnpj);
    Transportadora findByCnpj(String cnpj);
    
    Transportadora findTopByOrderByCodigoDesc();

}
