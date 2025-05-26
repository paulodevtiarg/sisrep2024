package br.com.osmiki.sisrep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.osmiki.sisrep.model.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Integer>, JpaSpecificationExecutor<Empresa> {

}
