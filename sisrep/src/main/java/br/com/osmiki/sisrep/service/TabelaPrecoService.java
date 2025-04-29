/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.TabelaPreco;
import br.com.osmiki.sisrep.repository.TabelaPrecoRepository;
import jakarta.persistence.EntityExistsException;
import java.time.LocalDateTime;
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
//@RequestMapping("/tabelaprecos")
public class TabelaPrecoService {
    
    private TabelaPrecoRepository repository;

    TabelaPrecoService(TabelaPrecoRepository repository) {
        this.repository = repository;
    }
   // métodos do CRUD aqui
   
    //Listando todos os tabelaprecos (GET /tabelaprecos)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um tabelapreco especídifo pelo ID (GET /tabelaprecos  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    //Criando um novo tabelapreco (POST /tabelaprecos)
    //@PostMapping
    public TabelaPreco create(@RequestBody TabelaPreco tabelapreco){
        
        TabelaPreco teste = repository.findByDescricao(tabelapreco.getDescricao());
        if (teste != null) {
            throw new EntityExistsException("Erro: TabelaPreco já existe no sistema com o Nome "+tabelapreco.getDescricao());
        }
        return repository.save(tabelapreco);
    }
    
    //Atualizando um tabelapreco (PUT /tabelaprecos)
    //@PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
            @RequestBody TabelaPreco tabelapreco) {
        return repository.findById(id)
                .map(record -> {
                    record.setDescricao(tabelapreco.getDescricao());
                    record.setInativo(tabelapreco.isInativo());
                    record.setDataAlteracao(LocalDateTime.now());
                    TabelaPreco updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    //Removendo um tabelapreco pelo ID (DELETE /tabelaprecos/{id})
    //@DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }  
    
}
