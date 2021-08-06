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


    public void createUser(ActionEvent event) throws IOException {
        // TODO: Save all data correctly
        // TODO: Tell user if any errors
        // TODO: Do better username,email and password check

        String username = signUpUserField.getText();
        String email = signUpEmailField.getText();
        String password = signUpPasswordField.getText();
        String rePassword = signUpRePasswordField.getText();

        try {
            File file = new File("users/"+username+".txt");
            if (isValidUsername(username) && file.createNewFile()) {
                writeEmailToFile(email,file);
                writePassWordToFile(password,rePassword,file);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidUsername(String username){
        return username.length() > 5;
    }

    private void writeEmailToFile(String email, File file) throws IOException {
        try {
            if (isValidEmail(email)){
                writeToUserFile(email,file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isValidEmail(String email){
        return email.contains("@");
    }


    private void writePassWordToFile(String password, String rePassword, File file) throws IOException {
        try {
            if (isValidPassword(password,rePassword)){
                writeToUserFile(password,file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isValidPassword(String password,String rePassword){
        return (password.equals(rePassword) && password.length()>=8);
    }


    private void writeToUserFile(String text, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        printWriter.println(text);

        // Can be done for global writers
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();

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
