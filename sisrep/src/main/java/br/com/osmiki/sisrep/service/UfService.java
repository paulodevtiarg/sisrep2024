/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.Estado;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import br.com.osmiki.sisrep.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Leonardo
 */
@RestController
//@RequestMapping("/ufs")
public class UfService {
    @Autowired
    private EstadoRepository repository;

    //Listando todos os ufs (GET /ufs)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um uf especídifo pelo ID (GET /ufs  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    public Estado findByUf(String uf){
        return repository.findByUf(uf);
    }
    
    
}
