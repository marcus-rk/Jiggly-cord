package com.example.javafx_jigglycord.controllers;

import com.example.javafx_jigglycord.App;
import javafx.css.CssParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class LoginPageController {
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginButton;
    @FXML
    private Button forgotPassword;
    @FXML
    private Button signUp;


    public void login(ActionEvent event){
        // TODO: Make a try-catch to see if registered.
        System.out.println("username: "+usernameField.getText());
    }

    public void forgotPassword(ActionEvent event){
        // TODO: Make forgotPassword have functionallity
        System.out.println("forgotPassword() has been pressed");
    }

    /**
     * Updating to Sign-Up scene with fxml and css
     * @param event button clicked
     * @throws IOException ...
     */
    public void signUp(ActionEvent event) throws IOException {
        // TODO: Make SignUp write to DB or file
        Parent root = FXMLLoader.load((App.class.getResource("SignUp.fxml")));
        Scene newScene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        String signUpCSS = App.class.getResource("SignUp.css").toExternalForm();
        newScene.getStylesheets().add(signUpCSS);

        stage.setScene(newScene);
        stage.show();
    }

}