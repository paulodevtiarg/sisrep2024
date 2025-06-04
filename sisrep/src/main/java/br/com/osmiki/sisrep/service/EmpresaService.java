package br.com.osmiki.sisrep.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import br.com.osmiki.sisrep.dtos.EmpresaDTO;
import br.com.osmiki.sisrep.converter.EmpresaConverter;
import br.com.osmiki.sisrep.converter.UsuarioConverter;
import br.com.osmiki.sisrep.model.Empresa;
import br.com.osmiki.sisrep.model.TipoAcesso;
import br.com.osmiki.sisrep.repository.EmpresaRepository;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    public List<EmpresaDTO>findAll(){
    	return EmpresaConverter.toDTOList(empresaRepository.findAll());
    }
    
    public Page<EmpresaDTO> findPaginated(int page, int size, 
    		Integer idEmpresaLogada, 
    		Integer idNivelLogado, 
    		TipoAcesso tipoAcessoLogado,
    		Integer id,
    		String nome,
    		String cnpj,
    		String sortField,
            String sortDirection){
    	
    	 List<String> camposValidos = Arrays.asList("id", "nome", "cnpj");
    	 String campoOrdenacao = camposValidos.contains(sortField) ? sortField : "id";
    	 Sort.Direction direcao = "desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
    	 
    	 Specification<Empresa> spec = (root, query, cb)->{
    		 List<Predicate> predicates = new ArrayList<>();
    		 // Aplica filtro de empresa APENAS se não for MASTER
 	        if (tipoAcessoLogado != TipoAcesso.MASTER) {
 	            if (idEmpresaLogada != null) {
 	                predicates.add(cb.equal(root.get("id_empresa"), idEmpresaLogada));
 	            } else {
 	                predicates.add(cb.isNull(root.get("id_empresa")));
 	            }
 	        }
 	        
 	       // Filtros adicionais
	        if (nome != null && !nome.isEmpty()) {
	            predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
	        }
	        
	        // Filtros adicionais
	        if (cnpj != null && !cnpj.isEmpty()) {
	            predicates.add(cb.like(cb.lower(root.get("cnpj")), "%" + cnpj.toLowerCase() + "%"));
	        }
	        
	        return cb.and(predicates.toArray(new Predicate[0]));
    		 
    	 };
    	 Sort sort = Sort.by("desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC, campoOrdenacao );
    	 Pageable pageable = PageRequest.of(page, size, Sort.by(direcao, campoOrdenacao));
 	    return empresaRepository.findAll(spec, pageable).map(EmpresaConverter::toDTO);
    	
    }
    
    
    public String getNomeEmpresa(Integer idEmpresa) {
        if (idEmpresa == null || idEmpresa == 0) {
            return "Não vinculado";
        }
        return empresaRepository.findById(idEmpresa)
                .map(Empresa::getNome)
                .orElse("Empresa não encontrada");
    }
}
