package br.com.osmiki.sisrep.dtos;

import java.time.LocalDateTime;

import br.com.osmiki.sisrep.model.Estado;


public class MunicipioDTO {
	
    private Integer idMunicipio;
    
    private String nome;
    
    private Estado estado;
    
    private Integer ibge;
    
    private LocalDateTime dataAlteracao;

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Integer getIbge() {
		return ibge;
	}

	public void setIbge(Integer ibge) {
		this.ibge = ibge;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
    
    

}
