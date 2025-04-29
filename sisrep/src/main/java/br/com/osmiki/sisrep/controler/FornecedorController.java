/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.controler;

import br.com.osmiki.sisrep.enumerador.TipoTelefoneEnum;
import br.com.osmiki.sisrep.model.Endereco;
import br.com.osmiki.sisrep.model.Fornecedor;
import br.com.osmiki.sisrep.model.Municipio;
import br.com.osmiki.sisrep.model.TelefoneFornecedor;
import br.com.osmiki.sisrep.service.EnderecoService;
import br.com.osmiki.sisrep.service.MunicipioService;
import br.com.osmiki.sisrep.service.UfService;
import br.com.osmiki.sisrep.service.FornecedorService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Leonardo
 */
@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {
    private FornecedorService service;
    private UfService ufService;
    private EnderecoService enderecoService;
    private MunicipioService municipioService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/logomarcas";
    
    FornecedorController( FornecedorService fornecedorService, UfService ufService, EnderecoService enderecoService, MunicipioService municipioService){
        service = fornecedorService;
        this.ufService = ufService;
        this.enderecoService = enderecoService;
        this.municipioService = municipioService;
    }
    
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("fornecedores", service.findAll());
        return "fornecedores/index";
    }
    
    @GetMapping("/fornecedor")
    public String showForm(Model model){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setTelefones(new ArrayList<TelefoneFornecedor>());
        model.addAttribute(fornecedor);
        model.addAttribute("estados", ufService.findAll());
        model.addAttribute("telefoneFornecedor", new TelefoneFornecedor());
        return "fornecedores/create";
    }
    
    //Criando um novo fornecedor (POST /fornecedores)
    @PostMapping
    public String addUser(@Valid Fornecedor fornecedor, @RequestPart("logomarca") MultipartFile file, BindingResult result, Model model) {
        fornecedor.setDataInicio(LocalDate.now());
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            System.out.println("ERRO VALIDAÇÃO!!!!!!!!");
            return "fornecedores/create";
        }
        fornecedor.setDataAlteracao(LocalDateTime.now());
        try {
            enderecoService.create(fornecedor.getEndereco());
            service.create(fornecedor, file);
            model.addAttribute("msgOk", "Fornecedor registrado.");
        } catch (Exception e) {
            e.printStackTrace();
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "fornecedores/create";
        }        
        model.addAttribute("fornecedores", service.findAll());
        return "fornecedores/index";
    }
    
    @GetMapping("/fornecedor/{id}")
    public String showForm(@PathVariable("id") Integer id, Model model){
        ResponseEntity u = service.findById(id);
        if (u.hasBody()) {
            //System.out.println("fornecedor presente "+u.getStatusCodeValue());
            Fornecedor fornecedor = (Fornecedor) u.getBody();
            //System.out.println("confirmar ID "+fornecedor.getIdPessoa());
            model.addAttribute(fornecedor);
            model.addAttribute("estados", ufService.findAll());
            model.addAttribute("municipios", municipioService.findByEstado(fornecedor.getEndereco().getMunicipio().getEstado().getIdEstado()));
            model.addAttribute("telefoneFornecedor", new TelefoneFornecedor());
        }
        return "fornecedores/update";
    }
    
    //Atualizar um fornecedor (PUT /fornecedores/ID)
    @PostMapping(value="/{id}")
    public String addUser(@PathVariable("id") Integer id, @Valid Fornecedor fornecedor, @RequestPart("logomarca") MultipartFile file, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String msgErro = "";
            for (ObjectError error : result.getAllErrors()) {
                msgErro = msgErro+error.getDefaultMessage()+System.lineSeparator(); 
            }
            model.addAttribute("msgErro", msgErro);
            fornecedor.setIdFornecedor(id);
            return "fornecedores/update";
        }
        try {
            service.update(id, fornecedor, file);
            model.addAttribute("msgOk", "Fornecedor atualizado.");
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "fornecedores/update";
        }
        model.addAttribute("fornecedores", service.findAll());
        return "fornecedores/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        try {
            service.delete(id);
            model.addAttribute("msgOk", "Fornecedor excluido.");
            model.addAttribute("fornecedores", service.findAll());
            return "fornecedores/index";
        } catch (Exception e) {
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "fornecedores/index";
        }
    }
    
    @RequestMapping(value="/fornecedores", params={"addRow"})
    public String addRow(final Fornecedor fornecedor, final BindingResult bindingResult) {
        fornecedor.getTelefones().add(new TelefoneFornecedor());
        return "fornecedor";
    }

    @RequestMapping(value="/fornecedores", params={"removeRow"})
    public String removeRow(final Fornecedor fornecedor, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer telefoneId = Integer.valueOf(req.getParameter("removeTelefone"));
        fornecedor.getTelefones().remove(telefoneId.intValue());
        return "fornecedor";
    }
    
    @GetMapping("/consultaCnpj")
    public String consultaCnpj(@RequestParam String cnpj, Model model) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj(cnpj);
        System.out.println("CNPJ "+cnpj);
        ResponseEntity response = service.consultaCnpj(cnpj);
        try{
            // Obter o JSON como String
            String jsonBody = (String) response.getBody();

            // Converter para um JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonBody);
            
            fornecedor.setNomeFantasia(rootNode.get("alias").asText());
            fornecedor.setRazaoSocial(rootNode.get("company").get("name").asText());
            
            JsonNode addressNode = rootNode.get("address");
            Municipio municipio = municipioService.findByIbge(addressNode.get("municipality").asInt());
            Endereco endereço = new Endereco();
            endereço.setMunicipio(municipio);
            endereço.setLogradouro(addressNode.get("street").asText());
            endereço.setNumero(addressNode.get("number").asText());
            endereço.setBairro(addressNode.get("district").asText());
            endereço.setComplemento(addressNode.get("details").isNull() ? "" : addressNode.get("details").asText());
            endereço.setCep(addressNode.get("zip").asText());
            fornecedor.setEndereco(endereço);
            List<TelefoneFornecedor> telefones = new ArrayList<>();
            
            JsonNode telJson = rootNode.get("phones");
            if(telJson.isArray()){
                for (JsonNode jn : telJson) {
                    TelefoneFornecedor tel = new TelefoneFornecedor();
                    tel.setDdd(jn.get("area").asInt());
                    tel.setNumero(jn.get("number").asText());
                    tel.setTipoTelefone(jn.get("type").asText().equals("LANDLINE") ? TipoTelefoneEnum.FONE : TipoTelefoneEnum.CELULAR);
                    telefones.add(tel);
                    fornecedor.setTelefones(telefones);
                }
            }
            
            
            JsonNode emailJson = rootNode.get("emails");
            if (emailJson.isArray()) {
                for (JsonNode jn : emailJson) {
                    fornecedor.setEmail(jn.get("address").asText());
                    break;
                }
            }

            model.addAttribute(fornecedor);
            model.addAttribute("estados", ufService.findAll());
            model.addAttribute("municipios", municipio);
//            model.addAttribute("telefoneFornecedor", new TelefoneFornecedor());
            return "fornecedores/create";
        } catch (Exception e) {
            e.printStackTrace();
            String msgErro = e.getMessage();
            model.addAttribute("msgErro", msgErro);
            return "fornecedores/index";
        }  
    }
}
