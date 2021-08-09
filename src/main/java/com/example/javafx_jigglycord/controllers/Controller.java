package com.example.javafx_jigglycord.controllers;

import com.example.javafx_jigglycord.App;
import com.example.javafx_jigglycord.Global;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Super Controller class made for more general controller methods for now.
 */
public abstract class Controller {

    protected UserController getCurrentUserController() throws IOException {
        return UserController.getUserFromFile(Global.currentUserFile);
    }

    protected void loadMainPage(@NotNull Stage stage) throws IOException {
        Parent root = FXMLLoader.load((App.class.getResource("mainPage/MainPage.fxml")));
        Scene newScene = new Scene(root);

        // Changing css-stylesheet to MainPage.css
        String mainPageCSS = App.class.getResource("mainPage/MainPage.css").toExternalForm();
        newScene.getStylesheets().add(mainPageCSS);

        stage.setScene(newScene);
        stage.setFullScreen(true); // ONLY BECAUSE OF MAC 12' SCREEN
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

    protected void loadLogIn(@NotNull Stage stage) throws IOException {
        Parent root = FXMLLoader.load((App.class.getResource("loginPage/LoginPage.fxml")));
        Scene newScene = new Scene(root);

        // Changing css-stylesheet to LoginPage.css
        String loginCSS = App.class.getResource("loginPage/LoginPage.css").toExternalForm();
        newScene.getStylesheets().add(loginCSS);

        stage.setScene(newScene);
        stage.show();
    }


}
