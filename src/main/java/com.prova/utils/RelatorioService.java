package com.prova.utils;

import com.prova.model.Produto;
import com.prova.persistence.ProdutoDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RelatorioService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    // Produtos com validade em até 60 dias
    public List<Produto> produtosProximosVencimento() {
        LocalDate hoje = LocalDate.now();
        return produtoDAO.listarTodos().stream()
                .filter(p -> !p.getDataValidade().isBefore(hoje) &&
                        p.getDataValidade().isBefore(hoje.plusDays(60)))
                .collect(Collectors.toList());
    }

    // Produtos com estoque menor que 10
    public List<Produto> produtosEstoqueBaixo() {
        return produtoDAO.listarTodos().stream()
                .filter(p -> p.getQuantidadeEstoque() < 10)
                .collect(Collectors.toList());
    }

    // Margem de lucro média por categoria
    public Map<String, BigDecimal> margemMediaPorCategoria() {
        return produtoDAO.listarTodos().stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCategoria().getNome(),
                        Collectors.mapping(
                                p -> p.getPrecoVenda().subtract(p.getPrecoCompra()),
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        lucros -> {
                                            if (lucros.isEmpty()) return BigDecimal.ZERO;
                                            BigDecimal soma = lucros.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                                            return soma.divide(BigDecimal.valueOf(lucros.size()), 2, BigDecimal.ROUND_HALF_UP);
                                        }
                                )
                        )
                ));
    }

    // Agrupar produtos por setor
    public Map<String, List<Produto>> produtosPorSetor() {
        return produtoDAO.listarTodos().stream()
                .collect(Collectors.groupingBy(p -> p.getCategoria().getSetor()));
    }
}
