package br.com.osmiki.sisrep.dtos;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.osmiki.sisrep.model.Usuario;


public class NivelDTO {
	
private Integer id;	

	
	private String nivel;	
	
	
	private Date data_cad;
	
	
	private Date data_alt;
	
	
	private Boolean status;
	
	
    private List<Usuario> usuarios = new ArrayList<>();

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNivel() {
		return nivel;
	}


	public void setNivel(String nivel) {
		this.nivel = nivel;
	}


	public Date getData_cad() {
		return data_cad;
	}


	public void setData_cad(Date data_cad) {
		this.data_cad = data_cad;
	}


	public Date getData_alt() {
		return data_alt;
	}


	public void setData_alt(Date data_alt) {
		this.data_alt = data_alt;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	

}
