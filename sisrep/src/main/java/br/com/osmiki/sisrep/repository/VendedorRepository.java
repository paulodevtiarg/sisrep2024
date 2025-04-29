/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.repository;

import br.com.osmiki.sisrep.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leonardo
 */
public interface VendedorRepository  extends JpaRepository<Vendedor, Integer>{

    public Vendedor findByCpf(String cpf);
    

}
