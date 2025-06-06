package br.com.osmiki.sisrep.utils;

import br.com.osmiki.sisrep.model.TipoAcesso;
import br.com.osmiki.sisrep.sessao.UsuarioSessao;

public class AcessoUtils {
    
    public static boolean isEmpresarialAdm(UsuarioSessao usuarioSessao) {
        if (usuarioSessao == null || !usuarioSessao.isLogado()) {
            return false;
        }
        return usuarioSessao.getUsuarioLogado().getTipoacesso() == TipoAcesso.EMPRESARIAL 
               && usuarioSessao.getUsuarioLogado().getNivel().equals(1);
    }
    
    public static boolean isUserMaster(UsuarioSessao usuarioSessao) {
        if (usuarioSessao == null || !usuarioSessao.isLogado()) {
            return false;
        }
        return usuarioSessao.getUsuarioLogado().getTipoacesso() == TipoAcesso.MASTER 
               && usuarioSessao.getUsuarioLogado().getNivel().equals(1);
    }
}