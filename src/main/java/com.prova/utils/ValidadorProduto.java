package com.prova.utils;

import com.prova.model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ValidadorProduto {

    public static boolean validarCodigo(String codigo) {
        return codigo != null && codigo.trim().matches("[a-zA-Z0-9]{3,8}");
    }

    public static boolean validarNome(String nome) {
        return nome != null && nome.trim().length() >= 3;
    }

    public static boolean validarDataFabricacao(LocalDate dataFabricacao) {
        if (dataFabricacao == null) return false;

        LocalDate hoje = LocalDate.now();
        LocalDate minData = hoje.minusWeeks(4);   // até 4 semanas atrás
        LocalDate maxData = hoje.plusDays(2);     // até 2 dias no futuro

        return !dataFabricacao.isBefore(minData) && !dataFabricacao.isAfter(maxData);
    }

    public static boolean validarDataValidade(LocalDate dataFabricacao, LocalDate dataValidade) {
        return dataFabricacao != null &&
                dataValidade != null &&
                !dataValidade.isBefore(dataFabricacao);
    }

    public static boolean validarPrecos(BigDecimal precoCompra, BigDecimal precoVenda) {
        return precoCompra != null && precoVenda != null &&
                precoCompra.compareTo(BigDecimal.ZERO) > 0 &&
                precoVenda.compareTo(precoCompra) > 0;
    }

    public static boolean validarQuantidadeEstoque(int quantidade) {
        return quantidade >= 0;
    }

    public static boolean validarProdutoCompleto(Produto produto) {
        return validarCodigo(produto.getCodigo()) &&
                validarNome(produto.getNome()) &&
                validarDataFabricacao(produto.getDataFabricacao()) &&
                validarDataValidade(produto.getDataFabricacao(), produto.getDataValidade()) &&
                validarPrecos(produto.getPrecoCompra(), produto.getPrecoVenda()) &&
                validarQuantidadeEstoque(produto.getQuantidadeEstoque());
    }

    public static String getMensagemErroValidacao(Produto produto) {
        if (!validarCodigo(produto.getCodigo()))
            return "Código inválido! Use entre 3 e 8 letras ou números.";

        if (!validarNome(produto.getNome()))
            return "Nome inválido! Mínimo de 3 caracteres.";

        if (!validarDataFabricacao(produto.getDataFabricacao()))
            return "Data de fabricação inválida! Use uma data entre 4 semanas atrás e até 2 dias no futuro.";

        if (!validarDataValidade(produto.getDataFabricacao(), produto.getDataValidade()))
            return "Data de validade inválida! Deve ser igual ou posterior à data de fabricação.";

        if (!validarPrecos(produto.getPrecoCompra(), produto.getPrecoVenda()))
            return "Preços inválidos! O preço de venda deve ser maior que o de compra, e ambos maiores que zero.";

        if (!validarQuantidadeEstoque(produto.getQuantidadeEstoque()))
            return "Quantidade inválida! Deve ser zero ou mais.";

        return null; // Tudo certo
    }
}
