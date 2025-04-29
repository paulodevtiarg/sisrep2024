/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.Municipio;
import br.com.osmiki.sisrep.model.Transportadora;
import br.com.osmiki.sisrep.model.Estado;
import br.com.osmiki.sisrep.repository.MunicipioRepository;
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
//@RequestMapping("/municipios")
public class MunicipioService {
    
    private MunicipioRepository repository;

    MunicipioService(MunicipioRepository municipioRepository) {
        this.repository = municipioRepository;
    }
    //Listando todos os municipios (GET /municipios)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um municipio especídifo pelo ID (GET /municipios  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    public List<Municipio> findByEstado(Integer id){
        Estado uf = new Estado(id);
        return repository.findByEstado(uf);
    }
    
    public Municipio create(@RequestBody Municipio objeto){
        return repository.save(objeto);
    }

    public Municipio findByIbge(Integer ibge) {
        return repository.findByIbge(ibge);
    }
    
}
