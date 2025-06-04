package br.com.osmiki.sisrep.sessao;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import br.com.osmiki.sisrep.model.Usuario;
@Component
@SessionScope
public class UsuarioSessao {
    private Usuario usuarioLogado;

    public void login(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public void logout() {
        this.usuarioLogado = null;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean isLogado() {
        return usuarioLogado != null;
    }

    public boolean isAdmin() {
        return isLogado() && "ADMINISTRATIVO".equalsIgnoreCase(usuarioLogado.getNivel().getNivel());
    }
    
    

}
