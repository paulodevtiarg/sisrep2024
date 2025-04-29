/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.model.Municipio;
import br.com.osmiki.sisrep.model.Transportadora;
import br.com.osmiki.sisrep.service.EnderecoService;
import br.com.osmiki.sisrep.service.MunicipioService;
import br.com.osmiki.sisrep.service.TransportadoraService;
import br.com.osmiki.sisrep.service.UfService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/transportadoras")
public class TransportadoraController {
    private TransportadoraService service;
    private UfService ufService;
    private EnderecoService enderecoService;
    private MunicipioService municipioService;
    private List<Municipio> listMunicipio;
    
    TransportadoraController( TransportadoraService transportadoraService, UfService ufService, EnderecoService enderecoService, MunicipioService municipioService){
        service = transportadoraService;
        this.ufService = ufService;
        this.enderecoService = enderecoService;
        this.municipioService = municipioService;
        listMunicipio = new ArrayList<>();
    }
    
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("transportadoras", service.findAll());
        return "transportadoras/index";
    }
    
    @GetMapping("/transportadora")
    public String showForm(Model model){
        Transportadora transportadora = new Transportadora();
        model.addAttribute(transportadora);
        model.addAttribute("estados", ufService.findAll());
        model.addAttribute("municipios", listMunicipio);
        return "transportadoras/create";
    }
    
    //Criando um novo transportadora (POST /transportadoras)
    @PostMapping
    public String addUser(@Valid Transportadora transportadora, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            System.out.println("ERRO!!!!!!!!");
            return "transportadoras/create";
        }
        transportadora.setDataAlteracao(LocalDateTime.now());
        
        try {
            enderecoService.create(transportadora.getEndereco());
            service.create(transportadora);
            model.addAttribute("msgOk", "Transportadora registrado.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "transportadoras/create";
        }
        model.addAttribute("transportadoras", service.findAll());
        return "transportadoras/index";
    }
    
    @GetMapping("/transportadora/{id}")
    public String showForm(@PathVariable("id") Integer id, Model model){
        ResponseEntity u = service.findById(id);
        if (u.hasBody()) {
            //System.out.println("transportadora presente "+u.getStatusCodeValue());
            Transportadora transportadora = (Transportadora) u.getBody();
            //System.out.println("confirmar ID "+transportadora.getIdPessoa());
            model.addAttribute(transportadora);
            model.addAttribute("estados", ufService.findAll());
            model.addAttribute("municipios", municipioService.findByEstado(transportadora.getEndereco().getMunicipio().getEstado().getIdEstado()));
        }
        return "transportadoras/update";
    }
    
    //Atualizar um transportadora (PUT /transportadoras/ID)
    @PostMapping(value="/{id}")
    public String addUser(@PathVariable("id") Integer id, @Valid Transportadora transportadora, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            transportadora.setIdTransportadora(id);
            return "transportadoras/update";
        }
        try {
            service.update(id, transportadora);
            model.addAttribute("msgOk", "Transportadora atualizado.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "transportadoras/update";
        }
        model.addAttribute("transportadoras", service.findAll());
        return "transportadoras/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            service.delete(id);
            model.addAttribute("msgOk", "Transportadora excluido.");
            model.addAttribute("transportadoras", service.findAll());
            return "transportadoras/index";
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "transportadoras/index";
        }
    }
    
}
