/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.repository;

import br.com.osmiki.sisrep.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Leonardo
 */
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario>{
    
    Usuario findByCpf(String cpf);
    
    Usuario findByNome(String nome);

    public Optional<Usuario> findByCpfAndSenha(String cpf, String senha);
    
}
