package br.com.osmiki.sisrep.controler;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.osmiki.sisrep.converter.EmpresaConverter;
import br.com.osmiki.sisrep.converter.MunicipioConverter;
import br.com.osmiki.sisrep.converter.UsuarioConverter;
import br.com.osmiki.sisrep.dtos.EmpresaDTO;
import br.com.osmiki.sisrep.dtos.MunicipioDTO;
import br.com.osmiki.sisrep.dtos.UsuarioDTO;
import br.com.osmiki.sisrep.model.Empresa;
import br.com.osmiki.sisrep.model.Municipio;
import br.com.osmiki.sisrep.model.Nivel;
import br.com.osmiki.sisrep.model.TipoAcesso;
import br.com.osmiki.sisrep.model.Usuario;
import br.com.osmiki.sisrep.model.Vendedor;
import br.com.osmiki.sisrep.service.EmpresaService;
import br.com.osmiki.sisrep.service.MunicipioService;
import br.com.osmiki.sisrep.service.NivelService;
import br.com.osmiki.sisrep.service.UfService;
import br.com.osmiki.sisrep.sessao.UsuarioSessao;
import br.com.osmiki.sisrep.utils.AcessoUtils;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {
	
		@Autowired
		private EmpresaService empresaService;
		
	
	
	    @Autowired
	    private UsuarioSessao usuarioSessao;
	    	    
	    @Autowired
	    private NivelService nivelService;
	    
	    @Autowired
	    private UfService ufService;
	    
	    @Autowired
	    private MunicipioService municipioService;
	    
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
	    @GetMapping("/empresa")
	    public String showForm(Model model){
	        EmpresaDTO empresa = new EmpresaDTO();	        
	        System.out.print("PASSOU AQUI");
	        if (!AcessoUtils.isEmpresarialAdm(usuarioSessao)) {
	        	//Carregar as listas que vão fazer parte da Empresa (UF ETC)
	        
	            List<MunicipioDTO> municipiosDTO = MunicipioConverter.toDTOList(municipioService.findAll());
	            
	            model.addAttribute("municipiosDTO", municipiosDTO); // Envia a lista de DTOs para a view
	            model.addAttribute("empresa", empresa); // Note o nome explícito "usuario"
	        }else{
	        	  model.addAttribute("msgErro", "USUÁRIO SEM PERMISSÃO PARA ADICIONAR NOVOS USUÁRIOS À EMPRESA");
	        	  return "empresas/index";
	        }
	        	       
	        return "empresas/create";
	    }
	    
	  //Criando um novo usuario (POST /usuarios)
	    @PostMapping
	    public String addEmpresa(@Valid @ModelAttribute("empresa") EmpresaDTO empresaDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
	        
	    	if (result.hasErrors()) {
	            String msgErro = "";
	            for (ObjectError error : result.getAllErrors()) {
	                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
	            }
	          
	            System.out.println("ERRO!!!!!!!!");
	            return "empresas/create";
	        }
	        try {
	        	// Busca o município diretamente
	            ResponseEntity<Municipio> response = municipioService.findById(empresaDTO.getMunicipio().getIdMunicipio());
	            
	            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
	                throw new RuntimeException("Município não encontrado");
	            }
	        	
	         // Cria a empresa e associa o município
	            Empresa empresa = EmpresaConverter.toEntity(empresaDTO);
	            empresa.setMunicipio(response.getBody()); // Usa o município diretamente
	          
	            
	            // Salvar A EMPRESA convertido com tratamento de erro
	            try {
	                empresaService.create(empresa);
	                redirectAttributes.addFlashAttribute("msgOk", "Empresa registrado com sucesso!");
	                
	                return "redirect:/empresas?page=0&size=10&sortField=idEmpresa&sortDirection=asc";
	            } catch (Exception e) {
	                // Log do erro completo
	                e.printStackTrace();
	                
	                // Mensagem amigável para o usuário
	                String msgErro = "Erro ao salvar Empresa: " + e.getMessage();
	                if (e.getCause() != null) {
	                    msgErro += " - Causa: " + e.getCause().getMessage();
	                }
	                
	                model.addAttribute("msgErro", msgErro);
	                return "empresas/create";
	            }
	            
	            
	                       
	        } catch (Exception e) {
	            String msgErro = e.getCause().getMessage();
	            model.addAttribute("msgErro", msgErro);
	            return "empresas/create";
	        }
	      //model.addAttribute("usuarios", usuarioService.findAll());
	     // Redireciona para a listagem com parâmetros padrão
	   //     return "redirect:/usuarios?page=0&size=10&sortField=nome&sortDirection=asc";
	    }
	

}
