package com.example.javafx_jigglycord.controllers;

import com.example.javafx_jigglycord.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    @FXML
    private VBox dialogVBOX;
    @FXML
    private TextField textField;


    public void sendMessage(ActionEvent event) throws IOException {
        addDialogBox();
        textField.clear();
    }

    @FXML
    void addDialogBox() {
        // Updates dialogVBOX with new DialogBox
        dialogVBOX.getChildren().add(createDialogBox());
    }

    private Pane createDialogBox(){
        // Create new pane
        Pane pane = new Pane();
        pane.setPrefSize(100.0,100.0);
        pane.setStyle("-fx-background-color: #00797a;");

        // Add text to pain
        Text text = new Text();
        text.setText(textField.getText());
        pane.getChildren().add(text);
        System.out.println(pane.getChildren().toString());

        return pane;
    }

}
