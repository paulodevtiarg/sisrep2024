/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.service;

import br.com.osmiki.sisrep.model.Fornecedor;
import br.com.osmiki.sisrep.repository.EnderecoRepository;
import br.com.osmiki.sisrep.repository.FornecedorRepository;
import jakarta.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Leonardo
 */
@RestController
//@RequestMapping("/fornecedors")
public class FornecedorService {
    
    @Autowired
    private FornecedorRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    // métodos do CRUD aqui
   
    //Listando todos os fornecedors (GET /fornecedors)
    //@GetMapping(value = "/lista")
    public List findAll(){
       return repository.findAll();
    }
    
    //Obtendo um fornecedor especídifo pelo ID (GET /fornecedors  /{id})
    //@GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    //Criando um novo fornecedor (POST /fornecedors)
    //@PostMapping
    public Fornecedor create(@RequestBody Fornecedor fornecedor, MultipartFile file){
        
        Fornecedor teste = repository.findByCnpj(fornecedor.getCnpj());
        if (teste != null) {
            throw new EntityExistsException("Erro: Fornecedor já existe no sistema com o CNPJ "+fornecedor.getCnpj());
        }
        String codigo = "000/00";
        String cnpj = fornecedor.getCnpj().substring(0, 10);
        Fornecedor max = repository.findTopByCnpjLikeOrderByCodigoFornecedorDesc(cnpj+"%");
        if (max != null) {
            codigo = maxCodigoFilial(max.getCodigoFornecedor());
            fornecedor.setCodigoFornecedor(codigo);
        } else {
            max = repository.findTopByOrderByCodigoFornecedorDesc();
            fornecedor.setCodigoFornecedor(max != null ? maxCodigo(max.getCodigoFornecedor()) : "001/00");
        }
        if (fornecedor.getTelefones() != null && !fornecedor.getTelefones().isEmpty()) {
            fornecedor.getTelefones().forEach(telefone -> telefone.setFornecedor(fornecedor));
        }
        if (fornecedor.getCondicoesPagamento() != null && !fornecedor.getCondicoesPagamento().isEmpty()) {
            fornecedor.getCondicoesPagamento().forEach(condicao -> condicao.setFornecedor(fornecedor));
        }
        
        try {
            fornecedor.setLogo(file != null ? file.getBytes() : null);
        } catch (Exception e) {
            System.out.println("ERRO OCORRIDO AO CONVERTER LOGOMARCA "+ e.getMessage());
        }
        
        return repository.save(fornecedor);
    }
    
    //Atualizando um fornecedor (PUT /fornecedors)
    //@PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
            @RequestBody Fornecedor fornecedor, MultipartFile file) {
        fornecedor.setIdFornecedor(id);
        if(fornecedor.getEndereco() != null){
            enderecoRepository.save(fornecedor.getEndereco());
        }
                
        if (fornecedor.getTelefones() != null && !fornecedor.getTelefones().isEmpty()) {
            fornecedor.getTelefones().forEach(telefone -> telefone.setFornecedor(fornecedor));
        }
        if (fornecedor.getCondicoesPagamento() != null && !fornecedor.getCondicoesPagamento().isEmpty()) {
            fornecedor.getCondicoesPagamento().forEach(condicao -> condicao.setFornecedor(fornecedor));
        }
        
        try {
            fornecedor.setLogo(file != null ? file.getBytes() : null);
        } catch (Exception e) {
            System.out.println("ERRO OCORRIDO AO CONVERTER LOGOMARCA "+ e.getMessage());
        }
        
        return repository.findById(id)
                .map(record -> {
                    record.setCalcularComissaoSobreIpi(fornecedor.isCalcularComissaoSobreIpi());
                    record.setCnpj(fornecedor.getCnpj());
                    record.setComissao(fornecedor.getComissao());
                    record.setComissaoPorProduto(fornecedor.isComissaoPorProduto());
                    record.setCondicoesPagamento(fornecedor.getCondicoesPagamento());
                    record.setContaBancaria(fornecedor.getContaBancaria());
                    record.setContato(fornecedor.getContato());
                    record.setDataAlteracao(LocalDateTime.now());
                    record.setDataInicio(fornecedor.getDataInicio());
                    record.setDescontarFreteAntesComissao(fornecedor.getDescontarFreteAntesComissao());
                    record.setDesconto1(fornecedor.getDesconto1());
                    record.setDesconto2(fornecedor.getDesconto2());
                    record.setDesconto3(fornecedor.getDesconto3());
                    record.setDesconto4(fornecedor.getDesconto4());
                    record.setDesconto5(fornecedor.getDesconto5());
                    record.setDesconto6(fornecedor.getDesconto6());
                    record.setEmail(fornecedor.getEmail());
                    record.setEnviarPedidoEmail(fornecedor.isEnviarPedidoEmail());
                    record.setGrupo(fornecedor.getGrupo());
                    record.setIe(fornecedor.getIe());
                    record.setInativo(fornecedor.isInativo());
                    record.setLogo(fornecedor.getLogo());
                    record.setMostrarMaisDeUmDescontoItemPedido(fornecedor.isMostrarMaisDeUmDescontoItemPedido());
                    record.setNomeFantasia(fornecedor.getNomeFantasia());
                    record.setObrigadoCorPedido(fornecedor.isObrigadoCorPedido());
                    record.setPertenceGrupo(fornecedor.isPertenceGrupo());
                    record.setRazaoSocial(fornecedor.getRazaoSocial());
                    record.setTipoComissao(fornecedor.getTipoComissao());
                    record.setTelefones(fornecedor.getTelefones());
                    //usuario
                    Fornecedor updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    //Removendo um fornecedor pelo ID (DELETE /fornecedors/{id})
    //@DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }  
    
    private String maxCodigoFilial(String codigo) {
        String[] split = codigo.split("/");
        Integer max = Integer.parseInt(split[1]);
        max++;
        codigo = "0"+max;
        codigo = split[0]+"/"+codigo.substring(codigo.length()-2);
        return codigo;
    }
    
    private String maxCodigo(String codigo) {
        String[] split = codigo.split("/");
        Integer max = Integer.parseInt(split[0]);
        max++;
        codigo = "00"+max;
        codigo = codigo.substring(codigo.length()-3)+"/00";
        return codigo;
    }

    public ResponseEntity<?> consultaCnpj(String cnpj) {
        String url = "https://open.cnpja.com/office/" + cnpj;
        System.out.println(url);
        try {
            RestTemplate restTemplate = new RestTemplate();
            String resposta = restTemplate.getForObject(url, String.class);
            System.out.println(resposta);

            // Processar a resposta se necessário
            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
