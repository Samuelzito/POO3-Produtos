package com.prova.persistence;

import com.prova.model.Categoria;
import com.prova.model.Produto;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class ProdutoDAO {

    private static final String ARQUIVO = "produtos.csv";
    private static final String SEPARADOR = ";";

    public void salvar(Produto produto) {
        List<Produto> produtos = listarTodos();
        produtos.removeIf(p -> p.getCodigo().equals(produto.getCodigo()));
        produtos.add(produto);
        escreverArquivo(produtos);
    }

    public void removerPorCodigo(String codigo) {
        List<Produto> produtos = listarTodos();
        produtos.removeIf(p -> p.getCodigo().equals(codigo));
        escreverArquivo(produtos);
    }

    public Produto buscarPorCodigo(String codigo) {
        return listarTodos().stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(SEPARADOR);

                if (campos.length < 11) continue;

                Produto p = new Produto(
                        campos[0],
                        campos[1],
                        campos[2],
                        LocalDate.parse(campos[3]),
                        LocalDate.parse(campos[4]),
                        new BigDecimal(campos[5]),
                        new BigDecimal(campos[6]),
                        Integer.parseInt(campos[7]),
                        new Categoria(
                                Integer.parseInt(campos[8]),
                                campos[9],
                                campos[10],
                                campos[11]
                        )
                );
                produtos.add(p);
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado ou erro ao ler: " + e.getMessage());
        }

        return produtos;
    }

    private void escreverArquivo(List<Produto> produtos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Produto p : produtos) {
                bw.write(String.join(SEPARADOR,
                        p.getCodigo(),
                        p.getNome(),
                        p.getDescricao(),
                        p.getDataFabricacao().toString(),
                        p.getDataValidade().toString(),
                        p.getPrecoCompra().toString(),
                        p.getPrecoVenda().toString(),
                        String.valueOf(p.getQuantidadeEstoque()),
                        String.valueOf(p.getCategoria().getId()),
                        p.getCategoria().getNome(),
                        p.getCategoria().getDescricao(),
                        p.getCategoria().getSetor()
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo: " + e.getMessage());
        }
    }
}
