package com.prova.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Produto {
    private String codigo;
    private String nome;
    private String descricao;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private BigDecimal precoCompra;
    private BigDecimal precoVenda;
    private int quantidadeEstoque;
    private Categoria categoria;

    public Produto(String codigo, String nome, String descricao, LocalDate dataFabricacao, LocalDate dataValidade,
                   BigDecimal precoCompra, BigDecimal precoVenda, int quantidadeEstoque, Categoria categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %d un - R$ %.2f", nome, categoria.getNome(), quantidadeEstoque, precoVenda);
    }
}
