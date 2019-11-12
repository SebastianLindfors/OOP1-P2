package com.example.DicePoker.GUIController;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

import java.awt.*;

public class GameController {

    @FXML
    private ToggleButton die1, die2, die3, die4, die5;

    @FXML
    private Button p1_die1, p1_die2, p1_die3, p1_die4, p1_die5;

    @FXML
    private Button p2_die1, p2_die2, p2_die3, p2_die4, p2_die5;

    @FXML
    private Button p3_die1, p3_die2, p3_die3, p3_die4, p3_die5;

    @FXML
    private Button p4_die1, p4_die2, p4_die3, p4_die4, p4_die5;

    @FXML
    private Label player1, player2, player3, player4;

    @FXML
    private Label player_turn;

    @FXML
    private  Label pot_text;

    @FXML
    private TextField Bet_field;

    @FXML
    private Button Bet_button, Call_button;

    @FXML
    private Button Fold_button;

    @FXML
    private Button Roll, Reroll;


    @FXML
    public void playerName () {
        if (name_pc_human1.getText().isEmpty()){} else {
        player1.setText(name_pc_human1.getText());}
        if (name_pc_human2.getText().isEmpty()){} else {
            player1.setText(name_pc_human2.getText());}
        if (name_pc_human3.getText().isEmpty()){} else {
            player1.setText(name_pc_human3.getText());}
        if (name_pc_human4.getText().isEmpty()){} else {
            player1.setText(name_pc_human4.getText());}

    }
}
