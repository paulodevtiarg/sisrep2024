package br.com.osmiki.sisrep.converter;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.osmiki.sisrep.dtos.EmpresaDTO;
import br.com.osmiki.sisrep.dtos.NivelDTO;
import br.com.osmiki.sisrep.model.Empresa;
import br.com.osmiki.sisrep.model.Nivel;

public class NivelConverter {
	private NivelConverter() {}
	
	
	public static NivelDTO toDTO(Nivel nivel) {
		if(nivel == null) {
			return null;
		}
		NivelDTO dto = new NivelDTO();
		dto.setId(nivel.getId());
		dto.setNivel(nivel.getNivel());
		dto.setData_cad(nivel.getData_cad());
		dto.setData_alt(nivel.getData_alt());
		dto.setStatus(nivel.getStatus());
		dto.setUsuarios(nivel.getUsuarios());
		
		return dto;
		
	}
	
	public static Nivel toEntity(NivelDTO nivelDTO) {
		Nivel nivel = new Nivel();
		
		nivel.setId(nivelDTO.getId());
		nivel.setNivel(nivelDTO.getNivel());
		// Verifica e atualiza data_cad (Date)
	    if (nivelDTO.getData_cad() != null) {
	        nivel.setData_cad(nivelDTO.getData_cad());
	    } else {
	        // Se data_cad for nulo, insere a data atual
	        nivel.setData_cad(new Date()); // Usa new Date() para data/hora atual
	    }
	    // Verifica e atualiza data_alt (Date)
	    if (nivelDTO.getData_alt() != null) {
	        // Se data_alt já existe, atualiza com a data atual
	        nivel.setData_alt(new Date());
	    } else {
	        // Se data_alt for nulo, mantém como nulo
	        nivel.setData_alt(null);
	    }
		nivel.setData_alt(nivelDTO.getData_alt());
		nivel.setStatus(nivelDTO.getStatus());
		nivel.setUsuarios(nivelDTO.getUsuarios());
		
		return nivel;
		
	}
	public static List<NivelDTO> toDTOList(List<Nivel> niveis) {
        return niveis.stream()
                      .map(NivelConverter::toDTO)
                      .collect(Collectors.toList());
    }

}
