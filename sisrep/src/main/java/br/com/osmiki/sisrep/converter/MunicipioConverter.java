package br.com.osmiki.sisrep.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.osmiki.sisrep.dtos.MunicipioDTO;
import br.com.osmiki.sisrep.dtos.NivelDTO;
import br.com.osmiki.sisrep.model.Municipio;
import br.com.osmiki.sisrep.model.Nivel;

public class MunicipioConverter {
	private MunicipioConverter() {}
	
	public static MunicipioDTO toDTO(Municipio municipio) {
		if(municipio == null) {
			return null;
		}
		MunicipioDTO dto = new MunicipioDTO();
		dto.setIdMunicipio(municipio.getIdMunicipio());
		dto.setNome(municipio.getNome());
		dto.setIbge(municipio.getIbge());
		dto.setEstado(municipio.getEstado());
		dto.setDataAlteracao(municipio.getDataAlteracao());
		
		return dto;
		
	}
	
	public static Municipio toEntity(MunicipioDTO dto) {
		if(dto == null) {
			return null;
		}
		Municipio municipio = new Municipio();
		municipio.setIdMunicipio(dto.getIdMunicipio());
		municipio.setNome(dto.getNome());
		municipio.setIbge(dto.getIbge());
		municipio.setEstado(dto.getEstado());
		municipio.setDataAlteracao(dto.getDataAlteracao());
		
		return municipio;
		
	}
	public static List<MunicipioDTO> toDTOList(List<Municipio> municipios) {
        return municipios.stream()
                      .map(MunicipioConverter::toDTO)
                      .collect(Collectors.toList());
    }
}
