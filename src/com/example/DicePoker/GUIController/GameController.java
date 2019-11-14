package com.example.DicePoker.GUIController;

import com.example.DicePoker.Logic.GameEngine;
import com.example.DicePoker.Logic.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;

public class GameController {

    boolean turn = false;

    GameEngine  mainGame;

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
    private Label player_turn, pot_text;

    @FXML
    private TextField Bet_field;

    @FXML
    private Button Bet_button, Call_button;

    @FXML
    private Button Fold_button;

    @FXML
    private Button Roll, Reroll;


    public void initialize(Player p1, Player p2, Player p3, Player p4){

        ArrayList<Player> lop = new ArrayList<>();
        lop.add(p1);
        lop.add(p2);
        lop.add(p3);
        lop.add(p4);
        mainGame = new GameEngine(lop);

        //----------------NNew Round-------------------------//

        mainGame.anteUp();





//        if(p1 != null){
//            player1.setText(p1.getName());
//            turn = true;
//            player_turn.setText(p1.getName() + " turn");
//        }
//
//        if(p2 != null) {
//            player2.setText(p2.getName());
//            if(turn == false){
//                turn = true;
//                player_turn.setText(p2.getName() + " turn");
//            }
//        }
//
//        if(p3 != null) {
//            player3.setText(p3.getName());
//            if(turn == false){
//                turn = true;
//                player_turn.setText(p3.getName() + " turn");
//            }
//        }
//
//        if(p4 != null) {
//            player4.setText(p4.getName());
//            if(turn == false){
//                turn = true;
//                player_turn.setText(p4.getName() + " turn");
//          }
//        }
    }

    public void Roll(){


    }

    private void UpdateBoardState() {
        ArrayList<Player> allPlayers = mainGame.getListOfAllPlayers();


    }



}
