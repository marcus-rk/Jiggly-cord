package com.example.javafx_jigglycord.controllers;

import com.example.javafx_jigglycord.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class SignUpController {
    @FXML
    private TextField signUpUserField;
    @FXML
    private TextField signUpEmailField;
    @FXML
    private TextField signUpPasswordField;
    @FXML
    private TextField signUpRePasswordField;


    /**
     * Creating new user by getting username, email and password.
     * @param event click on 'Create user'
     * @throws IOException ...
     */
    public void createUser(ActionEvent event) throws IOException {
        // TODO: Save all data correctly (password)
        // TODO: Tell user if any errors
        // TODO: Do better username,email and password check
        // TODO: Make writers more efficient

        String username = signUpUserField.getText();
        String email = signUpEmailField.getText();
        String password = signUpPasswordField.getText();
        String rePassword = signUpRePasswordField.getText();

        boolean notNullInput = true;

        // Check for valid user input
        if (!isValidUsername(username)){
            notNullInput = false;
        }
        if (!isValidEmail(email)){
            notNullInput = false;
        }
        if (!isValidPassword(password,rePassword)){
            notNullInput = false;
        }

        // Write user info to /users, if it does not exist already
        if (notNullInput){
            try {
                File file = new File("src/main/resources/users/"+username+".txt");
                if (file.createNewFile()){
                    new UserController(file,username,email,password);

                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    loadLogIn(stage);
                } else {
                    System.out.println("User: "+username+" already exists!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("All fields must be filled correctly");
        }

    }


    /**
     * Check if valid username
     * @param username ...
     * @return true if valid or false if not
     */
    private boolean isValidUsername(@NotNull String username){
        if (username.length() >= 5)
            return true;
        else {
            System.out.println("Invalid username");
            return false;
        }
    }

    private boolean isValidEmail(@NotNull String email){
        if (email.contains("@"))
            return true;
        else {
            System.out.println("Invalid email");
            return false;
        }
    }

    private boolean isValidPassword(@NotNull String password,String rePassword){
        if ((password.equals(rePassword) && password.length()>=8))
            return true;
        else {
            System.out.println("Invalid password");
            return false;
        }
    }


    /**
     * Takes the user back to the login page
     * @param event click on 'back'
     * @throws IOException ...
     */
    public void backToLogInPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load((App.class.getResource("loginPage/LoginPage.fxml")));
        Scene newScene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        String loginPageCSS = App.class.getResource("loginPage/LoginPage.css").toExternalForm();
        newScene.getStylesheets().add(loginPageCSS);

        stage.setScene(newScene);
        stage.show();
    }

    private void loadLogIn(@NotNull Stage stage) throws IOException {
        Parent root = FXMLLoader.load((App.class.getResource("loginPage/LoginPage.fxml")));
        Scene newScene = new Scene(root);

        // Changing css-stylesheet to LoginPage.css
        String loginCSS = App.class.getResource("loginPage/LoginPage.css").toExternalForm();
        newScene.getStylesheets().add(loginCSS);

        stage.setScene(newScene);
        stage.show();
    }

}
