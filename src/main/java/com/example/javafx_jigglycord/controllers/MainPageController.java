package com.example.javafx_jigglycord.controllers;

import com.example.javafx_jigglycord.Global;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainPageController {
    @FXML
    private VBox dialogVBOX;
    @FXML
    private TextField textField;


    public void sendMessage() throws IOException {
        addDialogBoxToVBOX();
        textField.clear();
    }

    @FXML
    void addDialogBoxToVBOX() throws IOException {
        // Updates dialogVBOX with new DialogBox
        dialogVBOX.setAlignment(Pos.BOTTOM_CENTER); // From bottom -> top
        dialogVBOX.getChildren().add(createDialogBox());
    }

    private Pane createDialogBox() throws IOException {
        UserController currentUser = UserController.getUserFromFile(Global.currentUserFile);

        // Create new pane
        StackPane pane = new StackPane();
        pane.setPrefSize(100.0,20.0);
        pane.setId("dialogPane");

        // Add text to pain
        Label text = new Label();
        String usernameString = "  "+currentUser.getUsername()+": ";
        text.setText(usernameString+textField.getText());
        text.setId("dialogPaneText");
        pane.getChildren().add(text);

        return pane;
    }

}
