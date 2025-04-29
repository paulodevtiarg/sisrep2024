/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.model.Regiao;
import br.com.osmiki.sisrep.service.RegiaoService;
import jakarta.validation.Valid;
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
@RequestMapping("/regioes")
public class RegiaoController {
    private RegiaoService service;
    
    RegiaoController( RegiaoService regiaoService){
        service = regiaoService;
    }
    
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("regioes", service.findAll());
        return "regioes/index";
    }
    
    @GetMapping("/regiao")
    public String showForm(Model model){
        Regiao regiao = new Regiao();
        model.addAttribute(regiao);
        return "regioes/create";
    }
    
    //Criando um novo regiao (POST /regioes)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Regiao> addUser(@RequestBody Regiao regiao, BindingResult result, Model model) {
        try {
            service.create(regiao);
            model.addAttribute("msgOk", "Região registrado.");
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return ResponseEntity.badRequest()
                    .body(null);
        }
        model.addAttribute("regioes", service.findAll());
        model.addAttribute("regiao", regiao);
        
            return ResponseEntity.status(HttpStatus.OK)
            .body(regiao);
    }
    
    @GetMapping("/regiao/{id}")
    public String showForm(@PathVariable("id") Integer id, Model model){
        ResponseEntity u = service.findById(id);
        if (u.hasBody()) {
            //System.out.println("regiao presente "+u.getStatusCodeValue());
            Regiao regiao = (Regiao) u.getBody();
            //System.out.println("confirmar ID "+regiao.getIdPessoa());
            model.addAttribute(regiao);
        }
        return "regioes/update";
    }
    
    //Atualizar um regiao (PUT /regioes/ID)
    @PostMapping(value="/{id}")
    public String addUser(@PathVariable("id") Integer id, @Valid Regiao regiao, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            regiao.setIdRegiao(id);
            return "regioes/update";
        }
        try {
            service.update(id, regiao);
            model.addAttribute("msgOk", "Região atualizado.");
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return "regioes/update";
        }
        model.addAttribute("regioes", service.findAll());
        return "regioes/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            service.delete(id);
            model.addAttribute("msgOk", "Região excluido.");
            model.addAttribute("regioes", service.findAll());
            return "regioes/index";
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return "regioes/index";
        }
    }
    
}
