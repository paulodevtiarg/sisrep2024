package br.com.osmiki.sisrep.controler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.osmiki.sisrep.model.Usuario;
import br.com.osmiki.sisrep.repository.UsuarioRepository;
import br.com.osmiki.sisrep.sessao.UsuarioSessao;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioSessao usuarioSession;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String cpf, 
                       @RequestParam String senha,
                       Model model) {
        
        Usuario usuario = usuarioRepository.findByCpfAndSenha(cpf, senha);
        
        if (usuario != null) {
            usuarioSession.login(usuario);
            return "redirect:/home";
        } else {
            model.addAttribute("erro", "CPF ou senha inválidos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        usuarioSession.logout();
        return "redirect:/login";
    }
}
