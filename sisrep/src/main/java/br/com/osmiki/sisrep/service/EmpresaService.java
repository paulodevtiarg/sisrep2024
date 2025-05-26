package br.com.osmiki.sisrep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.osmiki.sisrep.model.Empresa;
import br.com.osmiki.sisrep.repository.EmpresaRepository;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    public String getNomeEmpresa(Integer idEmpresa) {
        if (idEmpresa == null || idEmpresa == 0) {
            return "Não vinculado";
        }
        return empresaRepository.findById(idEmpresa)
                .map(Empresa::getNome)
                .orElse("Empresa não encontrada");
    }
}
