package com.example.DicePoker.GUI;

import com.sun.java.accessibility.util.TopLevelWindowListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create a label.
        response = new Label("DicePoker");


        // Create two push buttons.
        Button btnNewGame = new Button("New Game");
        //TODO -- Make load button -- //
        Button btnRules = new Button("Rules");
        Button btnQuit = new Button("Quit");

        btnQuit.centerShapeProperty();

        mainMenuGrid.add(response, 0,1);
        mainMenuGrid.add(btnNewGame,0,2);
        mainMenuGrid.add(btnRules,0,3);
        mainMenuGrid.add(btnQuit,0,4);

        // Handle the action events for the New Game Button
        btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("You pressed New Game.");
            }
            //TODO -- Sett action til ny scene -- //

        });

        // Handle the action events for the Rules button.
        btnRules.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("You pressed Down.");
            }
            //TODO -- Sett action til ny scene --//

        });

        // Handle the action events for the New Game Button
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
