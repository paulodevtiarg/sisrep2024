package br.com.osmiki.sisrep.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.osmiki.sisrep.model.Estado;
import br.com.osmiki.sisrep.model.Municipio;
import br.com.osmiki.sisrep.model.Nivel;
import br.com.osmiki.sisrep.repository.MunicipioRepository;
import br.com.osmiki.sisrep.repository.NivelRepository;

@Service
public class NivelService {
	private NivelRepository repository;

	NivelService(NivelRepository nivelRepository) {
        this.repository = nivelRepository;
    }
    //Listando todos os municipios (GET /municipios)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    
    public Nivel findById(Integer id) {
        return repository.findById(id)
                       .orElseThrow(() -> new RuntimeException("Nível com ID " + id + " não encontrado"));
    }
    
    
    public Nivel create(@RequestBody Nivel objeto){
        return repository.save(objeto);
    }

    

}
