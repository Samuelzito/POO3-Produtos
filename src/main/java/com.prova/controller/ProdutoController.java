package com.prova.controller;

import com.prova.model.Produto;
import com.prova.persistence.ProdutoDAO;
import com.prova.utils.ValidadorProduto;

import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }

    public String salvarProduto(Produto produto) {
        // Novo: captura o erro específico, se existir
        String erro = ValidadorProduto.getMensagemErroValidacao(produto);
        if (erro != null) {
            return erro; // Retorna a mensagem específica
        }

        produtoDAO.salvar(produto);
        return "Produto salvo com sucesso!";
    }

    public String removerProduto(String codigo) {
        Produto encontrado = produtoDAO.buscarPorCodigo(codigo);
        if (encontrado == null) {
            return "Produto não encontrado!";
        }

        produtoDAO.removerPorCodigo(codigo);
        return "Produto removido com sucesso!";
    }

    public Produto buscarProduto(String codigo) {
        return produtoDAO.buscarPorCodigo(codigo);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarTodos();
    }
}
