package com.prova.utils;

import com.prova.model.Produto;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RelatorioProduto {

    /**
     * Filtra produtos cuja validade esteja entre 2 dias atrás e até 60 dias no futuro.
     */
    public static List<Produto> filtrarProximosVencimento(List<Produto> produtos) {
        LocalDate hoje = LocalDate.now();
        LocalDate limiteInferior = hoje.minusDays(2);   // tolera vencidos recentes
        LocalDate limiteSuperior = hoje.plusDays(60);   // até 2 meses adiante

        return produtos.stream()
                .filter(p -> !p.getDataValidade().isBefore(limiteInferior) &&
                        !p.getDataValidade().isAfter(limiteSuperior))
                .collect(Collectors.toList());
    }

    /**
     * Retorna os produtos com estoque abaixo de 10 unidades.
     */
    public static List<Produto> filtrarEstoqueBaixo(List<Produto> produtos) {
        return produtos.stream()
                .filter(p -> p.getQuantidadeEstoque() < 10)
                .collect(Collectors.toList());
    }

    /**
     * Calcula a margem de lucro média por categoria.
     */
    public static Map<String, Double> calcularMargemLucroPorCategoria(List<Produto> produtos) {
        return produtos.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCategoria().getNome(),
                        Collectors.averagingDouble(p ->
                                p.getPrecoVenda().subtract(p.getPrecoCompra()).doubleValue()
                        )
                ));
    }

    /**
     * Agrupa os produtos por setor da categoria.
     */
    public static Map<String, List<Produto>> agruparPorSetor(List<Produto> produtos) {
        return produtos.stream()
                .collect(Collectors.groupingBy(p -> p.getCategoria().getSetor()));
    }
}
