/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.Cliente;
import br.com.osmiki.sisrep.repository.EnderecoRepository;
import br.com.osmiki.sisrep.repository.UsuarioRepository;
import br.com.osmiki.sisrep.repository.ClienteRepository;
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
public class ClienteService {
    
    private ClienteRepository repository;
    private EnderecoRepository enderecoRepository;
    private UsuarioRepository usuarioRepository;

    ClienteService(ClienteRepository repository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }
   // métodos do CRUD aqui
   
    //Listando todos os clientes (GET /clientes)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um cliente especídifo pelo ID (GET /clientes  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    //Criando um novo cliente (POST /clientes)
    //@PostMapping
    public Cliente create(@RequestBody Cliente cliente){
        if (cliente.getCnpj() == null) {
            throw new EntityExistsException("Erro: Cliente sem CNPJ/CPF ");
        }
        Cliente teste = (Cliente) repository.findByCnpj(cliente.getCnpj());
        if (teste != null) {
            throw new EntityExistsException("Erro: Cliente já existe no sistema com o CNPJ "+cliente.getCnpj());
        }
        
        String codigo = "0000/00";
        String cnpj = cliente.getCnpj().substring(0, 10);
        Cliente max = (Cliente) repository.findTopByCnpjLikeOrderByCodigoDesc(cnpj+"%");
        if (max != null) {
            codigo = maxCodigoFilial(max.getCodigo());
            cliente.setCodigo(codigo);
        } else {
            max = (Cliente) repository.findTopByOrderByCodigoDesc();
            if (max != null) {
                cliente.setCodigo(maxCodigo(max.getCodigo()));
            } else {
                cliente.setCodigo("0001/00");
            }
        }
        return repository.save(cliente);
    }
    
    //Atualizando um cliente (PUT /clientes)
    //@PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
            @RequestBody Cliente cliente) {
        enderecoRepository.save(cliente.getEndereco());
        if (cliente.getUsuario() != null && cliente.getUsuario().getIdPessoa() != null) {
            usuarioRepository.save(cliente.getUsuario());
        }
        return repository.findById(id)
                .map(record -> {
                    record.setBloquearVenda(cliente.isBloquearVenda());
                    record.setCentroDistribuicao(cliente.isCentroDistribuicao());
                    record.setCnpj(cliente.getCnpj());
                    record.setCodigo(cliente.getCodigo());
                    record.setDataAlteracao(LocalDateTime.now());
                    record.setDataFundacao(cliente.getDataFundacao());
                    record.setDataUltimaCompra(cliente.getDataUltimaCompra());
                    record.setDebito(cliente.getDebito());
                    record.setEndereco(cliente.getEndereco());
                    record.setEnderecoCobranca(cliente.getEnderecoCobranca());
                    record.setEnderecoEntrega(cliente.getEnderecoEntrega());
                    record.setEnviarPedidoEmail(cliente.isEnviarPedidoEmail());
                    record.setExclusivoVendedor(cliente.isExclusivoVendedor());
                    record.setFilial(cliente.isFilial());
                    record.setIe(cliente.getIe());
                    record.setInativo(cliente.isInativo());
                    record.setMatriz(cliente.getMatriz());
                    record.setNomeComprador(cliente.getNomeComprador());
                    record.setNomeFantasia(cliente.getNomeFantasia());
                    record.setNumeroLoja(cliente.getNumeroLoja());
                    record.setObs(cliente.getObs());
                    record.setPostoVenda(cliente.isBloquearVenda());
                    record.setRazaoSocial(cliente.getRazaoSocial());
                    record.setRegiao(cliente.getRegiao());
                    record.setSede(cliente.getSede());
                    record.setSituacaoCliente(cliente.getSituacaoCliente());
                    record.setUsuario(cliente.getUsuario());
                    record.setVendedor(cliente.getVendedor());
                    Cliente updated = (Cliente) repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    //Removendo um cliente pelo ID (DELETE /clientes/{id})
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
        codigo = "000"+max;
        codigo = codigo.substring(codigo.length()-4)+"/00";
        return codigo;
    }
    
}
