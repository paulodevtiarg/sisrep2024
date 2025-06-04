/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.converter.UsuarioConverter;
import br.com.osmiki.sisrep.dtos.UsuarioDTO;
import br.com.osmiki.sisrep.model.TipoAcesso;
import br.com.osmiki.sisrep.model.Usuario;
import br.com.osmiki.sisrep.repository.UsuarioRepository;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo
 */
@RestController
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	// Mantém o método original sem argumentos
	public List<UsuarioDTO> findAll() {
		return UsuarioConverter.toDTOList(repository.findAll());
	}
	
	public Page<UsuarioDTO> findPaginated(int page, int size,
            Integer idEmpresaLogada,
            Integer idNivelLogado,
            TipoAcesso tipoAcessoLogado,
            Integer idPessoa,
            String nome, 
            String email,
            Boolean ativo,
            String sortField,
            String sortDirection) {

		// 1. Define campos de ordenação válidos
	    List<String> camposValidos = Arrays.asList("idPessoa", "nome", "email", "dataCadastro");
	    String campoOrdenacao = camposValidos.contains(sortField) ? sortField : "idPessoa";
	    Sort.Direction direcao = "desc".equalsIgnoreCase(sortDirection) ? 
	            Sort.Direction.DESC : Sort.Direction.ASC;
	    // 2. Cria a Specification com filtros
	    Specification<Usuario> spec = (root, query, cb) -> {
	        List<Predicate> predicates = new ArrayList<>();
	        
	     // Filtro principal por empresa (tratando null)
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
	        // ... outros filtros
	        
	        if (email != null && !email.isEmpty()) {
	            predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
	        }
	               
	        if (ativo != null) {
	            // Usar "inativo" em vez de "ativo" e inverter a lógica
	            predicates.add(cb.equal(root.get("inativo"), !ativo));
	        }

	        	        
	        return cb.and(predicates.toArray(new Predicate[0]));
	    };
	     // 3. Cria a ordenação
	    Sort sort = Sort.by("desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC, campoOrdenacao );
	    
	    // 4. Executa a consulta
	    Pageable pageable = PageRequest.of(page, size, Sort.by(direcao, campoOrdenacao));
	    return repository.findAll(spec, pageable).map(UsuarioConverter::toDTO);
	}

	// Obtendo um usuario especídifo pelo ID (GET /usuarios /{id})
	// @GetMapping(path = {"/{id}"})
	public ResponseEntity findById(@PathVariable Integer id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	// Criando um novo usuario (POST /usuarios)
	// @PostMapping
	public Usuario create(@RequestBody Usuario usuario) {
		usuario.setDataAlteracao(LocalDateTime.now());
		return repository.save(usuario);
	}

	// Atualizando um usuario (PUT /usuarios)
	// @PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
		return repository.findById(id).map(record -> {
			record.setDataAlteracao(LocalDateTime.now());
			record.setNome(usuario.getNome());
			record.setEmail(usuario.getEmail());
			record.setInativo(usuario.getInativo());
			record.setProximoNumeroAssistencia(usuario.getProximoNumeroAssistencia());
			record.setCliente(usuario.getCliente());
			record.setEmailSenha(usuario.getEmailSenha());
			record.setGerenteFornecedor(usuario.getGerenteFornecedor());
			record.setProximoNumeroContasPagar(usuario.getProximoNumeroContasPagar());
			record.setProximoNumeroInadimplenciaCliente(usuario.getProximoNumeroInadimplenciaCliente());
			Usuario updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	// Removendo um usuario pelo ID (DELETE /usuarios/{id})
	// @DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	// @RequestMapping(value = "/login", method = RequestMethod.GET)
	/*
	 * public ResponseEntity getUsuario(@RequestBody Usuario usuario){ String cpf,
	 * senha; cpf = usuario.getCpf(); senha = usuario.getSenha(); //Usuario user =
	 * repository.findByCpf(usuario.getCpf()); return
	 * repository.findByCpfAndSenha(cpf, senha) .map(record ->
	 * ResponseEntity.ok().body(record)) .orElse(ResponseEntity.notFound().build());
	 * // if (user == null) { // return null; // } // String senha =
	 * usuario.getSenha(); // if (user.getSenha().equals(senha) &&
	 * !user.getInativo()) { // return user; // } // return null; }
	 */
	public Usuario findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

}
