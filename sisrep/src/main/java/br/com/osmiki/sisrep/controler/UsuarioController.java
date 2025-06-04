/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.converter.UsuarioConverter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.osmiki.sisrep.dtos.UsuarioDTO;
import br.com.osmiki.sisrep.model.Nivel;
import br.com.osmiki.sisrep.model.TipoAcesso;
import br.com.osmiki.sisrep.model.Usuario;
import br.com.osmiki.sisrep.service.NivelService;
import br.com.osmiki.sisrep.service.UsuarioService;
import br.com.osmiki.sisrep.sessao.UsuarioSessao;
import br.com.osmiki.sisrep.utils.AcessoUtils;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	    
	    @Autowired
	    private UsuarioSessao usuarioSessao;
	    
	    
	    @Autowired
	    private NivelService nivelService;

	    
	    @GetMapping
	    public String listUsuarios(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(required = false) Integer idPessoa,  // Adicione esta linha
	            @RequestParam(required = false) String nome,
	            @RequestParam(required = false) String email,
	            @RequestParam(required = false) Boolean ativo,
	            @RequestParam(defaultValue = "idPessoa") String sortField,
	            @RequestParam(defaultValue = "asc") String sortDirection,
	            Model model) {
	        
	    	 Usuario usuarioLogado = usuarioSessao.getUsuarioLogado();
	    	 
	    	 boolean isAdmin = usuarioLogado.getNivel().getId() == 1;
	    	// Obtém dados do usuário logado
	        Integer idEmpresa = usuarioSessao.getUsuarioLogado().getId_empresa();
	        Integer nivel = usuarioSessao.getUsuarioLogado().getNivel().getId();
	        
	        Page<UsuarioDTO> pageResult = usuarioService.findPaginated(
	                page, size, idEmpresa, nivel,usuarioLogado.getTipoacesso(), idPessoa, nome, email, ativo, sortField, sortDirection);
	        
	        model.addAttribute("usuarios", pageResult.getContent());
	        model.addAttribute("isAdmin", nivel == 1); // Se é administrador
	        model.addAttribute("isMaster", usuarioLogado.getTipoacesso() == TipoAcesso.MASTER);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("pageSize", size);
	        model.addAttribute("totalPages", pageResult.getTotalPages());
	        model.addAttribute("pageSizes", List.of(5, 10, 20, 25));
	        model.addAttribute("idPessoa", idPessoa);
	        model.addAttribute("nome", nome);
	        model.addAttribute("email", email);
	        model.addAttribute("ativo", ativo);
	        model.addAttribute("sortField", sortField);
	        model.addAttribute("sortDirection", sortDirection);
	        model.addAttribute("pageResult", pageResult); // Adiciona o objeto pageResult completo
	        
	        return "usuarios/index";
	    }
	 
   
    @GetMapping("/usuario")
    public String showForm(Model model){
        UsuarioDTO usuario = new UsuarioDTO();
        
        //vai verificar se o usuario logado é um EMPRESARIAL e se é ADMIN
        //se for vai atribuir o valor EMPRESARIAL ao atributo do novo usuário
        //e atribuir o nivel 2, pois esse nao é admin
        if (!AcessoUtils.isEmpresarialAdm(usuarioSessao)) {
        	//pega a empresa do usuario e adiciona ao novo usuário
            usuario.setId_empresa(usuarioSessao.getUsuarioLogado().getId_empresa());
            usuario.setTipoacesso(TipoAcesso.EMPRESARIAL);
            // Busca direta 
            Nivel nivelComum = nivelService.findById(2); // Nível 2 = usuário comum
                      
            usuario.setNivel(nivelComum);
            model.addAttribute("usuario", usuario); // Note o nome explícito "usuario"
        }else{
        	  model.addAttribute("msgErro", "USUÁRIO SEM PERMISSÃO PARA ADICIONAR NOVOS USUÁRIOS À EMPRESA");
        	  return "usuarios/index";
        }
        
       
        return "usuarios/create";
    } 
    
    //Criando um novo usuario (POST /usuarios)
    @PostMapping
    public String addUser(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
          
            System.out.println("ERRO!!!!!!!!");
            return "usuarios/create";
        }
        try {
        	 // Converter DTO para Entity usando o conversor estático
            Usuario usuario = UsuarioConverter.toEntity(usuarioDTO);
            
            // Salvar o usuário convertido com tratamento de erro
            try {
                usuarioService.create(usuario);
                redirectAttributes.addFlashAttribute("msgOk", "Usuário registrado com sucesso!");
                
                return "redirect:/usuarios?page=0&size=10&sortField=nome&sortDirection=asc";
            } catch (Exception e) {
                // Log do erro completo
                e.printStackTrace();
                
                // Mensagem amigável para o usuário
                String msgErro = "Erro ao salvar usuário: " + e.getMessage();
                if (e.getCause() != null) {
                    msgErro += " - Causa: " + e.getCause().getMessage();
                }
                
                model.addAttribute("msgErro", msgErro);
                return "usuarios/create";
            }
            
            
                       
        } catch (Exception e) {
            String msgErro = e.getCause().getMessage();
            model.addAttribute("msgErro", msgErro);
            return "usuarios/create";
        }
      //model.addAttribute("usuarios", usuarioService.findAll());
     // Redireciona para a listagem com parâmetros padrão
   //     return "redirect:/usuarios?page=0&size=10&sortField=nome&sortDirection=asc";
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
