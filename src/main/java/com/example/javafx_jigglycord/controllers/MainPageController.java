package com.example.javafx_jigglycord.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller that controls the (whole) main page of the application for now.
 */
public class MainPageController extends Controller {
    @FXML
    private VBox dialogVBOX;
    @FXML
    private TextField textField;


    public void sendMessage() throws IOException {
        if(textField.getText().length() != 0){
        addDialogBoxToVBOX(textField.getText());
        textField.clear();
        }
    }

    @FXML
    void addDialogBoxToVBOX(String inputText) throws IOException {
        // Updates dialogVBOX with new DialogBox
        dialogVBOX.setAlignment(Pos.BOTTOM_CENTER); // From bottom -> top
        dialogVBOX.getChildren().add(createDialogBox(inputText));
    }

    private Pane createDialogBox(String inputText) throws IOException {
        UserController currentUser = super.getCurrentUserController();

        // Create new pane
        StackPane pane = new StackPane();
        pane.setPrefSize(100.0,20.0);
        pane.setId("dialogPane");

        // Add text (Label) to pane
        Label text = new Label();
        String usernameString = "  "+currentUser.getUsername()+": ";
        text.setText(usernameString+inputText);
        text.setId("dialogPaneText");
        pane.getChildren().add(text);

        return pane;
    }

}
