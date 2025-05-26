package br.com.osmiki.sisrep.converter;

import java.util.List;
import java.util.stream.Collectors;
import br.com.osmiki.sisrep.dtos.EmpresaDTO;
import br.com.osmiki.sisrep.model.Empresa;

public class EmpresaConverter {
	 private EmpresaConverter() {} // Construtor privado para evitar instanciação
	 
	 public static EmpresaDTO toDTO(Empresa empresa) {
		 if(empresa == null) {
			 return null;
		 }
		 
		 EmpresaDTO dto = new EmpresaDTO();
		 dto.setId(empresa.getId());
		 dto.setNome(empresa.getNome());
		 dto.setCnpj(empresa.getCnpj());
		 dto.setLogradouro(empresa.getLogradouro());
		 dto.setComplemento(empresa.getComplemento());
		 dto.setBairro(empresa.getBairro());
		 dto.setMunicipio(empresa.getMunicipio());
		 dto.setCep(empresa.getCep());
		 dto.setTelefone_fixo(empresa.getTelefone_fixo());
		 dto.setTelefone_celular(empresa.getTelefone_celular());
		 dto.setEmail(empresa.getEmail());
		 dto.setRazao_social(empresa.getRazao_social());
		 dto.setIe(empresa.getIe());
		 dto.setIm(empresa.getIm());
		 
		 return dto;
				 
	 }
	 
	 public static Empresa toEntity(EmpresaDTO empresaDTO) {
		 Empresa empresa = new Empresa();
		 empresa.setId(empresaDTO.getId());
		 empresa.setNome(empresaDTO.getNome());
		 empresa.setCnpj(empresaDTO.getCnpj());
		 empresa.setLogradouro(empresaDTO.getLogradouro());
		 empresa.setComplemento(empresaDTO.getComplemento());
		 empresa.setBairro(empresaDTO.getBairro());
		 empresa.setMunicipio(empresaDTO.getMunicipio());
		 empresa.setCep(empresaDTO.getCep());
		 empresa.setTelefone_fixo(empresaDTO.getTelefone_fixo());
		 empresa.setTelefone_celular(empresaDTO.getTelefone_celular());
		 empresa.setEmail(empresaDTO.getEmail());
		 empresa.setRazao_social(empresaDTO.getRazao_social());
		 empresa.setIe(empresaDTO.getIe());
		 empresa.setIm(empresaDTO.getIm());
		 
		 return empresa;
	 }
	 
	// Opcional: Conversão em massa (List/Page)
	    public static List<EmpresaDTO> toDTOList(List<Empresa> empresas) {
	        return empresas.stream()
	                      .map(EmpresaConverter::toDTO)
	                      .collect(Collectors.toList());
	    }


}
