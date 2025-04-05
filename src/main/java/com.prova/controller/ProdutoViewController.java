package com.prova.controller;

import com.prova.controller.ProdutoController;
import com.prova.model.Categoria;
import com.prova.model.Produto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoViewController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNome;
    @FXML private TextField txtDescricao;
    @FXML private DatePicker dpFabricacao;
    @FXML private DatePicker dpValidade;
    @FXML private TextField txtPrecoCompra;
    @FXML private TextField txtPrecoVenda;
    @FXML private TextField txtQuantidade;

    @FXML private TextField txtCategoriaId;
    @FXML private TextField txtCategoriaNome;
    @FXML private TextField txtCategoriaDesc;
    @FXML private TextField txtCategoriaSetor;

    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto, String> colCodigo;
    @FXML private TableColumn<Produto, String> colNome;
    @FXML private TableColumn<Produto, BigDecimal> colPreco;
    @FXML private TableColumn<Produto, Integer> colEstoque;
    @FXML private TableColumn<Produto, String> colCategoria;

    private ProdutoController produtoController;

    private ObservableList<Produto> listaProdutos;

    @FXML
    public void initialize() {
        produtoController = new ProdutoController();
        listaProdutos = FXCollections.observableArrayList();
        tabelaProdutos.setItems(listaProdutos);

        // Define colunas
        colCodigo.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCodigo()));
        colNome.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNome()));
        colPreco.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getPrecoVenda()));
        colEstoque.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getQuantidadeEstoque()).asObject());
        colCategoria.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCategoria().getNome()));

        carregarProdutos();
        tabelaProdutos.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> preencherFormulario(novo)
        );
    }

    @FXML
    public void onSalvar() {
        try {
            Produto p = montarProdutoDoFormulario();
            String msg = produtoController.salvarProduto(p);
            mostrarAlerta(Alert.AlertType.INFORMATION, msg);
            limparFormulario();
            carregarProdutos();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao salvar: " + e.getMessage());
        }
    }

    @FXML
    public void onExcluir() {
        Produto selecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            String msg = produtoController.removerProduto(selecionado.getCodigo());
            mostrarAlerta(Alert.AlertType.INFORMATION, msg);
            limparFormulario();
            carregarProdutos();
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecione um produto para excluir.");
        }
    }

    @FXML
    public void onNovo() {
        limparFormulario();
    }

    private void carregarProdutos() {
        listaProdutos.setAll(produtoController.listarProdutos());
    }

    private void preencherFormulario(Produto p) {
        if (p != null) {
            txtCodigo.setText(p.getCodigo());
            txtNome.setText(p.getNome());
            txtDescricao.setText(p.getDescricao());
            dpFabricacao.setValue(p.getDataFabricacao());
            dpValidade.setValue(p.getDataValidade());
            txtPrecoCompra.setText(p.getPrecoCompra().toString());
            txtPrecoVenda.setText(p.getPrecoVenda().toString());
            txtQuantidade.setText(String.valueOf(p.getQuantidadeEstoque()));

            Categoria c = p.getCategoria();
            txtCategoriaId.setText(String.valueOf(c.getId()));
            txtCategoriaNome.setText(c.getNome());
            txtCategoriaDesc.setText(c.getDescricao());
            txtCategoriaSetor.setText(c.getSetor());
        }
    }

    private Produto montarProdutoDoFormulario() {
        return new Produto(
                txtCodigo.getText(),
                txtNome.getText(),
                txtDescricao.getText(),
                dpFabricacao.getValue(),
                dpValidade.getValue(),
                new BigDecimal(txtPrecoCompra.getText()),
                new BigDecimal(txtPrecoVenda.getText()),
                Integer.parseInt(txtQuantidade.getText()),
                new Categoria(
                        Integer.parseInt(txtCategoriaId.getText()),
                        txtCategoriaNome.getText(),
                        txtCategoriaDesc.getText(),
                        txtCategoriaSetor.getText()
                )
        );
    }

    private void limparFormulario() {
        txtCodigo.clear();
        txtNome.clear();
        txtDescricao.clear();
        dpFabricacao.setValue(null);
        dpValidade.setValue(null);
        txtPrecoCompra.clear();
        txtPrecoVenda.clear();
        txtQuantidade.clear();
        txtCategoriaId.clear();
        txtCategoriaNome.clear();
        txtCategoriaDesc.clear();
        txtCategoriaSetor.clear();
        tabelaProdutos.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
