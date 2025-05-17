/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.dtos.UsuarioDTO;
import br.com.osmiki.sisrep.model.Usuario;
import br.com.osmiki.sisrep.service.UsuarioService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Leonardo
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
  
	    @Autowired
	    private UsuarioService usuarioService;
    /*
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("usuarios", service.findAll());
        return "usuarios/index";
    }*/
	    
	    @GetMapping
	    public String listUsuarios(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(required = false) String nome,
	            @RequestParam(required = false) String email,
	            @RequestParam(required = false) Boolean ativo,
	            @RequestParam(defaultValue = "nome") String sortField,
	            @RequestParam(defaultValue = "asc") String sortDirection,
	            Model model) {
	        
	        Page<UsuarioDTO> pageResult = usuarioService.findPaginated(
	            page, size, nome, email, ativo, sortField, sortDirection);
	        
	        model.addAttribute("usuarios", pageResult.getContent());
	        model.addAttribute("currentPage", page);
	        model.addAttribute("pageSize", size); // tamanho atual
	        model.addAttribute("totalPages", pageResult.getTotalPages());
	        model.addAttribute("pageSizes", List.of(5, 10, 20, 25));
	        model.addAttribute("nome", nome);
	        model.addAttribute("email", email);
	        model.addAttribute("ativo", ativo);
	        model.addAttribute("sortField", sortField);
	        model.addAttribute("sortDirection", sortDirection);
	        
	        return "usuarios/index";
	    }
	 // Rota alternativa para obter todos sem paginação (se necessário)
	    @GetMapping("/todos")
	    @ResponseBody
	    public List<UsuarioDTO> listAllUsuarios() {
	        return usuarioService.findAll();
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
        	usuarioService.create(usuario);
            model.addAttribute("msgOk", "Usuário registrado.");
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return "usuarios/create";
        }
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/index";
    }
    
    @GetMapping("/usuario/{id}")
    public String showForm(@PathVariable("id") Integer id, Model model){
        ResponseEntity u = usuarioService.findById(id);
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
        	usuarioService.update(id, usuario);
            model.addAttribute("msgOk", "Usuário atualizado.");
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return "usuarios/update";
        }
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
        	usuarioService.delete(id);
            model.addAttribute("msgOk", "Usuário excluido.");
            model.addAttribute("usuarios", usuarioService.findAll());
            return "usuarios/index";
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return "usuarios/index";
        }
    }
    
}
