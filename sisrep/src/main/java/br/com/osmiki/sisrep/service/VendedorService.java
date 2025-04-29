/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.Vendedor;
import br.com.osmiki.sisrep.repository.EnderecoRepository;
import br.com.osmiki.sisrep.repository.UsuarioRepository;
import br.com.osmiki.sisrep.repository.VendedorRepository;
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
//@RequestMapping("/vendedors")
public class VendedorService {
    
    private VendedorRepository repository;
    private EnderecoRepository enderecoRepository;
    private UsuarioRepository usuarioRepository;

    VendedorService(VendedorRepository repository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }
   // métodos do CRUD aqui
   
    //Listando todos os vendedors (GET /vendedors)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um vendedor especídifo pelo ID (GET /vendedors  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    //Criando um novo vendedor (POST /vendedors)
    //@PostMapping
    public Vendedor create(@RequestBody Vendedor vendedor){
        
        Vendedor teste = repository.findByCpf(vendedor.getCpf());
        if (teste != null) {
            throw new EntityExistsException("Erro: Vendedor já existe no sistema com o CPF "+vendedor.getCpf());
        }
        return repository.save(vendedor);
    }
    
    //Atualizando um vendedor (PUT /vendedors)
    //@PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
            @RequestBody Vendedor vendedor) {
        enderecoRepository.save(vendedor.getEndereco());
        if (vendedor.getUsuario() != null && vendedor.getUsuario().getIdPessoa() != null) {
            usuarioRepository.save(vendedor.getUsuario());
        }
        return repository.findById(id)
                .map(record -> {
                    record.setComissaoApenasFaturamento(vendedor.isComissaoApenasFaturamento());
                    record.setComissaoPropria(vendedor.getComissaoPropria());
                    record.setComissaoClienteNaoPropria(vendedor.getComissaoClienteNaoPropria());
                    record.setDataAlteracao(LocalDateTime.now());
                    record.setDataFimComissao(vendedor.getDataFimComissao());
                    record.setEmail(vendedor.getEmail());
                    record.setGerenteVendedor(vendedor.getGerenteVendedor());
                    record.setInativo(vendedor.isInativo());
                    record.setNome(vendedor.getNome());
                    record.setProximoNumeroAssistencia(vendedor.getProximoNumeroAssistencia());
                    record.setProximoNumeroContasPagar(vendedor.getProximoNumeroContasPagar());
                    record.setProximoNumeroInadimplenciaCliente(vendedor.getProximoNumeroInadimplenciaCliente());
                    record.setProximoNumeroPedido(vendedor.getProximoNumeroPedido());
                    record.setRg(vendedor.getRg());
                    record.setSenha(vendedor.getSenha());
                    record.setVendedorInterno(vendedor.isVendedorInterno());
                    Vendedor updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    //Removendo um vendedor pelo ID (DELETE /vendedors/{id})
    //@DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }  
    
}
