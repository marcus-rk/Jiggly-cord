package com.example.javafx_jigglycord.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Controller which responsibility is to check user input for sign up page,
 * and then create a new user, with UserController class.
 */
public class SignUpController extends Controller {
    @FXML
    private TextField signUpUserField;
    @FXML
    private TextField signUpEmailField;
    @FXML
    private TextField signUpPasswordField;
    @FXML
    private TextField signUpRePasswordField;

    /**
     * Takes the user back to the login page
     * @param event click on 'back' button
     * @throws IOException ...
     */
    public void backToLogInPage(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        super.loadLogIn(stage);
    }

    /**
     * Creating new user by getting username, email and password.
     * @param event click on 'Create user'
     */
    public void signUpUser(ActionEvent event) {
        // TODO: Tell user if any errors
        // TODO: Do better username,email and password check

        String username = signUpUserField.getText();
        String email = signUpEmailField.getText();
        String password = signUpPasswordField.getText();
        String rePassword = signUpRePasswordField.getText();

        // Check for valid user input
        boolean notNullInput = isValidUsername(username);

        if (!isValidEmail(email)){
            notNullInput = false;
        }
        if (!isValidPassword(password,rePassword)){
            notNullInput = false;
        }

        // Create new user/usercontroller file to 'users' package, if it does not exist already
        if (notNullInput){
            try {
                File file = new File("src/main/resources/users/"+username+".txt");
                if (file.createNewFile()){
                    new UserController(file,username,email,password);

                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    super.loadLogIn(stage);
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

}
