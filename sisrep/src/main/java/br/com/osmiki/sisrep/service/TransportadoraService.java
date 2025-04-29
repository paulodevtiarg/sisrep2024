/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.Transportadora;
import br.com.osmiki.sisrep.repository.EnderecoRepository;
import br.com.osmiki.sisrep.repository.TransportadoraRepository;
import br.com.osmiki.sisrep.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo
 */
@RestController
//@RequestMapping("/transportadoras")
public class TransportadoraService {
    
    private TransportadoraRepository repository;
    private EnderecoRepository enderecoRepository;
    private UsuarioRepository usuarioRepository;

    TransportadoraService(TransportadoraRepository transportadoraRepository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.repository = transportadoraRepository;
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }
    
    /*@GetMapping(value = "/lista")
    public String getAll(){
        
        return "transportadora/ListaTransportadoras";
    }*/
    
   // métodos do CRUD aqui
   
    //Listando todos os transportadoras (GET /transportadoras)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um transportadora especídifo pelo ID (GET /transportadoras  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    //Criando um novo transportadora (POST /transportadoras)
    //@PostMapping
    public Transportadora create(@RequestBody Transportadora transportadora){
        
        Transportadora teste = repository.findByCnpj(transportadora.getCnpj());
        if (teste != null) {
            throw new EntityExistsException("Erro: Transportadora já existe no sistema com o CNPJ "+transportadora.getCnpj());
        }
        
        String codigo = "000/00";
        String cnpj = transportadora.getCnpj().substring(0, 10);
        Transportadora max = repository.findTopByCnpjLikeOrderByCodigoDesc(cnpj+"%");
        if (max != null) {
            codigo = maxCodigoFilial(max.getCodigo());
            transportadora.setCodigo(codigo);
        } else {
            max = repository.findTopByOrderByCodigoDesc();
            if (max != null) {
                transportadora.setCodigo(maxCodigo(max.getCodigo()));
            } else {
                transportadora.setCodigo("001/00");
            }
        }
        return repository.save(transportadora);
    }
    
    //Atualizando um transportadora (PUT /transportadoras)
    //@PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
            @RequestBody Transportadora transportadora) {
        enderecoRepository.save(transportadora.getEndereco());
        return repository.findById(id)
                .map(record -> {
                    record.setDataAlteracao(LocalDateTime.now());
                    record.setNome(transportadora.getNome());
                    record.setEmail(transportadora.getEmail());
                    record.setContato(transportadora.getContato());
                    record.setCalcularFreteSobreValorSemIpi(transportadora.isCalcularFreteSobreValorSemIpi());
                    record.setInativo(transportadora.isInativo());
                    record.setValorPeso(transportadora.getValorPeso());
                    record.setValorPorcentagem(transportadora.getValorPorcentagem());
                    //record.setEndereco(transportadora.getEndereco());
                    Transportadora updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    //Removendo um transportadora pelo ID (DELETE /transportadoras/{id})
    //@DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    private String maxCodigoFilial(String codigo) {
        String[] split = codigo.split("/");
        Integer max = Integer.parseInt(split[1]);
        max++;
        codigo = "0"+max;
        codigo = split[0]+"/"+codigo.substring(codigo.length()-2);
        return codigo;
    }
    
    private String maxCodigo(String codigo) {
        String[] split = codigo.split("/");
        Integer max = Integer.parseInt(split[0]);
        max++;
        codigo = "00"+max;
        codigo = codigo.substring(codigo.length()-3)+"/00";
        return codigo;
    }
    
    
    
    
    
}
