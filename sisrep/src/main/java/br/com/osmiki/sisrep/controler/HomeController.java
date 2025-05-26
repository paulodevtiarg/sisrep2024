/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.osmiki.sisrep.model.TipoAcesso;
import br.com.osmiki.sisrep.service.EmpresaService;
import br.com.osmiki.sisrep.sessao.UsuarioSessao;
import org.springframework.ui.Model;

/**
 *
 * @author Leonardo
 */
@Controller
public class HomeController {
	/**
     * Carrega a página de login (index.html)
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Carrega a página inicial após login
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
