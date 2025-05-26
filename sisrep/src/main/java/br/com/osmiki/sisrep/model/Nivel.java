package br.com.osmiki.sisrep.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "nivel")
public class Nivel implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nivel")
	private Integer id;	

	@Column(name = "nivel")
	private String nivel;	
	
	@Column(name="data_cad")
	private Date data_cad;
	
	@Column(name="data_alt")
	private Date data_alt;
	
	@Column(name="status")
	private Boolean status;
	
	@OneToMany(mappedBy = "nivel")
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
