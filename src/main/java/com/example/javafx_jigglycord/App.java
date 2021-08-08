package com.example.javafx_jigglycord;

import com.example.javafx_jigglycord.network.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    //TODO: Make controllers change to their own scenes
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("loginPage/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Settings to application window
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Jiggly-Cord v1");

        // Add icon to application window
        Image iconImage = new Image(getClass().getResource("images/Jiggly_icon.png").toExternalForm());
        stage.getIcons().add(iconImage);

        // Link css stylesheet to scene
        String logInCSS = this.getClass().getResource("loginPage/LoginPage.css").toExternalForm();
        scene.getStylesheets().add(logInCSS);

        // Should always be at the end of the start-method
        stage.setScene(scene);
        stage.show();

        // Start and setup server
        Server.getServer().setIp("localhost");
        Server.getServer().setPort(1111);
        Server.getServer().setEndTag("BYE");
        // Server.getServer().startServer();
        // Look into mulit-thread

    }
}