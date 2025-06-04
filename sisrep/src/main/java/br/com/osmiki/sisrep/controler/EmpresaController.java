package br.com.osmiki.sisrep.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.osmiki.sisrep.dtos.EmpresaDTO;
import br.com.osmiki.sisrep.model.TipoAcesso;
import br.com.osmiki.sisrep.model.Usuario;
import br.com.osmiki.sisrep.service.EmpresaService;
import br.com.osmiki.sisrep.service.NivelService;
import br.com.osmiki.sisrep.sessao.UsuarioSessao;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {
	
		@Autowired
		private EmpresaService empresaService;
	
	    @Autowired
	    private UsuarioSessao usuarioSessao;
	    	    
	    @Autowired
	    private NivelService nivelService;
	    
	    @GetMapping
	    public String listEmpresas(@RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(required = false) Integer idEmpresa,  // Adicione esta linha
	            @RequestParam(required = false) String nome,
	            @RequestParam(required = false) String cnpj,
	            @RequestParam(defaultValue = "idEmpresa") String sortField,
	            @RequestParam(defaultValue = "asc") String sortDirection,
	            Model model) {
	    	
	    	  Usuario usuarioLogado = usuarioSessao.getUsuarioLogado();
	    	  boolean isAdmin = usuarioLogado.getNivel().getId() == 1;
		    	// Obtém dados do usuário logado
		        Integer id_Empresa = usuarioSessao.getUsuarioLogado().getId_empresa();
		        Integer nivel = usuarioSessao.getUsuarioLogado().getNivel().getId();
		        TipoAcesso tipoAcesso = usuarioSessao.getUsuarioLogado().getTipoacesso();
		        
		        
		        Page<EmpresaDTO> pageResult = empresaService.findPaginated(
		                page, size, id_Empresa, nivel, tipoAcesso, idEmpresa, nome, cnpj, sortField, sortDirection);
		        
		        model.addAttribute("empresas", pageResult.getContent());
		        model.addAttribute("isAdmin", nivel == 1); // Se é administrador
		        model.addAttribute("isMaster", tipoAcesso == TipoAcesso.MASTER);
		        model.addAttribute("currentPage", page);
		        model.addAttribute("pageSize", size);
		        model.addAttribute("totalPages", pageResult.getTotalPages());
		        model.addAttribute("pageSizes", List.of(5, 10, 20, 25));
		        model.addAttribute("idEmpresa", id_Empresa);
		        model.addAttribute("nome", nome);
		        model.addAttribute("cnpj", cnpj);
		        model.addAttribute("sortField", sortField);
		        model.addAttribute("sortDirection", sortDirection);
		        model.addAttribute("pageResult", pageResult); // Adiciona o objeto pageResult completo
	    	
		        return "empresas/index";
	    	
	    }
	
	

}
