/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.TelefoneFornecedor;
import br.com.osmiki.sisrep.repository.TelefoneFornecedorRepository;
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
//@RequestMapping("/telefones")
public class TelefoneFornecedorService {
    
    private TelefoneFornecedorRepository repository;

    TelefoneFornecedorService(TelefoneFornecedorRepository repository) {
        this.repository = repository;
    }
   // métodos do CRUD aqui
   
    //Listando todos os telefones (GET /telefones)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um telefone especídifo pelo ID (GET /telefones  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    //Criando um novo telefone (POST /telefones)
    //@PostMapping
    public TelefoneFornecedor create(@RequestBody TelefoneFornecedor telefone){
        return repository.save(telefone);
    }
    
    //Atualizando um telefone (PUT /telefones)
    //@PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
            @RequestBody TelefoneFornecedor telefone) {
        return repository.findById(id)
                .map(record -> {
                    record.setDdd(telefone.getDdd());
                    record.setNumero(telefone.getNumero());
                    record.setObs(telefone.getObs());
                    record.setTipoTelefone(telefone.getTipoTelefone());
                    TelefoneFornecedor updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    //Removendo um telefone pelo ID (DELETE /telefones/{id})
    //@DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }  
    
}
