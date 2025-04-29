/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.Endereco;
import br.com.osmiki.sisrep.repository.EnderecoRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo
 */
@RestController
//@RequestMapping("/enderecos")
public class EnderecoService {
    
    private EnderecoRepository repository;

    EnderecoService(EnderecoRepository enderecoRepository) {
        this.repository = enderecoRepository;
    }
    //Listando todos os enderecos (GET /enderecos)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um endereco especídifo pelo ID (GET /enderecos  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    public Endereco create(@RequestBody Endereco objeto){
        return repository.save(objeto);
    }
    
}
