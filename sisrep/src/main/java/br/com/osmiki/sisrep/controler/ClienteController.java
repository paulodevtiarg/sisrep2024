/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.model.Cliente;
import br.com.osmiki.sisrep.service.EnderecoService;
import br.com.osmiki.sisrep.service.MunicipioService;
import br.com.osmiki.sisrep.service.UfService;
import br.com.osmiki.sisrep.service.ClienteService;
import br.com.osmiki.sisrep.service.RegiaoService;
import br.com.osmiki.sisrep.service.VendedorService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Leonardo
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService service;
    private UfService ufService;
    private EnderecoService enderecoService;
    private VendedorService vendedorService;
    private MunicipioService municipioService;
    private RegiaoService regiaoService;
    
    ClienteController( ClienteService clienteService, UfService ufService, EnderecoService enderecoService, 
            VendedorService vendedorService, MunicipioService municipioService, RegiaoService regiaoService){
        service = clienteService;
        this.ufService = ufService;
        this.enderecoService = enderecoService;
        this.vendedorService = vendedorService;
        this.municipioService = municipioService;
        this.regiaoService = regiaoService;
    }
    // Rota: GET /clientes
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("clientes", service.findAll());
        return "clientes/index";
    }
    //Rota: GET /clientes/cliente
    @GetMapping("/cliente")
    public String showForm(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute(cliente);
        model.addAttribute("estados", ufService.findAll());
        model.addAttribute("vendedores", vendedorService.findAll());
        model.addAttribute("regioes", regiaoService.findAll());
        return "clientes/create";
    }
    
    //Criando um novo cliente (POST /clientes)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody Cliente cliente, BindingResult result, Model model, int vendedorId) {
        
        System.out.println("metodo add");
        cliente.setDataAlteracao(LocalDateTime.now());
        cliente.setDataCadastro(LocalDateTime.now());
        
        try {
            if (cliente.getEndereco() != null) {
                enderecoService.create(cliente.getEndereco());
            }
            
            service.create(cliente);
            model.addAttribute("msgOk", "Cliente registrado.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            model.addAttribute(cliente);
            model.addAttribute("estados", ufService.findAll());
            model.addAttribute("vendedores", vendedorService.findAll());
            model.addAttribute("regioes", regiaoService.findAll());
            model.addAttribute("msgErro", msgErro);
            return ResponseEntity.badRequest()
                    .body("ERRO AO SALVAR CLIENTE: "+msgErro);
        }
        model.addAttribute("clientes", service.findAll());
        return ResponseEntity.status(HttpStatus.OK)
        .body("CLIENTE SALVO COM SUCESSO!");
    }
    //Rota: GET /clientes/cliente/{id}
    @GetMapping("/cliente/{id}")
    public String showForm(@PathVariable("id") Integer id, Model model){
        ResponseEntity u = service.findById(id);
        if (u.hasBody()) {
            //System.out.println("cliente presente "+u.getStatusCodeValue());
            Cliente cliente = (Cliente) u.getBody();
            //System.out.println("confirmar ID "+cliente.getIdPessoa());
            model.addAttribute(cliente);
            model.addAttribute("estados", ufService.findAll());
            model.addAttribute("municipios", municipioService.findByEstado(cliente.getEndereco().getMunicipio().getEstado().getIdEstado()));
        }
        return "clientes/update";
    }
    
    //Atualizar um cliente (PUT /clientes/ID)
    @PostMapping(value="/{id}")
    public String addUser(@PathVariable("id") Integer id, @Valid Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            //cliente.setIdPessoa(id);
            //cliente.setUsuario(usuarioService.findByCpf(cliente.getCpf()));
            
            return "clientes/update";
        }
        try {
            service.update(id, cliente);
            model.addAttribute("msgOk", "Cliente atualizado.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "clientes/update";
        }
        model.addAttribute("clientes", service.findAll());
        return "clientes/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            service.delete(id);
            model.addAttribute("msgOk", "Cliente excluido.");
            model.addAttribute("clientes", service.findAll());
            return "clientes/index";
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "clientes/index";
        }
    }
    
}
