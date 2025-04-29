/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Leonardo
 */
@Controller
public class HomeController {
    /***
	 * ESSE MÉTODO CARREGA A PÁGINA(index.html) DE LOGIN DA NOSSA APLICAÇÃO
	 * @return
	 */
	@RequestMapping(value="/", method= RequestMethod.GET)	
	public String index(){	
 
	    return "index";
	}
 
	/***
	 * CARREGA À PÁGINA INICIAL DA APLICAÇÃO APÓS EFETUARMOS O LOGIN 
	 * @return
	 */
	@RequestMapping(value="/home", method= RequestMethod.GET)
	public String home(){
 
		return "home";
	}
}
