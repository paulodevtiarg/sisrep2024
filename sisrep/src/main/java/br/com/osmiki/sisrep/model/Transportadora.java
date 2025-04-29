/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.osmiki.sisrep.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Leonardo
 */
@Entity
public class Transportadora implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransportadora;
    @NotNull
    @NotBlank(message = "Nome obrigatório")
    private String nome;
    @NotNull
    @NotBlank(message = "CNPJ obrigatório")
    private String cnpj;
    private String email;
    private String contato;
    private boolean inativo;
    @ManyToOne
    @JoinColumn(name = "endereco")
    private Endereco endereco;
    private String codigo;
    private BigDecimal valorPeso;
    private BigDecimal valorPorcentagem;
    private LocalDateTime dataAlteracao;
    private boolean calcularFreteSobreValorSemIpi;

    public Transportadora() {
    }

    public Transportadora(Integer idTransportadora) {
        this.idTransportadora = idTransportadora;
    }

    public Integer getIdTransportadora() {
        return idTransportadora;
    }

    public void setIdTransportadora(Integer idTransportadora) {
        this.idTransportadora = idTransportadora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValorPeso() {
        return valorPeso;
    }

    public void setValorPeso(BigDecimal valorPeso) {
        this.valorPeso = valorPeso;
    }

    public BigDecimal getValorPorcentagem() {
        return valorPorcentagem;
    }

    public void setValorPorcentagem(BigDecimal valorPorcentagem) {
        this.valorPorcentagem = valorPorcentagem;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public boolean isCalcularFreteSobreValorSemIpi() {
        return calcularFreteSobreValorSemIpi;
    }

    public void setCalcularFreteSobreValorSemIpi(boolean calcularFreteSobreValorSemIpi) {
        this.calcularFreteSobreValorSemIpi = calcularFreteSobreValorSemIpi;
    }
    
}
