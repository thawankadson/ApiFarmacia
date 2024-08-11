package com.thawandev.CrudDesafio.dto;

import com.thawandev.CrudDesafio.entities.Material;
import java.time.LocalDate;

public class MaterialDTO {

    private Long id;
    private String nome;
    private String lote;
    private LocalDate validade;
    private Integer quantidadeTotal;
    private Integer saidaEstoque;

    public MaterialDTO() {
    }

    public MaterialDTO(Material material) {
        this.id = material.getId();
        this.nome = material.getNome();
        this.lote = material.getLote();
        this.validade = material.getValidade();
        this.quantidadeTotal = material.getQuantidadeTotal();
        this.saidaEstoque = material.getSaidaEstoque();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Integer getSaidaEstoque() {
        return saidaEstoque;
    }

    public void setSaidaEstoque(Integer saidaEstoque) {
        this.saidaEstoque = saidaEstoque;
    }
}