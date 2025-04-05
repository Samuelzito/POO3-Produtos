package com.prova.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathFXML {

    public static String pathBase() {
        return "C:\\Users\\Super\\IdeaProjects\\ProvaN1\\src\\main\\java\\com.prova\\view";
    }

    public static Parent loadFXML(String fxmlName) throws Exception {
        Path fullPath = Paths.get(pathBase(), fxmlName);
        System.out.println("Caminho completo para FXML: " + fullPath.toAbsolutePath());
        FXMLLoader loader = new FXMLLoader(fullPath.toUri().toURL());
        return loader.load();
    }
}
