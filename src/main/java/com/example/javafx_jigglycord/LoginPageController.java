package com.example.javafx_jigglycord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    public void signUp(ActionEvent event){
        // TODO: Make forgotPassword have functionallity
        System.out.println("signUp() has been pressed");
    }

}