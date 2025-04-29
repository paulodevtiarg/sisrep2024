/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.model;

import br.com.osmiki.sisrep.enumerador.TipoComissaoEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Leonardo
 */
@Entity
public class Fornecedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFornecedor;
    private TipoComissaoEnum tipoComissao;
    @NotNull
    @NotBlank(message = "Razão Social campo obrigatório")
    private String razaoSocial;
    @NotNull
    @NotBlank(message = "Nome Fantasia campo obrigatório")
    private String nomeFantasia;
    private String cnpj;
    private String ie;
    private BigDecimal comissao;
    private String email;
    private String contato;
    private boolean enviarPedidoEmail;
    private boolean inativo;
    @ManyToOne
    @JoinColumn(name = "endereco", nullable = true)
    private Endereco endereco;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    private Integer grupo;
    private String codigoFornecedor;
    @ManyToOne
    @JoinColumn(name = "usuario", nullable = true)
    private Usuario usuario;
    private LocalDateTime dataAlteracao;
    private boolean pertenceGrupo;
    @Lob
    @Column(name="logo", nullable=true, columnDefinition="mediumblob")
    private byte[] logo;
    private BigDecimal desconto1;
    private BigDecimal desconto2;
    private BigDecimal desconto3;
    private BigDecimal desconto4;
    private BigDecimal desconto5;
    private BigDecimal desconto6;
    private boolean calcularComissaoSobreIpi;
    private BigDecimal descontarFreteAntesComissao;
    private boolean comissaoPorProduto;
    private boolean obrigadoCorPedido;
    private boolean mostrarMaisDeUmDescontoItemPedido;
    @OneToMany(mappedBy="fornecedor", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<TelefoneFornecedor> telefones;
    @OneToMany(mappedBy="fornecedor", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<CondicoesPagamento> condicoesPagamento;
    private String contaBancaria;

    public Fornecedor() {
    }

    public Fornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public TipoComissaoEnum getTipoComissao() {
        return tipoComissao;
    }

    public void setTipoComissao(TipoComissaoEnum tipoComissao) {
        this.tipoComissao = tipoComissao;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public boolean isEnviarPedidoEmail() {
        return enviarPedidoEmail;
    }

    public void setEnviarPedidoEmail(boolean enviarPedidoEmail) {
        this.enviarPedidoEmail = enviarPedidoEmail;
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public String getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(String codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public boolean isPertenceGrupo() {
        return pertenceGrupo;
    }

    public void setPertenceGrupo(boolean pertenceGrupo) {
        this.pertenceGrupo = pertenceGrupo;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public BigDecimal getDesconto1() {
        return desconto1;
    }

    public void setDesconto1(BigDecimal desconto1) {
        this.desconto1 = desconto1;
    }

    public BigDecimal getDesconto2() {
        return desconto2;
    }

    public void setDesconto2(BigDecimal desconto2) {
        this.desconto2 = desconto2;
    }

    public BigDecimal getDesconto3() {
        return desconto3;
    }

    public void setDesconto3(BigDecimal desconto3) {
        this.desconto3 = desconto3;
    }

    public BigDecimal getDesconto4() {
        return desconto4;
    }

    public void setDesconto4(BigDecimal desconto4) {
        this.desconto4 = desconto4;
    }

    public BigDecimal getDesconto5() {
        return desconto5;
    }

    public void setDesconto5(BigDecimal desconto5) {
        this.desconto5 = desconto5;
    }

    public BigDecimal getDesconto6() {
        return desconto6;
    }

    public void setDesconto6(BigDecimal desconto6) {
        this.desconto6 = desconto6;
    }

    public boolean isCalcularComissaoSobreIpi() {
        return calcularComissaoSobreIpi;
    }

    public void setCalcularComissaoSobreIpi(boolean calcularComissaoSobreIpi) {
        this.calcularComissaoSobreIpi = calcularComissaoSobreIpi;
    }

    public BigDecimal getDescontarFreteAntesComissao() {
        return descontarFreteAntesComissao;
    }

    public void setDescontarFreteAntesComissao(BigDecimal descontarFreteAntesComissao) {
        this.descontarFreteAntesComissao = descontarFreteAntesComissao;
    }

    public boolean isComissaoPorProduto() {
        return comissaoPorProduto;
    }

    public void setComissaoPorProduto(boolean comissaoPorProduto) {
        this.comissaoPorProduto = comissaoPorProduto;
    }

    public boolean isObrigadoCorPedido() {
        return obrigadoCorPedido;
    }

    public void setObrigadoCorPedido(boolean obrigadoCorPedido) {
        this.obrigadoCorPedido = obrigadoCorPedido;
    }

    public boolean isMostrarMaisDeUmDescontoItemPedido() {
        return mostrarMaisDeUmDescontoItemPedido;
    }

    public void setMostrarMaisDeUmDescontoItemPedido(boolean mostrarMaisDeUmDescontoItemPedido) {
        this.mostrarMaisDeUmDescontoItemPedido = mostrarMaisDeUmDescontoItemPedido;
    }

    public List<TelefoneFornecedor> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneFornecedor> telefones) {
        this.telefones = telefones;
    }

    public List<CondicoesPagamento> getCondicoesPagamento() {
        return condicoesPagamento;
    }

    public void setCondicoesPagamento(List<CondicoesPagamento> condicoesPagamento) {
        this.condicoesPagamento = condicoesPagamento;
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }
    
    public String generateBase64Image() {
        if(logo != null)
            return Base64.getEncoder().encodeToString(logo);
        else return "";
    }
    
}
