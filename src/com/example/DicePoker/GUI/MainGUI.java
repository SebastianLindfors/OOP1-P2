package com.example.DicePoker.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Label response;

    @Override
    public void start(Stage myStage) {
        // Give the stage a title.
        myStage.setTitle("Use JavaFX Buttons and Events.");

        // Use a FlowPane for the root node. In this case,
        // vertical and horizontal gaps of 10.
        FlowPane rootNode = new FlowPane(10, 10);
        GridPane mainMenuGrid = new GridPane();
        mainMenuGrid.setVgap(10);


        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 200, 300);
        myScene.getStylesheets().add("com.example.DicePoker.css");

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create a label.
        response = new Label("DicePoker");
        response.setId("intro");


        // Create two push buttons.

        Button btnNewGame = new Button("New Game");
        btnNewGame.getStyleClass().add("buttonMain");
        Button btnLoad = new Button("Load Game");
        btnLoad.getStyleClass().add("buttonMain");
        Button btnRules = new Button("Rules");
        btnRules.getStyleClass().add("buttonMain");
        Button btnQuit = new Button("Quit");
        btnQuit.getStyleClass().add("buttonMain");



        mainMenuGrid.add(response, 0,1);
        mainMenuGrid.add(btnNewGame,0,2);
        mainMenuGrid.add(btnLoad,0,3);
        mainMenuGrid.add(btnRules,0,4);
        mainMenuGrid.add(btnQuit,0,5);

        // Handle the action events for the New Game Button
        btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("You pressed New Game.");
            }
            //TODO -- Sett action til ny scene -- //

        });

        //Handle the action events for the Load Game Button
        btnLoad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("You pressed Load Game.");
            }
            //TODO -- Sett action til ny scene --//

        });

        // Handle the action events for the Rules button.
        btnRules.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("You pressed Rules.");
            }
            //TODO -- Sett action til ny scene --//

        });

        // Handle the action events for the Quit Button
        btnQuit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                System.exit(0);
            }

        });

        // Add the label and buttons to the scene graph.
        rootNode.getChildren().addAll(mainMenuGrid);

        // Show the stage and its scene.
        myStage.show();


    }
}
