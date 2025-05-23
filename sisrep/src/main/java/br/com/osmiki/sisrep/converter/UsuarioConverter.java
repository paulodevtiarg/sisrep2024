package br.com.osmiki.sisrep.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.query.Page;

import br.com.osmiki.sisrep.dtos.UsuarioDTO;
import br.com.osmiki.sisrep.model.Usuario;

public class  UsuarioConverter {
	   private UsuarioConverter() {} // Construtor privado para evitar instanciação

	    public static UsuarioDTO toDTO(Usuario usuario) {
	        if (usuario == null) {
	            return null;
	        }
	        
	        UsuarioDTO dto = new UsuarioDTO();
	        dto.setIdPessoa(usuario.getIdPessoa());
	        dto.setNome(usuario.getNome());
	        dto.setCpf(usuario.getCpf());
	        dto.setSenha(usuario.getSenha());
	        dto.setInativo(usuario.getInativo());
	        dto.setProximoNumeroAssistencia(usuario.getProximoNumeroAssistencia());
	        dto.setProximoNumeroInadimplenciaCliente(usuario.getProximoNumeroInadimplenciaCliente());
	        dto.setDataAlteracao(usuario.getDataAlteracao());
	        dto.setProximoNumeroContasPagar(usuario.getProximoNumeroContasPagar());
	        dto.setEmail(usuario.getEmail());
	        dto.setEmailSenha(usuario.getEmailSenha());
	        dto.setGerenteFornecedor(usuario.getGerenteFornecedor());
	        dto.setCliente(usuario.getCliente());
	        dto.setDataAlteracaoSenha(usuario.getDataAlteracaoSenha());
	        return dto;
	    }
	    
	    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
	    	Usuario usuario = new Usuario();
	        usuario.setIdPessoa(usuarioDTO.getIdPessoa());
	        usuario.setNome(usuarioDTO.getNome());
	        usuario.setCpf(usuarioDTO.getCpf());
	        usuario.setSenha(usuarioDTO.getSenha());
	        usuario.setInativo(usuarioDTO.isInativo());
	        usuario.setProximoNumeroAssistencia(usuarioDTO.getProximoNumeroAssistencia());
	        usuario.setProximoNumeroInadimplenciaCliente(usuarioDTO.getProximoNumeroInadimplenciaCliente());
	        usuario.setDataAlteracao(usuarioDTO.getDataAlteracao());
	        usuario.setProximoNumeroContasPagar(usuarioDTO.getProximoNumeroContasPagar());
	        usuario.setEmail(usuarioDTO.getEmail());
	        usuario.setEmailSenha(usuarioDTO.getEmailSenha());
	        usuario.setGerenteFornecedor(usuarioDTO.getGerenteFornecedor());
	        usuario.setCliente(usuarioDTO.getCliente());
	        usuario.setDataAlteracaoSenha(usuarioDTO.getDataAlteracaoSenha());
	        return usuario;
	    	
	    	
	    }
	    // Opcional: Conversão em massa (List/Page)
	    public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
	        return usuarios.stream()
	                      .map(UsuarioConverter::toDTO)
	                      .collect(Collectors.toList());
	    }

	    

}
