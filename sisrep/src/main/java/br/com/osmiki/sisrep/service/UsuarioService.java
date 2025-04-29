/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.Usuario;
import br.com.osmiki.sisrep.repository.UsuarioRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo
 */
@RestController
//@RequestMapping("/usuarios")
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    
    /*@GetMapping(value = "/lista")
    public String getAll(){
        
        return "usuario/ListaUsuarios";
    }*/
    
   // métodos do CRUD aqui
   
    //Listando todos os usuarios (GET /usuarios)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um usuario especídifo pelo ID (GET /usuarios  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    //Criando um novo usuario (POST /usuarios)
    //@PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        usuario.setDataAlteracao(LocalDateTime.now());
        return repository.save(usuario);
    }
    
    //Atualizando um usuario (PUT /usuarios)
    //@PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
            @RequestBody Usuario usuario) {
        return repository.findById(id)
                .map(record -> {
                    record.setDataAlteracao(LocalDateTime.now());
                    record.setNome(usuario.getNome());
                    record.setEmail(usuario.getEmail());
                    record.setInativo(usuario.getInativo());
                    record.setProximoNumeroAssistencia(usuario.getProximoNumeroAssistencia());
                    record.setCliente(usuario.getCliente());
                    record.setEmailSenha(usuario.getEmailSenha());
                    record.setGerenteFornecedor(usuario.getGerenteFornecedor());
                    record.setProximoNumeroContasPagar(usuario.getProximoNumeroContasPagar());
                    record.setProximoNumeroInadimplenciaCliente(usuario.getProximoNumeroInadimplenciaCliente());
                    Usuario updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    //Removendo um usuario pelo ID (DELETE /usuarios/{id})
    //@DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    
    //@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity getUsuario(@RequestBody Usuario usuario){
        String cpf, senha;
        cpf = usuario.getCpf();
        senha = usuario.getSenha();
        //Usuario user = repository.findByCpf(usuario.getCpf());
        return repository.findByCpfAndSenha(cpf, senha)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
//        if (user == null) {
//            return null;
//        }
//        String senha = usuario.getSenha();
//        if (user.getSenha().equals(senha) && !user.getInativo()) {
//            return user;
//        }
//        return null;
    }

    public Usuario findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
    
    
    
    
}
