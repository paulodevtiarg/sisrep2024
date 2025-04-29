/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.model.Municipio;
import br.com.osmiki.sisrep.service.MunicipioService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo
 */
@RestController
@RequestMapping("/municipios")
public class MunicipioController {
        
    private MunicipioService service;

    public MunicipioController(MunicipioService service) {
        this.service = service;
    }
    
    @GetMapping("/uf/{id}")
    public List<Municipio> findMunicipios(@PathVariable("id") Integer Id){
        System.out.println("buscando municipios de "+Id);
        List<Municipio> listMunicipio = service.findByEstado(Id);
        //System.out.println("retornando municipios");
        return listMunicipio;
        //return "transportadoras/create";
    }
}
