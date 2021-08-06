package com.example.javafx_jigglycord.controllers;

import com.example.javafx_jigglycord.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    public void createUser(ActionEvent event){
        // TODO: Make a try-catch to see if registered.
        System.out.println("signUp() has been pressed");
    }

    public void backToLogInPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load((App.class.getResource("LoginPage.fxml")));
        Scene newScene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        String loginPageCSS = App.class.getResource("LoginPage.css").toExternalForm();
        newScene.getStylesheets().add(loginPageCSS);

        stage.setScene(newScene);
        stage.show();
    }

}
