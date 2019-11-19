package com.example.DicePoker.GUI;

import com.example.DicePoker.GUIController.GameController;
import com.example.DicePoker.Logic.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Label response;

    @Override
    public void start(Stage myStage) {
        // Give the stage a title.
        myStage.setTitle("DICE POKER");

        // Use a FlowPane for the root node. In this case,
        // vertical and horizontal gaps of 10.
        FlowPane rootNode = new FlowPane(10, 10);
        GridPane mainMenuGrid = new GridPane();
        mainMenuGrid.setVgap(10);


        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);
        rootNode.setId("pane");

        // Create a scene.
        Scene myScene = new Scene(rootNode, 400, 500);
        myScene.getStylesheets().add("com.example.DicePoker.css");

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create a label.
        response = new Label("DicePoker");
        response.setId("intro");
        response.setTranslateY(-30);



        // Create two push buttons.

        Button btnNewGame = new Button("New Game");
        btnNewGame.getStyleClass().add("buttonMain");
        btnNewGame.setStyle("-fx-text-fill: #ffe6e6;");
        Button btnLoad = new Button("Load Game");
        btnLoad.getStyleClass().add("buttonMain");
        btnLoad.setStyle("-fx-text-fill: #ffe6e6;");
        Button btnRules = new Button("Rules");
        btnRules.getStyleClass().add("buttonMain");
        btnRules.setStyle("-fx-text-fill: #ffe6e6;");
        Button btnQuit = new Button("Quit");
        btnQuit.getStyleClass().add("buttonMain");
        btnQuit.setStyle("-fx-text-fill: #ffe6e6;");



        mainMenuGrid.add(response, 0,1);
        mainMenuGrid.add(btnNewGame,0,2);
        mainMenuGrid.add(btnLoad,0,3);
        mainMenuGrid.add(btnRules,0,4);
        mainMenuGrid.add(btnQuit,0,5);

        // Handle the action events for the New Game Button
        btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                RegisterPlayers();
            }
        });

        //Handle the action events for the Load Game Button
        btnLoad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                loadGame();
            }
            //TODO -- Sett action til ny scene --//

        });

        // Handle the action events for the Rules button.
        btnRules.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                Rules();
            }

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

    @FXML
    public void RegisterPlayers(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterPlayers.fxml"));
            Parent player = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Welcome to new player creation page");
            stage.setScene(new Scene(player,400,300));
            player.getStylesheets().setAll("com.example.DicePoker.css");
            stage.show();
        } catch (IOException e){
            System.out.println("Could not load the page");
            e.printStackTrace();
            return;
        }
    }

    @FXML
    public void Rules(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Rules.fxml"));
            Parent rules = fxmlLoader.load();
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("DicePoker Rules");
            stage.setScene(new Scene(rules,500,400));
            rules.getStylesheets().add("com.example.DicePoker.css");
            stage.show();
        } catch (IOException e){
            System.out.println("Could not load the page");
            e.printStackTrace();
            return;
        }

    }

    @FXML
    public void loadGame() {

        Player p1 = new Player("Temp1", 1, true);
        Player p2 = new Player("Temp2", 2, true);
        Player p3 = new Player("Temp3", 3, true);
        Player p4 = new Player("Temp4", 4, true);

        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/NewGame.fxml"));
            Parent startGame = fxmlLoader.load();
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("DICE POKER");
            stage.setScene(new Scene(startGame, 620, 480));
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        GameController gameController = fxmlLoader.getController();
        gameController.initialize(p1, p2, p3, p4);
        gameController.loadGame();


    }
}
