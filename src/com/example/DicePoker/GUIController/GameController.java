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
    private Label player1, player2, player3, player4, p1_mark, p2_mark, p3_mark, p4_mark;

    @FXML
    private Label player_turn, pot_text, p1_pot, p2_pot, p3_pot, p4_pot;

    @FXML
    private Label PlayerPot[] = new Label[]{p1_mark,p2_mark,p3_mark,p4_mark};

    @FXML
    private TextField Bet_field;

    @FXML
    private Button Bet_button, Call_button;

    @FXML
    private Button Fold_button;

    @FXML
    private Button Roll, Reroll;

    @FXML
    private ToggleButton Die1, Die2, Die3, Die4, Die5;


    public ToggleButton[] Dices;
    public Button[] P1Dice;
    public Button[] P2Dice;
    public Button[] P3Dice;
    public Button[] P4Dice;

    private Button[][] allPlayerDice;


    int startPosition = 0;

    @FXML
    public void initialize(Player p1, Player p2, Player p3, Player p4){

        Dices = new ToggleButton[5];

        Dices[0] = Die1;
        Dices[1] = Die2;
        Dices[2] = Die3;
        Dices[3] = Die4;
        Dices[4] = Die5;

        PlayerPot = new Label[4];
        PlayerPot[0] = p1_mark;
        PlayerPot[1] = p2_mark;
        PlayerPot[2] = p3_mark;
        PlayerPot[3] = p4_mark;

        allPlayerDice = new Button[4][5];
        allPlayerDice[0][0] = p1_die1;
        allPlayerDice[0][1] = p1_die2;
        allPlayerDice[0][2] = p1_die3;
        allPlayerDice[0][3] = p1_die4;
        allPlayerDice[0][4] = p1_die5;
        allPlayerDice[1][0] = p2_die1;
        allPlayerDice[1][1] = p2_die2;
        allPlayerDice[1][2] = p2_die3;
        allPlayerDice[1][3] = p2_die4;
        allPlayerDice[1][4] = p2_die5;
        allPlayerDice[2][0] = p3_die1;
        allPlayerDice[2][0] = p3_die1;
        allPlayerDice[2][1] = p3_die2;
        allPlayerDice[2][2] = p3_die3;
        allPlayerDice[2][3] = p3_die4;
        allPlayerDice[2][4] = p3_die5;
        allPlayerDice[3][0] = p4_die1;
        allPlayerDice[3][0] = p4_die1;
        allPlayerDice[3][1] = p4_die2;
        allPlayerDice[3][2] = p4_die3;
        allPlayerDice[3][3] = p4_die4;
        allPlayerDice[3][4] = p4_die5;


        ArrayList<Player> lop = new ArrayList<>();
        lop.add(p1);
        lop.add(p2);
        lop.add(p3);
        lop.add(p4);
        mainGame = new GameEngine(lop);

        if(p1 != null){
            startPosition = 1;
            player1.setText(p1.getName());
            turn = true;
            p1_mark.setText(String.valueOf(p1.getMarker()));
            player_turn.setText(p1.getName() + " turn");
        }

        if(p2 != null) {
            startPosition = 2;
            player2.setText(p2.getName());
            p2_mark.setText(String.valueOf(p2.getMarker()));
            if(turn == false){
                turn = true;
                player_turn.setText(p2.getName() + " turn");
            }
        }

        if(p3 != null) {
            player3.setText(p3.getName());
            p3_mark.setText(String.valueOf(p3.getMarker()));
            if(turn == false){
                turn = true;
                player_turn.setText(p3.getName() + " turn");
            }
        }

        if(p4 != null) {
            player4.setText(p4.getName());
            p4_mark.setText(String.valueOf(p4.getMarker()));
            if(turn == false){
                turn = true;
                player_turn.setText(p4.getName() + " turn");
          }
        }
        pot_text.setText(String.valueOf(mainGame.getCurrentPot()));
    }

    public void Roll(){

        if (mainGame.currentPlayerPayAnte()) {

            int diceValues[] = mainGame.rollCurrentPlayer();

            for(int i=0; i< diceValues.length; i++) {

                Dices[i].getStyleClass().clear();
               if(diceValues[i] == 1){
                   Dices[i].getStyleClass().add("dice1");
               } else if(diceValues[i] == 2){
                   Dices[i].getStyleClass().add("dice2");
               } else if(diceValues[i] == 3){
                   Dices[i].getStyleClass().add("dice3");
               } else if(diceValues[i] == 4){
                   Dices[i].getStyleClass().add("dice4");
               } else if(diceValues[i] == 5){
                   Dices[i].getStyleClass().add("dice5");
               } else if(diceValues[i] == 6){
                   Dices[i].getStyleClass().add("dice6");
               }
            }

            int currentPlayerNumber = mainGame.getCurrentPlayerNumber();  //Current player number refers to which players graphics are to be updated.
            System.out.println("Player Number: " + currentPlayerNumber);
            updatePlayerDie(currentPlayerNumber);

            updateMarkerAndPot(currentPlayerNumber);
        }
        mainGame.nextPlayer();


    }

//    private void UpdateBoardState() {
//        ArrayList<Player> allPlayers = mainGame.getListOfAllPlayers();
//
//
//        int number = 0;
//        for (Player player : allPlayers){
//            System.out.println(player.getName() + " " + number);
//
//            PlayerPot[number].setText("Mer Moo");
//            //p1_mark.setText(String.valueOf(player.getMarker()));
//            number++;
//        }
//
//        pot_text.setText(String.valueOf(mainGame.getCurrentPot()));
//    }

    public void updatePlayerDie(int playerNumber) {

        int[] playerDice = mainGame.getPlayer(playerNumber).getDieValues();

        for (int i = 0; i < 5; i++) {
            allPlayerDice[playerNumber - 1][i].getStyleClass().clear();
            if(playerDice[i] == 1){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice1");
            } else if(playerDice[i] == 2){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice2");
            } else if(playerDice[i] == 3){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice3");
            } else if(playerDice[i] == 4){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice4");
            } else if(playerDice[i] == 5){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice5");
            } else if(playerDice[i] == 6){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice6");
            }
        }



    }

    public void updateMarkerAndPot(int player) {
        PlayerPot[player - 1].setText(String.valueOf(mainGame.getPlayer(player).getMarker()));
        pot_text.setText(String.valueOf(mainGame.getCurrentPot()));
    }



}
