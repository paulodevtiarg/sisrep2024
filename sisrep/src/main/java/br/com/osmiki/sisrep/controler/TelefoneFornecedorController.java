/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.model.Fornecedor;
import br.com.osmiki.sisrep.model.TelefoneFornecedor;
import br.com.osmiki.sisrep.service.FornecedorService;
import br.com.osmiki.sisrep.service.MunicipioService;
import br.com.osmiki.sisrep.service.TelefoneFornecedorService;
import br.com.osmiki.sisrep.service.UfService;
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
@RequestMapping("/telefoneFornecedores")
public class TelefoneFornecedorController {
    private TelefoneFornecedorService service;
    private FornecedorService fornecedorService;
    private UfService ufService;
    private MunicipioService municipioService;
    
    TelefoneFornecedorController( TelefoneFornecedorService telefoneFornecedorService, FornecedorService fornecedorService, UfService ufService, MunicipioService municipioService ){
        service = telefoneFornecedorService;
        this.fornecedorService = fornecedorService;
        this.ufService = ufService;
        this.municipioService = municipioService;
    }
    
    //Criando um novo telefoneFornecedor (POST /telefoneFornecedores/1)
    @PostMapping(value="/{idFornecedor}")
    public String add(@PathVariable("idFornecedor") Integer idFornecedor,@Valid TelefoneFornecedor telefoneFornecedor, BindingResult result, Model model) {

        ResponseEntity u = fornecedorService.findById(idFornecedor);
        Fornecedor fornecedor = null;
        if (u.hasBody()) {
            fornecedor = (Fornecedor) u.getBody();
        }
        telefoneFornecedor.setFornecedor(fornecedor);
        
        if (result.hasErrors()) {
            String msgErro = "";
            
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            System.out.println("ERRO!!!!!!!!");
            return "telefoneFornecedores/create";
        }
        
        try {
            service.create(telefoneFornecedor);
            model.addAttribute("msgOk", "TelefoneFornecedor registrado.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "fornecedores/create";
        }
        u = fornecedorService.findById(idFornecedor);
        if (u.hasBody()) {
            fornecedor = (Fornecedor) u.getBody();
        }
        model.addAttribute(fornecedor);
        model.addAttribute("estados", ufService.findAll());
        model.addAttribute("municipios", municipioService.findByEstado(fornecedor.getEndereco().getMunicipio().getEstado().getIdEstado()));
        model.addAttribute("telefoneFornecedor", new TelefoneFornecedor());
        return "fornecedores/update";
    }
    
    //Atualizar um telefoneFornecedor (PUT /telefoneFornecedores/ID)
    @PostMapping(value="/update/{id}")
    public String up(@PathVariable("id") Integer id, @Valid TelefoneFornecedor telefoneFornecedor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            telefoneFornecedor.setIdTelefone(id);
            return "fornecedores/update";
        }
        try {
            service.update(id, telefoneFornecedor);
            model.addAttribute("msgOk", "TelefoneFornecedor atualizado.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "fornecedores/update";
        }
        
        model.addAttribute(telefoneFornecedor.getFornecedor());
        model.addAttribute("estados", ufService.findAll());
        return "fornecedores/update";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            ResponseEntity u = service.findById(id);
            if (u.hasBody()) {
                TelefoneFornecedor telefone = (TelefoneFornecedor) u.getBody();
                service.delete(id);
                model.addAttribute("msgOk", "TelefoneFornecedor excluido.");
                model.addAttribute(telefone.getFornecedor());
                model.addAttribute("estados", ufService.findAll());
                return "fornecedores/update";
            }
            
            model.addAttribute("msgErro", "Erro ao deletar Telefone.");
            return "fornecedores/index";
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "fornecedores/index";
        }
    }
    
}
