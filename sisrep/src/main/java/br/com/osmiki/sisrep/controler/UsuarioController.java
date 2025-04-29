/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.model.Usuario;
import br.com.osmiki.sisrep.service.UsuarioService;
import jakarta.validation.Valid;
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
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService service;
    
    UsuarioController( UsuarioService usuarioService){
        service = usuarioService;
    }
    
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("usuarios", service.findAll());
        return "usuarios/index";
    }
    
    @GetMapping("/usuario")
    public String showForm(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute(usuario);
        return "usuarios/create";
    }
    
    //Criando um novo usuario (POST /usuarios)
    @PostMapping
    public String addUser(@Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            System.out.println("ERRO!!!!!!!!");
            return "usuarios/create";
        }
        try {
            service.create(usuario);
            model.addAttribute("msgOk", "Usuário registrado.");
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return "usuarios/create";
        }
        model.addAttribute("usuarios", service.findAll());
        return "usuarios/index";
    }
    
    @GetMapping("/usuario/{id}")
    public String showForm(@PathVariable("id") Integer id, Model model){
        ResponseEntity u = service.findById(id);
        if (u.hasBody()) {
            //System.out.println("usuario presente "+u.getStatusCodeValue());
            Usuario usuario = (Usuario) u.getBody();
            //System.out.println("confirmar ID "+usuario.getIdPessoa());
            model.addAttribute(usuario);
        }
        return "usuarios/update";
    }
    
    //Atualizar um usuario (PUT /usuarios/ID)
    @PostMapping(value="/{id}")
    public String addUser(@PathVariable("id") Integer id, @Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            usuario.setIdPessoa(id);
            return "usuarios/update";
        }
        try {
            service.update(id, usuario);
            model.addAttribute("msgOk", "Usuário atualizado.");
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return "usuarios/update";
        }
        model.addAttribute("usuarios", service.findAll());
        return "usuarios/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            service.delete(id);
            model.addAttribute("msgOk", "Usuário excluido.");
            model.addAttribute("usuarios", service.findAll());
            return "usuarios/index";
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return "usuarios/index";
        }
    }
    
}
