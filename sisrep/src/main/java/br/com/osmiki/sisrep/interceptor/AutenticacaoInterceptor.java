package br.com.osmiki.sisrep.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import br.com.osmiki.sisrep.sessao.UsuarioSessao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticacaoInterceptor implements HandlerInterceptor {
	@Autowired
    private UsuarioSessao usuarioSessao;

    @Override
    public boolean preHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler) throws Exception {
        
        if (!usuarioSessao.isLogado() && !request.getRequestURI().endsWith("/login")) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

}
