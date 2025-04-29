/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.model.TabelaPreco;
import br.com.osmiki.sisrep.service.TabelaPrecoService;
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
@RequestMapping("/tabelaprecos")
public class TabelaPrecoController {
    private TabelaPrecoService service;
    
    TabelaPrecoController( TabelaPrecoService tabelaprecoService){
        service = tabelaprecoService;
    }
    
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("tabelaprecos", service.findAll());
        return "tabelaprecos/index";
    }
    
    @GetMapping("/tabelapreco")
    public String showForm(Model model){
        TabelaPreco tabelaPreco = new TabelaPreco();
        model.addAttribute(tabelaPreco);
        return "tabelaprecos/create";
    }
    
    //Criando um novo tabelapreco (POST /tabelaprecos)
    @PostMapping
    public String addUser(@Valid TabelaPreco tabelapreco, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            System.out.println("ERRO!!!!!!!!");
            return "tabelaprecos/create";
        }
        tabelapreco.setDataAlteracao(LocalDateTime.now());
        try {
            service.create(tabelapreco);
            model.addAttribute("msgOk", "Tabela de Preço registrada.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "tabelaprecos/create";
        }
        model.addAttribute("tabelaprecos", service.findAll());
        return "tabelaprecos/index";
    }
    
    @GetMapping("/tabelapreco/{id}")
    public String showForm(@PathVariable("id") Integer id, Model model){
        ResponseEntity u = service.findById(id);
        if (u.hasBody()) {
            //System.out.println("tabelapreco presente "+u.getStatusCodeValue());
            TabelaPreco tabelapreco = (TabelaPreco) u.getBody();
            //System.out.println("confirmar ID "+tabelapreco.getIdPessoa());
            model.addAttribute(tabelapreco);
        }
        return "tabelaprecos/update";
    }
    
    //Atualizar um tabelapreco (PUT /tabelaprecos/ID)
    @PostMapping(value="/{id}")
    public String addUser(@PathVariable("id") Integer id, @Valid TabelaPreco tabelapreco, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            tabelapreco.setIdTabela(id);
            return "tabelaprecos/update";
        }
        try {
            service.update(id, tabelapreco);
            model.addAttribute("msgOk", "Tabela de Preço atualizada.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "tabelaprecos/update";
        }
        model.addAttribute("tabelaprecos", service.findAll());
        return "tabelaprecos/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            service.delete(id);
            model.addAttribute("msgOk", "Tabela de Preco excluida.");
            model.addAttribute("tabelaprecos", service.findAll());
            return "tabelaprecos/index";
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "tabelaprecos/index";
        }
    }
    
}
