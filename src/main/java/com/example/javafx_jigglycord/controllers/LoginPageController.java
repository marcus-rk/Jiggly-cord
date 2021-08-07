package com.example.javafx_jigglycord.controllers;

import com.example.javafx_jigglycord.Global;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Controller class which responsibility is to check for, if user is valid
 * and log in.
 */
public class LoginPageController extends Controller {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button forgotPassword;
    @FXML
    private Button signUp;

    /**
     * Updating to Sign-Up scene with fxml and css
     * @param event button clicked
     * @throws IOException ...
     */
    public void signUp(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        super.loadSignUpPage(stage);
    }

    public void login(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean validLogIn = true;

        try {
            File userFile = new File("src/main/resources/users/"+username+".txt");

            // Check if user exists
            boolean userExists = userFile.exists();
            if (!userExists && !username.isEmpty()){
                validLogIn = false;
                System.out.println("User does not exist");
            }

            // Check if password matches to username
            if (!passwordMatch(password,userFile)){
                validLogIn = false;
                System.out.println("Not correct password");
            }

            // Load main page if valid login
            if (validLogIn){
                Global.currentUserFile = userFile;
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                super.loadMainPage(stage);
            }
        } catch (FileNotFoundException e){
            if (username.isEmpty() || password.isEmpty()) System.out.println("Please fill out all fields");
            else System.out.println("File not found");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean passwordMatch(@NotNull String password, @NotNull File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        boolean passwordMatch = false;

        String line;
        while ((line=reader.readLine())!=null){
            if (line.contains("password:")){
                line=line.replace("password:","");
                int lastIndex = line.indexOf(',');

                String newLine = line.substring(0,lastIndex);
                if (newLine.matches(password))
                    passwordMatch=true;
            }
        }
        return passwordMatch;
    }

    public void forgotPassword(ActionEvent event){
        // TODO: Make forgotPassword have functionallity
        System.out.println("forgotPassword() has been pressed");
    }

}