package br.com.osmiki.sisrep.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.osmiki.sisrep.model.TipoAcesso;
import br.com.osmiki.sisrep.service.EmpresaService;
import br.com.osmiki.sisrep.sessao.UsuarioSessao;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UsuarioSessao usuarioSessao;

    @Autowired
    private EmpresaService empresaService;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        if (usuarioSessao.isLogado()) {
            model.addAttribute("usuarioSession", usuarioSessao);
            
            if (usuarioSessao.getUsuarioLogado().getTipoacesso() == TipoAcesso.EMPRESARIAL) {
                String nomeEmpresa = empresaService.getNomeEmpresa(
                    usuarioSessao.getUsuarioLogado().getId_empresa()
                );
                model.addAttribute("nomeEmpresa", nomeEmpresa);
            }
            
            // Logs para debug (opcional)
            System.out.println("Usuário logado: " + usuarioSessao.getUsuarioLogado().getNome());
            System.out.println("Tipo de acesso: " + usuarioSessao.getUsuarioLogado().getTipoacesso());
        }
    }
}
