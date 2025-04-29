/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.model.Vendedor;
import br.com.osmiki.sisrep.service.EnderecoService;
import br.com.osmiki.sisrep.service.MunicipioService;
import br.com.osmiki.sisrep.service.UfService;
import br.com.osmiki.sisrep.service.UsuarioService;
import br.com.osmiki.sisrep.service.VendedorService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Leonardo
 */
@Controller
@RequestMapping("/vendedores")
public class VendedorController {
    private VendedorService service;
    private UfService ufService;
    private EnderecoService enderecoService;
    private UsuarioService usuarioService;
    private MunicipioService municipioService;
    
    VendedorController( VendedorService vendedorService, UfService ufService, EnderecoService enderecoService, UsuarioService usuarioService, MunicipioService municipioService){
        service = vendedorService;
        this.ufService = ufService;
        this.enderecoService = enderecoService;
        this.usuarioService = usuarioService;
        this.municipioService = municipioService;
    }
    
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("vendedores", service.findAll());
        return "vendedores/index";
    }
    
    @GetMapping("/vendedor")
    public String showForm(Model model){
        Vendedor vendedor = new Vendedor();
        model.addAttribute(vendedor);
        model.addAttribute("estados", ufService.findAll());
        return "vendedores/create";
    }
    
    //Criando um novo vendedor (POST /vendedores)
    @PostMapping
    public String addUser(@Valid Vendedor vendedor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            System.out.println("ERRO!!!!!!!!");
            return "vendedores/create";
        }
        vendedor.setDataAlteracao(LocalDateTime.now());
        vendedor.setUsuario(usuarioService.findByCpf(vendedor.getCpf()));
        try {
            enderecoService.create(vendedor.getEndereco());
            service.create(vendedor);
            model.addAttribute("msgOk", "Vendedor registrado.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "vendedores/create";
        }
        model.addAttribute("vendedores", service.findAll());
        return "vendedores/index";
    }
    
    @GetMapping("/vendedor/{id}")
    public String showForm(@PathVariable("id") Integer id, Model model){
        ResponseEntity u = service.findById(id);
        if (u.hasBody()) {
            //System.out.println("vendedor presente "+u.getStatusCodeValue());
            Vendedor vendedor = (Vendedor) u.getBody();
            //System.out.println("confirmar ID "+vendedor.getIdPessoa());
            model.addAttribute(vendedor);
            model.addAttribute("estados", ufService.findAll());
            model.addAttribute("municipios", municipioService.findByEstado(vendedor.getEndereco().getMunicipio().getEstado().getIdEstado()));
        }
        return "vendedores/update";
    }
    
    //Atualizar um vendedor (PUT /vendedores/ID)
    @PostMapping(value="/{id}")
    public String addUser(@PathVariable("id") Integer id, @Valid Vendedor vendedor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            vendedor.setIdPessoa(id);
            vendedor.setUsuario(usuarioService.findByCpf(vendedor.getCpf()));
            return "vendedores/update";
        }
        try {
            service.update(id, vendedor);
            model.addAttribute("msgOk", "Vendedor atualizado.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "vendedores/update";
        }
        model.addAttribute("vendedores", service.findAll());
        return "vendedores/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            service.delete(id);
            model.addAttribute("msgOk", "Vendedor excluido.");
            model.addAttribute("vendedores", service.findAll());
            return "vendedores/index";
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "vendedores/index";
        }
    }
    
}
