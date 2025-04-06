package com.prova;

import com.prova.utils.PathFXML; // MANTÉM essa e REMOVE a outra
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = PathFXML.loadFXML("produto.fxml");
        Scene scene = new Scene(root, 1040, 700);

        primaryStage.setTitle("Menu Principal");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
