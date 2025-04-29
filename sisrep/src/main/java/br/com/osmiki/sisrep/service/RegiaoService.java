/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.Regiao;
import br.com.osmiki.sisrep.repository.RegiaoRepository;
import jakarta.persistence.EntityExistsException;
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
//@RequestMapping("/regiaos")
public class RegiaoService {
    
    private RegiaoRepository repository;

    RegiaoService(RegiaoRepository repository) {
        this.repository = repository;
    }
   // métodos do CRUD aqui
   
    //Listando todos os regiaos (GET /regiaos)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um regiao especídifo pelo ID (GET /regiaos  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    //Criando um novo regiao (POST /regiaos)
    //@PostMapping
    public Regiao create(@RequestBody Regiao regiao){
        
        Regiao teste = repository.findByNome(regiao.getNome());
        if (teste != null) {
            throw new EntityExistsException("Erro: Regiao já existe no sistema com o Nome "+regiao.getNome());
        }
        return repository.save(regiao);
    }
    
    //Atualizando um regiao (PUT /regiaos)
    //@PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
            @RequestBody Regiao regiao) {
        return repository.findById(id)
                .map(record -> {
                    record.setNome(regiao.getNome());
                    record.setInativo(regiao.isInativo());
                    Regiao updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    //Removendo um regiao pelo ID (DELETE /regiaos/{id})
    //@DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }  
    
}
