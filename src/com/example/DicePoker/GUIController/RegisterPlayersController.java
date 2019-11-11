package com.example.DicePoker.GUIController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterPlayersController {

    @FXML
    private Button pc_human1, pc_human2, pc_human3, pc_human4;

    @FXML
    private CheckBox p1_check, p2_check, p3_check, p4_check;

    @FXML
    private TextField name_pc_human1, name_pc_human2, name_pc_human3, name_pc_human4;

    @FXML
    private Label warning_text;
    @FXML
    private Button Start;

    @FXML
    public void changePlayer1Type() {
        if (pc_human1.getText().equals("PC")) {
            pc_human1.setText("Human");
        } else {
            pc_human1.setText("PC");
        }
    }

    @FXML
    public void changePlayer2Type() {
        if (pc_human2.getText().equals("PC")) {
            pc_human2.setText("Human");
        } else {
            pc_human2.setText("PC");
        }
    }

    @FXML
    public void changePlayer3Type() {
        if (pc_human3.getText().equals("PC")) {
            pc_human3.setText("Human");
        } else {
            pc_human3.setText("PC");
        }
    }

    @FXML
    public void changePlayer4Type() {
        if (pc_human4.getText().equals("PC")) {
            pc_human4.setText("Human");
        } else {
            pc_human4.setText("PC");
        }
    }

    @FXML
    public void startGame(){
        int check = 0;
        if (p1_check.isSelected()){
            if (name_pc_human1.getText().isEmpty()) {

            } else {
                check++;}
        }
        if (p2_check.isSelected()) {
            if (name_pc_human2.getText().isEmpty()) {

            } else {
                check++;
            }
        }
        if (p3_check.isSelected()) {
            if (name_pc_human3.getText().isEmpty()) {

            } else {
                check++;
            }
        }
        if (p4_check.isSelected()) {
            if (name_pc_human4.getText().isEmpty()) {

            } else {
                check++;
            }
        }
        if (check >= 2) {
            try {

                handleCloseWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/NewGame.fxml"));
                Parent startGame = fxmlLoader.load();
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("DICE POKER");
                stage.setScene(new Scene(startGame, 620, 480));
                stage.show();
            } catch (IOException e) {
                System.out.println("Could not load the page");
                e.printStackTrace();
                return;
            }
        } else {
            warning_text.setText("Not enough players have \nnames and are ready");
        }

    }

    public void handleCloseWindow(){
        Stage stage = (Stage) Start.getScene().getWindow();
        stage.close();
    }
}
