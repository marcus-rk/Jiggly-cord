package com.example.javafx_jigglycord.controllers;

import com.example.javafx_jigglycord.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Controller {

    protected void loadMainPage(@NotNull Stage stage) throws IOException {
        Parent root = FXMLLoader.load((App.class.getResource("mainPage/MainPage.fxml")));
        Scene newScene = new Scene(root);

        // Changing css-stylesheet to SignUp.css
        String mainPageCSS = App.class.getResource("mainPage/MainPage.css").toExternalForm();
        newScene.getStylesheets().add(mainPageCSS);

        stage.setScene(newScene);
        stage.show();
    }

    protected void loadSignUpPage(@NotNull Stage stage) throws IOException {
        Parent root = FXMLLoader.load((App.class.getResource("signUpPage/SignUp.fxml")));
        Scene newScene = new Scene(root);

        // Changing css-stylesheet to SignUp.css
        String signUpCSS = App.class.getResource("signUpPage/SignUp.css").toExternalForm();
        newScene.getStylesheets().add(signUpCSS);

        stage.setScene(newScene);
        stage.show();
    }

}
